package me.deo.dekotpiler.decompile

import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.model.type.KtReferenceType
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.state.DCCommonState

interface KotlinJar {
    val types: List<KtReferenceType>
    fun load(type: KtReferenceType): ClassFile?
    fun metadata(type: KtReferenceType): KotlinClassMetadata?
}