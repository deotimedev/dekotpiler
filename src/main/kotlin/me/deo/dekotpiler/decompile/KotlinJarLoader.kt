package me.deo.dekotpiler.decompile

import java.io.File

interface KotlinJarLoader {
    fun decompile(file: File): KotlinJar
}