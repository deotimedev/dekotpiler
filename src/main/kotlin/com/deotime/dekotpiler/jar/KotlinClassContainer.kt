package com.deotime.dekotpiler.jar

import com.deotime.dekotpiler.model.type.KtReferenceType
import kotlinx.metadata.jvm.KotlinClassMetadata
import org.benf.cfr.reader.entities.ClassFile

interface KotlinClassContainer {
    val types: List<KtReferenceType>
    fun type(name: String): KtReferenceType?
    fun load(type: KtReferenceType): ClassFile?
    fun metadata(type: KtReferenceType): KotlinClassMetadata?
    operator fun contains(type: KtReferenceType): Boolean
}