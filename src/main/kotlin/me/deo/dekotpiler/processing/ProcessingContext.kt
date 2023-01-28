package me.deo.dekotpiler.processing

import com.github.javaparser.ast.CompilationUnit

interface ProcessingContext {
    val context: CompilationUnit
}