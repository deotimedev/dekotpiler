package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.body.Parameter
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class ParameterTranslator : Translator<Parameter> {
    override fun Translator.Context.translate(value: Parameter) = buildString {
        if (value.isVarArgs) append("vararg ")
        append("${value.nameAsString}: ${value.typeAsString}")
    }
}