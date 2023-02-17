package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.model.expressions.invoke.KtMethodInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ExtensionProcessor(
    private val jarPool: KotlinJarPool
) :
    PreProcessor<KtStaticInvoke> {

    override fun KtStaticInvoke.match() =
        (function.receiver != null)



    override fun replace(value: KtStaticInvoke) =
        KtMethodInvoke(
            value.function,
            value.args[0],
            value.args.subList(1, value.args.size),
            extension = true
        )


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