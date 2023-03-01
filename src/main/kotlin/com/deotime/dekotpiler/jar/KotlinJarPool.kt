package com.deotime.dekotpiler.jar

import com.deotime.dekotpiler.model.type.KtReferenceType
import org.benf.cfr.reader.entities.ClassFile

interface KotlinJarPool : KotlinJar {
    fun standardLibrary(): KotlinJar
    fun register(jar: KotlinJar)
}