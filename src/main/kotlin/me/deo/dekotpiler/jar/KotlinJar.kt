package me.deo.dekotpiler.jar

import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.model.type.KtReferenceType
import org.benf.cfr.reader.entities.ClassFile

interface KotlinJar {
    val types: List<KtReferenceType>
    fun load(type: KtReferenceType): ClassFile?
    fun metadata(type: KtReferenceType): KotlinClassMetadata?
    operator fun contains(type: KtReferenceType): Boolean
}