package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.state.DCCommonState
import java.io.File

interface CFRDecompilerEngine {
    fun decompile(file: File): DCCommonState
}