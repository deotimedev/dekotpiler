package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRVariable
import me.deo.dekotpiler.util.CFRStatement
import kotlin.reflect.KClass

interface Translation {
    fun <C : Any, K> translator(type: KClass<out C>): Translator<C, K>?

    fun translateExpression(expression: CFRExpression): KtExpression
    fun translateStatement(statement: CFRStatement): KtStatement
    fun translateVariable(variable: CFRVariable): KtVariable
}