package me.deo.dekotpiler.polish.impl

import kotlinx.metadata.KmType
import me.deo.dekotpiler.jar.KotlinJarPool
import me.deo.dekotpiler.mapping.TypeMappings
import me.deo.dekotpiler.metadata.MetadataLocator
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.model.type.KtReferenceType
import me.deo.dekotpiler.polish.FunctionPolisher
import me.deotime.kpoetdsl.metadata.rawName
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance
import org.springframework.stereotype.Component

@Component
internal class FunctionPolisherImpl(
    private val metadataLocator: MetadataLocator,
    private val jarPool: KotlinJarPool,
    private val typeMappings: TypeMappings
) : FunctionPolisher {
    private val polished = hashSetOf<KtFunction>()
    override fun polish(func: KtFunction) {
        metadataLocator.function(func.enclosing, func.name, func.descriptor)?.let { meta ->
            fun KmType.toKtType() =
                (typeMappings.mapping(JavaRefTypeInstance.createTypeConstant(rawName)) ?: jarPool.locate(rawName))
                    ?: KtReferenceType(rawName)
            if (polished.add(func)) {
                val ktType = meta.receiverParameterType?.toKtType()
                func.receiver = ktType
                func.parameters.zip(meta.valueParameters).forEach { (kt, km) ->
                    kt.name = km.name
                }
            }
        }

    }
}