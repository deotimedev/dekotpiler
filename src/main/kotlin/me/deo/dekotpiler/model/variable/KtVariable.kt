package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.model.KtExpression
import org.benf.cfr.reader.bytecode.analysis.parse.LValue

interface KtVariable : KtExpression {

    val delegate: LValue // TODO remove
    var name: String
    var final: Boolean

}

//override fun code() = buildCode {
//    if (delegate is AbstractFieldVariable) {
//        write(
//            (delegate.owningClassType as? JavaRefTypeInstance)?.rawShortName
//            , ".", name)
//    } else {
//        write(name)
//    }
//}