package com.deotime.dekotpiler.translation

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.dekotpiler.util.CFRExpression
import com.deotime.dekotpiler.util.CFRLValue
import com.deotime.dekotpiler.util.CFRStatement
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype
import org.benf.cfr.reader.bytecode.analysis.types.discovery.InferredJavaType
import kotlin.reflect.KClass

interface Translation {

    fun <C : Any, K> translators(type: KClass<out C>): List<Translator<C, K>>
    fun session(): Session

    interface Session {

        fun translateExpression(expression: CFRExpression): KtExpression
        fun translateStatement(statement: CFRStatement): KtStatement
        fun translateStatement(statement: Op04StructuredStatement): KtStatement
        fun translateBlock(statement: CFRStatement): KtBlockStatement
        fun translateBlock(statement: Op04StructuredStatement): KtBlockStatement
        fun <V : KtVariable> translateVariable(variable: CFRLValue): V
        fun translateType(type: JavaTypeInstance): KtType
        fun translateType(type: InferredJavaType) = translateType(type.javaTypeInstance)
        fun translateArrayType(type: JavaTypeInstance): KtType
        fun translateArrayType(type: InferredJavaType) = translateArrayType(type.javaTypeInstance)
        fun translateConditional(conditional: ConditionalExpression): KtConditional
        fun translateFunction(function: MethodPrototype): KtFunctionDescriptor

    }

}