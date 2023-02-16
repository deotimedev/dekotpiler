package me.deo.dekotpiler.decompile

import java.io.File

interface KotlinJarLoader {
    fun load(jar: File): KotlinJar
}