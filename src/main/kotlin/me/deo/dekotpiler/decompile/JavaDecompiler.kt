package me.deo.dekotpiler.decompile

import java.io.File

interface JavaDecompiler {
    fun decompile(file: File): String
}