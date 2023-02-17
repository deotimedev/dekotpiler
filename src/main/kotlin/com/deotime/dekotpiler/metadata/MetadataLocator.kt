package com.deotime.dekotpiler.metadata

import com.deotime.dekotpiler.model.type.KtReferenceType
import kotlinx.metadata.KmFunction

interface MetadataLocator {
    fun function(type: KtReferenceType, name: String, descriptor: String): KmFunction?
}