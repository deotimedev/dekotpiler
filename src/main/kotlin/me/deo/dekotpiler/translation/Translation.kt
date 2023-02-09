package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRStatement
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.discovery.InferredJavaType
import kotlin.reflect.KClass

interface Translation {

    fun <C : Any, K> translators(type: KClass<out C>): List<Translator<C, K>>

    fun translateExpression(expression: CFRExpression): KtExpression
    fun translateStatement(statement: CFRStatement): KtStatement
    fun translateStatement(statement: Op04StructuredStatement): KtStatement
    fun translateBlock(statement: CFRStatement): KtBlockStatement
    fun translateBlock(statement: Op04StructuredStatement): KtBlockStatement
    fun <V : KtVariable> translateVariable(variable: LValue): V
    fun translateType(type: JavaTypeInstance): KtType
    fun translateType(type: InferredJavaType) = translateType(type.javaTypeInstance)
    fun translateConditional(conditional: ConditionalExpression): KtConditional

}