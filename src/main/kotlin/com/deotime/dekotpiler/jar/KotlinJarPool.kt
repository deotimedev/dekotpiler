package com.deotime.dekotpiler.jar

interface KotlinJarPool : KotlinClassContainer {
    fun register(vararg jars: KotlinClassContainer)
}