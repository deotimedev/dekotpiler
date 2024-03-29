package com.deotime.dekotpiler.jar.impl

import com.deotime.dekotpiler.jar.KotlinClassContainer
import com.deotime.dekotpiler.jar.KotlinJarLoader
import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.jar.storage.KotlinJarFileLocator
import com.deotime.dekotpiler.jar.storage.KotlinLibraryProvider
import com.deotime.dekotpiler.metadata.MetadataResolver
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.translation.Translation
import kotlinx.metadata.jvm.KotlinClassMetadata
import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.util.getopt.OptionsImpl.ALLOW_CORRECTING
import org.benf.cfr.reader.util.getopt.OptionsImpl.ANTI_OBF
import org.benf.cfr.reader.util.getopt.OptionsImpl.DECOMPILER_COMMENTS
import org.benf.cfr.reader.util.getopt.OptionsImpl.TIDY_VARIABLE_NAMES
import org.benf.cfr.reader.util.getopt.PermittedOptionProvider
import org.koin.core.annotation.Single
import java.io.File

@Single
internal class CFRKotlinJarLoader(
    private val translation: Translation,
    private val metadataResolver: MetadataResolver,
    private val jarPool: KotlinJarPool,
    private val jarLocator: KotlinJarFileLocator,
    libraries: List<KotlinLibraryProvider>,
) : KotlinJarLoader {

    init {
        libraries
            .flatMap { it.provide() }
            .mapNotNull(jarLocator::locate)
            .map(::load)
            .forEach(jarPool::register)
    }

    private val config = cfrConfig {
        -DECOMPILER_COMMENTS
        -ANTI_OBF
        -ALLOW_CORRECTING
        -TIDY_VARIABLE_NAMES
    }

    override fun load(jar: File): KotlinClassContainer {
        val result = CfrDriver.Builder()
            .withOptions(config)
            .build()
            .analyse(jar.absolutePath)
        val session = translation.session()

        val jarTypes = result.second.values.flatten()
        val mappedTypes = jarTypes
            .map(session::translateType)
            .filterIsInstance<KtReferenceType>()

        val javaByName = jarTypes.associateBy { it.rawName }
        val byName = mappedTypes.associateBy { it.qualifiedName }
        val state = result.first
        return object : KotlinClassContainer {
            override val types = mappedTypes
            override fun load(type: KtReferenceType) =
                javaByName[type.qualifiedName]
                    ?.let(state::getClassFile)

            override fun type(name: String) = byName[name]

            override fun metadata(type: KtReferenceType) =
                javaByName[type.qualifiedName]
                    ?.let(state::getClassContent)
                    ?.let(metadataResolver::resolve)
                    ?.let(KotlinClassMetadata.Companion::read) // https://youtrack.jetbrains.com/issue/KT-56750
//                    ?.let(KotlinMetadataReader::read)

            override fun contains(type: KtReferenceType) = type.qualifiedName in byName.keys
        }.also { jarPool.register(it) }
    }

    private fun cfrConfig(closure: CFRConfig.() -> Unit) =
        CFRConfig().apply(closure).options

    private class CFRConfig {
        val options = mutableMapOf<String, String>()

        infix fun <T> PermittedOptionProvider.ArgumentParam<T, *>.set(value: T) {
            options[name] = value.toString()
        }

        operator fun PermittedOptionProvider.ArgumentParam<Boolean, *>.unaryPlus() = set(true)
        operator fun PermittedOptionProvider.ArgumentParam<Boolean, *>.unaryMinus() = set(false)
    }
}