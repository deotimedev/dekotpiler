package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.body.Parameter
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import org.springframework.stereotype.Component

@Component
class ParameterTranslator : Translator<Parameter> {
    override fun Translator.Context.translate(value: Parameter) = codeWriter {
        if (value.isVarArgs) write("vararg ")
        write("${value.nameAsString}: ${value.typeAsString}")
    }
}