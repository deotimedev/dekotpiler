package me.deo.dekotpiler.mapping

import me.deo.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

interface TypeMappings {
    fun mapping(java: JavaTypeInstance): KtType?
}