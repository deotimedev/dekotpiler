package me.deo.dekotpiler.decompile.impl

import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.decompile.KotlinJar
import me.deo.dekotpiler.decompile.KotlinJarLoader
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deo.dekotpiler.model.type.KtReferenceType
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.api.CfrDriver
import org.springframework.stereotype.Component
import java.io.File

import org.benf.cfr.reader.util.getopt.OptionsImpl.*
import org.benf.cfr.reader.util.getopt.PermittedOptionProvider

@Component
class CFRKotlinJarLoader(
    private val translation: Translation,
    private val metadataResolver: MetadataResolver
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
        val typeConversions = result.second.values
            .flatten()
            .associateBy {
                session.translateType(it) as KtReferenceType
            }
        val typeMappings = typeConversions.mapKeys { (type, _)  -> type.qualifiedName }
        val state = result.first
        return object : KotlinJar {
            override val types = typeConversions.keys.toList()
            override fun load(type: KtReferenceType) = state.getClassFile(typeMappings[type.qualifiedName])
            override fun metadata(type: KtReferenceType) =
                KotlinClassMetadata.read(metadataResolver.resolve(state.getClassContent(typeMappings[type.qualifiedName])))
        }
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