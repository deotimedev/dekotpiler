package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable

data class KtMemberField(
    var member: KtExpression,
    override var name: String,
    override var final: Boolean,
    override val type: KtType,
    override var synthetic: Boolean
) : KtField {
    override fun code() = buildCode {
        write(member, member.nullCheckedChain(), name)
    }
}