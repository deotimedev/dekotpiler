package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable

data class KtMemberField(
    override val delegate: FieldVariable,
    var member: KtExpression,
    override var name: String,
    override var final: Boolean,
    override val type: KtType
) : KtField {
    override fun code() = buildCode {
        write(member, ".", name)
    }
}