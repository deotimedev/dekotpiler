package me.deo.dekotpiler.jar.impl

import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.jar.KotlinJar
import me.deo.dekotpiler.jar.KotlinJarLoader
import me.deo.dekotpiler.jar.KotlinJarPool
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deo.dekotpiler.model.type.KtReferenceType
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.util.getopt.OptionsImpl.ALLOW_CORRECTING
import org.benf.cfr.reader.util.getopt.OptionsImpl.ANTI_OBF
import org.benf.cfr.reader.util.getopt.OptionsImpl.DECOMPILER_COMMENTS
import org.benf.cfr.reader.util.getopt.OptionsImpl.TIDY_VARIABLE_NAMES
import org.benf.cfr.reader.util.getopt.PermittedOptionProvider
import org.springframework.stereotype.Component
import java.io.File

@Component
internal class CFRKotlinJarLoader(
    private val translation: Translation,
    private val metadataResolver: MetadataResolver,
    private val jarPool: KotlinJarPool
) : KotlinJarLoader {

    private val config = cfrConfig {
        DECOMPILER_COMMENTS set false
        ANTI_OBF set false
        ALLOW_CORRECTING set false
        TIDY_VARIABLE_NAMES set false
    }

    override fun load(jar: File): KotlinJar {
        val result = CfrDriver.Builder()
            .withOptions(config)
            .build()
            .analyse(jar.absolutePath)
        val session = translation.session()

        // fixme
        fun realName(type: KtReferenceType) = type.qualifiedName.replace("$", ".")

        val jarTypes = result.second.values.flatten()
        val mappedTypes = jarTypes
            .map(session::translateType)
            .filterIsInstance<KtReferenceType>()

        val javaByName = jarTypes.associateBy { it.rawName }
        val byName = mappedTypes.associateBy(::realName)
        val state = result.first
        return object : KotlinJar {
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
    }
}