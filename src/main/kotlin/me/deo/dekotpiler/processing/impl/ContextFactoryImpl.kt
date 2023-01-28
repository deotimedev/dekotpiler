package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.CompilationUnit
import me.deo.dekotpiler.processing.TranslationHelper
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class ContextFactoryImpl(
    private val translationHelper: TranslationHelper
) : Translator.Context.Factory {
    override fun create(context: CompilationUnit) =
        StandardVisitorContext(translationHelper, context)
}