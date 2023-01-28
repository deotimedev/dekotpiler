package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.body.MethodDeclaration
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class MethodTranslator : Translator<MethodDeclaration> {
    override fun Translator.Context.translate(value: MethodDeclaration) = translateStatement(value.body.get())
}