package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRStatement
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import kotlin.reflect.KClass

interface Translation {
    fun <C : Any, K> translator(type: KClass<out C>): Translator<C, K>?

    fun translateExpression(expression: CFRExpression): KtExpression
    fun translateStatement(statement: CFRStatement): KtStatement
    fun translateStatement(statement: Op04StructuredStatement): KtStatement
    fun translateVariable(variable: LValue): KtVariable
    fun translateType(type: JavaTypeInstance): KtType
    fun translateConditional(conditional: ConditionalExpression): KtConditional

}