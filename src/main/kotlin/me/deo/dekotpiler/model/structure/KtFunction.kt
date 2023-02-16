package me.deo.dekotpiler.model.structure

import com.squareup.kotlinpoet.KModifier
import kotlinx.metadata.Flag
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.model.KtTypeParameter
import me.deo.dekotpiler.model.expressions.invoke.KtMethodInvoke
import me.deo.dekotpiler.model.type.KtTyped
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.javaMethod

data class KtFunction(
    var name: String,
    var signature: String,
    var receiver: KtType?,
    val parameters: MutableList<Parameter>,
    val typeParameters: MutableList<KtTypeParameter>,
    var returns: KtType,
    var enclosing: KtType?, // null if top level function
    var kind: Kind,
) {

    var operator: Operator? = null
        get() = field ?: Operator.All.find { it.functionName == name }

    // i dont think this is needed anymore
//    constructor(reflect: KFunction<*>) : this(
//        reflect.name,
//        reflect.signature,
//        reflect.extensionReceiverParameter?.type?.let { KtType(it) },
//        reflect.valueParameters.map { Parameter(it) }.toMutableList(),
//        reflect.typeParameters.map { KtTypeParameter(it) }.toMutableList(),
//        KtType(reflect.returnType),
//        reflect.instanceParameter?.type?.let { KtType(it) },
//        when {
//            reflect.javaConstructor != null -> Kind.Constructor
//            reflect.instanceParameter != null -> Kind.Instance
//            else -> Kind.TopLevel
//        },
//        isOperator = reflect.isOperator
//    )

    enum class Kind {
        Instance,
        Constructor,
        TopLevel,
        JavaStatic
    }

    enum class Operator(val format: String) {
        UnaryPlus("+@"),
        UnaryMinus("-@"),
        Not("!@"),
        Inc("@++"),
        Dec("@--"),
        Plus(arith('+')),
        Minus(arith('-')),
        Times(arith('*')),
        Div(arith('/')),
        Rem(arith('%')),
        PlusAssign(augment('+')),
        MinusAssign(augment('-')),
        TimesAssign(augment('*')),
        DivAssign(augment('/')),
        RemAssign(augment('%')),
        RangeTo("@..#1"),
        Contains("#1 in @"),
        Get("@[!MID!]"),
        Set("@[!MID!] = !LAST!"),
        Invoke("@(!MID!, !LAST!)"), // TODO lambda
        Equals("@ == #1"),
        // TODO: delegators and compareTo
        ;

        val functionName = name.replaceFirstChar { it.lowercase() }

        companion object {
            val All by lazy { values().toSet() }
        }
    }


    // TODO modifiers
    data class Parameter(
        var name: String?,
        override var type: KtType,
        var vararg: Boolean = false
    ) : KtTyped {
        constructor(reflect: KParameter) : this(
            reflect.name!!,
            KtType(reflect.type),
            reflect.isVararg
        )
    }

    companion object {
        // kotlin enums initialize very strange
        private fun arith(char: Char) = "@ $char #1"
        private fun augment(char: Char) = "@ $char= #1"

        @Suppress("NOTHING_TO_INLINE")
        private inline fun <A> resolve(func: KFunction<A>) = func

        val KFunction<*>.signature get() = javaMethod?.toGenericString() as String
    }
}
