package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.attribute.KtModifier
import com.deotime.dekotpiler.model.attribute.KtOperator
import com.deotime.dekotpiler.model.expressions.invoke.KtMethodInvoke
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import com.deotime.dekotpiler.util.isStringy
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.koin.core.annotation.Single
import java.util.concurrent.ConcurrentHashMap

@Single
class ArithmeticOperationExpressionTranslator :
    Translator<ArithmeticOperation, KtMethodInvoke> {

    override fun ArithmeticOperation.match() = !isStringy()

    context (Translation.Session)
    override fun translation(value: ArithmeticOperation) =
        KtMethodInvoke(
            translateArithmeticFunction(value),
            translateExpression(value.lhs),
            mutableListOf(translateExpression(value.rhs)),
        )


    companion object {

        private val ArithOp.operatorName
            get() = when (this) {
                ArithOp.MULTIPLY -> "times"
                else -> name.lowercase()
            }

        // todo make infix render right
        private val ArithOp.useInfix
            get() = when (this) {
                else -> false
            }

        private val ArithmeticMap = ConcurrentHashMap<String, KtFunction>()

        @OptIn(ExperimentalStdlibApi::class)
        private fun Translation.Session.translateArithmeticFunction(value: ArithmeticOperation) =
            ArithmeticMap.computeIfAbsent("${value.lhs.inferredJavaType.rawType}|${value.rhs.inferredJavaType.rawType}") {
                val lhsType = translateType(value.lhs.inferredJavaType).nullable(false)
                val rhsType = translateType(value.rhs.inferredJavaType).nullable(false)
                val opName = value.op.operatorName
                KtFunction(
                    KtFunctionDescriptor.Raw(
                        opName,
                        "**${lhsType.name}|${rhsType.name}",
                        lhsType,
                        lhsType as KtReferenceType,
                        listOf(rhsType)
                    ),
                    mutableListOf(
                        KtFunction.Parameter(
                            "other",
                            rhsType
                        )
                    ),
                    KtFunction.Kind.Instance,
                    null,
                    operator = KtOperator.entries.find { it.functionName == opName },
                    modifiers = if (value.op.useInfix) mutableListOf(KtModifier.Infix) else mutableListOf()
                )
            }
    }
}