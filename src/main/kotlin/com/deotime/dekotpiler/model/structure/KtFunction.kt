package com.deotime.dekotpiler.model.structure

import com.deotime.dekotpiler.model.KtTypeParameter
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtTyped
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.javaMethod

// i dont really like this implementation and dont think it will work long term
// since kotlin generates multiple synthetics and it doesnt make sense to have
// a ktfunction for all of them
// ideally instead of doing ktfunctionpolishing we have some immutable instance of
// the actual function (amy be synthetic) which acts as the backend of this, and then
// polishers an processors can make adjustments to ktfunction (the frontend) to make it
// look accurte since synthetics will never be dispalyed
class KtFunction(
    var name: String,
    var descriptor: String,
    var receiver: KtType?,
    val parameters: MutableList<Parameter>,
    val typeParameters: MutableList<KtTypeParameter>,
    var returns: KtType,
    var enclosing: KtReferenceType,
    var kind: Kind,
    // will this technically not work with multiple vararg parameters? yes. is anyone making
    // functions with multiple vararg parameters? no (unless you are kotlinpoet for some reason)
    var vararg: Boolean = false
) {

    var operator: Operator? = null
        get() = field ?: Operator.All.find { it.functionName == name }

    constructor(reflect: KFunction<*>) : this(
        reflect.name,
        reflect.signature,
        reflect.extensionReceiverParameter?.type?.let { KtType(it) },
        reflect.valueParameters.map { Parameter(it) }.toMutableList(),
        reflect.typeParameters.map { KtTypeParameter(it) }.toMutableList(),
        KtType(reflect.returnType),
        reflect.instanceParameter?.type?.let {
            KtType(it)
        } ?: KtType.invoke(reflect.javaMethod!!.declaringClass.kotlin),
        when {
            reflect.javaConstructor != null -> Kind.Constructor
            reflect.instanceParameter != null -> Kind.Instance
            else -> Kind.TopLevel
        }
    )

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

        val Equals = KtFunction(Any::equals)

        // kotlin enums initialize very strange
        private fun arith(char: Char) = "@ $char #1"
        private fun augment(char: Char) = "@ $char= #1"

        @Suppress("NOTHING_TO_INLINE")
        private inline fun <A> resolve(func: KFunction<A>) = func

        val KFunction<*>.signature get() = javaMethod?.toGenericString() as String
    }
}
