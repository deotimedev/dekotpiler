package com.deotime.dekotpiler.jar

import com.deotime.dekotpiler.model.type.KtReferenceType
import org.benf.cfr.reader.entities.ClassFile

interface KotlinJarPool : KotlinClassContainer {
    fun standardLibrary(): KotlinClassContainer
    fun register(jar: KotlinClassContainer)
}