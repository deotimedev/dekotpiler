package me.deo.dekotpiler.model.variable

import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.AbstractFieldVariable

interface KtField : KtVariable {
    override val delegate: AbstractFieldVariable
}