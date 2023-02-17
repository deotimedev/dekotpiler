package com.deotime.dekotpiler.jar

import java.io.File

interface KotlinJarLoader {
    fun load(jar: File): KotlinJar
}