package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.buildCode
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.AbstractFieldVariable
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance

data class KtVariable(
    val delegate: LValue, // TODO remove
    var name: String,
    var final: Boolean,
    override var type: KtType
) : KtExpression {
    override fun code() = buildCode {
        if (delegate is AbstractFieldVariable) {
            write(
                (delegate.owningClassType as? JavaRefTypeInstance)?.rawShortName
                , ".", name)
        } else {
            write(name)
        }
    }
}
