package com.deotime.dekotpiler.metadata.impl

import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.metadata.MetadataLocator
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.util.cache
import kotlinx.metadata.KmFunction
import kotlinx.metadata.jvm.KotlinClassMetadata
import kotlinx.metadata.jvm.signature
import org.springframework.stereotype.Component

@Component
internal class MetadataLocatorimpl(
    private val jarPool: KotlinJarPool
) : MetadataLocator {
    private val classMetadata = cache<KtReferenceType, List<KmFunction>> {
        jarPool.metadata(it)
            ?.let(::functions)
            .orEmpty()
    }

    override fun function(type: KtReferenceType, name: String, descriptor: String) =
        classMetadata[type].find { it.signature?.name == name && it.signature?.desc == descriptor }

    // todo this is not good
    private fun functions(meta: KotlinClassMetadata) = when (meta) {
        is KotlinClassMetadata.Class -> meta.toKmClass().functions
        is KotlinClassMetadata.FileFacade -> meta.toKmPackage().functions
        is KotlinClassMetadata.MultiFileClassPart -> meta.toKmPackage().functions
        is KotlinClassMetadata.SyntheticClass -> meta.toKmLambda()?.function?.let(::listOf).orEmpty()
        else -> error("What")
    }
}