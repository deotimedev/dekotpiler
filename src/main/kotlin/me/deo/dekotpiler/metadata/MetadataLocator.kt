package me.deo.dekotpiler.metadata

import kotlinx.metadata.KmFunction
import me.deo.dekotpiler.model.type.KtReferenceType

interface MetadataLocator {
    fun function(type: KtReferenceType, name: String, descriptor: String): KmFunction?
}