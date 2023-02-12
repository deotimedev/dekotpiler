package me.deo.dekotpiler.model.function

import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.KtTypeParameter
import me.deo.dekotpiler.model.KtTyped
import java.lang.reflect.Method
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
    var kind: Kind
) {

    constructor(reflect: KFunction<*>) : this(
        reflect.name,
        reflect.signature,
        reflect.extensionReceiverParameter?.type?.let { KtType(it) },
        reflect.valueParameters.map { Parameter(it) }.toMutableList(),
        reflect.typeParameters.map { KtTypeParameter(it) }.toMutableList(),
        KtType(reflect.returnType),
        reflect.instanceParameter?.type?.let { KtType(it) },
        when {
            reflect.javaConstructor != null -> Kind.Constructor
            reflect.instanceParameter != null -> Kind.Instance
            else -> Kind.TopLevel
        }
    )

    enum class Kind {
        Instance,
        Constructor,
        TopLevel
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
        val IntArrayOf = String::codePointAt

        val KFunction<*>.signature get() = javaMethod?.toGenericString() as String
    }
}