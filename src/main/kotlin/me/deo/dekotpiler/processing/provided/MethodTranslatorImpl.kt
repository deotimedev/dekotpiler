package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.body.MethodDeclaration
import me.deo.dekotpiler.processing.MethodTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class MethodTranslatorImpl : MethodTranslator {
    override fun Translator.Context.translate(value: MethodDeclaration) = translateStatement(value.body.get())
}