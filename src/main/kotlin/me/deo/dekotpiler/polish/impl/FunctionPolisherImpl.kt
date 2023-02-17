package me.deo.dekotpiler.polish.impl

import kotlinx.metadata.Flag
import kotlinx.metadata.KmType
import me.deo.dekotpiler.jar.KotlinJarPool
import me.deo.dekotpiler.metadata.MetadataLocator
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.polish.FunctionPolisher
import me.deo.dekotpiler.translation.Translation
import me.deotime.kpoetdsl.metadata.rawName
import org.springframework.stereotype.Component

@Component
internal class FunctionPolisherImpl(
    private val metadataLocator: MetadataLocator,
    private val jarPool: KotlinJarPool
) : FunctionPolisher {
    private val polished = hashSetOf<KtFunction>()
    override fun polish(func: KtFunction) {
        metadataLocator.function(func.enclosing, func.name, func.descriptor)?.let { meta ->
            fun KmType.toKtType() = jarPool.locate(rawName)
            if (polished.add(func)) {
                func.receiver = meta.receiverParameterType?.toKtType()
                func.parameters.zip(meta.valueParameters).forEach { (kt, km) ->
                    kt.name = km.name
                }
            }
        }

    }
}