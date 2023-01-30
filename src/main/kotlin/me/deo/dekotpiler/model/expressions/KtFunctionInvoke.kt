package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

// will completely refactor when not feeling lazy
data class KtFunctionInvoke(
    var method: MethodPrototype, // TODO REMOVE
    var reference: KtExpression?,
    val args: MutableList<KtExpression>,
    var isExtension: Boolean = false,
    var isConstructor: Boolean = false,
    var staticType: KtType? = null // abstraction? never heard of it
) : KtExpression, KtStatement {
    override val type: KtType get() = KtType(method.returnType) // TODO REMOVE

    var getter = method.name.startsWith("get") // todo replace with regex lol
    var setter = method.name.startsWith("set") // todo replace with regex lol

    // please fix i am tired and writing spagetheri code
    val getterSetterName get() = method.name.drop(3).decapitalize()

    override fun writeCode() = codeWriter {
        // todo operator function mappings
        write(reference, ".",
            if (getter || setter) getterSetterName else method.name
        )
        if (setter) {
            write(" = ", args[0])
        }
        if (!getter && !setter) braced(args.map { it.writeCode() }.joinToString())
    }
}