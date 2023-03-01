package com.deotime.dekotpiler.jar

interface KotlinJarPool : KotlinClassContainer {
    fun standardLibrary(): KotlinClassContainer
    fun register(jar: KotlinClassContainer)
}