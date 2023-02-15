package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.expressions.KtArrayCreation
import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtMethodInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtSetterInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.util.update
import org.springframework.stereotype.Component

@Component
class ExtensionProcessor :
    PreProcessor<KtStaticInvoke> {

    override fun KtStaticInvoke.match() =
        method.enclosing?.name in StandardLibraryExtensionFiles
    override fun replace(value: KtStaticInvoke): KtExpression {

        StandardTopLevelFunctions[value.name]?.let { vararg ->
            value.method.kind = KtFunction.Kind.TopLevel
            value.method.parameters.last().vararg = vararg
            return value
        }

        return KtMethodInvoke(
            value.method,
            value.args.subList(1, value.args.size),
            value.args[0],
            extension = true
        )
    }


    companion object {

        // TODO: All of these will hopefully be replaced
        // when multi-file indexing can be done, but these
        // will have to be hardcoded for now

        // Files we know for sure contain extensions
        // OThers will need to be inferred
        val StandardLibraryExtensionFiles = listOf(
            "ArraysKt___ArraysKt",
            "CollectionsKt__CollectionsKt"
        )

        // temp
        val StandardTopLevelFunctions = mapOf(
            "listOf" to true
        )
    }
}