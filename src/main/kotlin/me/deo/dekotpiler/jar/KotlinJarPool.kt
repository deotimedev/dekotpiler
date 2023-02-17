package me.deo.dekotpiler.jar

import me.deo.dekotpiler.model.type.KtReferenceType

interface KotlinJarPool {
    fun locate(type: String): KtReferenceType?
    fun containingJar(type: KtReferenceType): KotlinJar?
    fun standardLibrary(): KotlinJar

    fun register(jar: KotlinJar)
}