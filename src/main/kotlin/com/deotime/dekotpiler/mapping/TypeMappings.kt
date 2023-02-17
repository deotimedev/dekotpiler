package com.deotime.dekotpiler.mapping

import com.deotime.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

interface TypeMappings {
    fun mapping(java: JavaTypeInstance): KtType?
}