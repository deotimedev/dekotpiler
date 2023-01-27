package me.deo.dekotpiler.decompile

import java.io.File

interface DecompilerEngine {
    val name: String
    fun decompile(file: File): String
}