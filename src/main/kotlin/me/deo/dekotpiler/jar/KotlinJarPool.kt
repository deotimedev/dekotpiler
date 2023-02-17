package me.deo.dekotpiler.jar

import me.deo.dekotpiler.model.type.KtReferenceType
import org.benf.cfr.reader.entities.ClassFile

interface KotlinJarPool {
    fun load(type: KtReferenceType): ClassFile?
    fun locate(type: String): KtReferenceType?
    fun locateJar(type: KtReferenceType): KotlinJar?
    fun standardLibrary(): KotlinJar

    fun register(jar: KotlinJar)
}