package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.entities.ClassFile
import java.io.File

interface DecompilerEngine {
    val name: String
    fun decompile(file: File): ClassFile?
}