package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.buildCode
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype
import org.benf.cfr.reader.entities.constantpool.ConstantPoolEntryMethodRef

// Not to be confused with a companion object invoke
// Can this also be used as a constructor?
data class KtStaticInvoke(
    var enclosingType: KtType,
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(enclosingType.nullable(false).simpleName, ".", method.name)
        braced { +args.joinToString { it.code().toString() } }
    }
}