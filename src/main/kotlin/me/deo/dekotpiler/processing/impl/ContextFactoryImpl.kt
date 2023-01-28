package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.CompilationUnit
import me.deo.dekotpiler.processing.Visitor
import org.springframework.stereotype.Component

@Component
class ContextFactoryImpl : Visitor.Context.Factory {
    override fun create(context: CompilationUnit) = StandardVisitorContext(context)
}