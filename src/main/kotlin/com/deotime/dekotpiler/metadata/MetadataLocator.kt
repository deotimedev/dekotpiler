package com.deotime.dekotpiler.metadata

import kotlinx.metadata.KmFunction
import com.deotime.dekotpiler.model.type.KtReferenceType

interface MetadataLocator {
    fun function(type: KtReferenceType, name: String, descriptor: String): KmFunction?
}