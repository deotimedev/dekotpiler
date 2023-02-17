package com.deotime.dekotpiler.polish.impl

import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.mapping.TypeMappings
import com.deotime.dekotpiler.metadata.MetadataLocator
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.polish.FunctionPolisher
import kotlinx.metadata.KmType
import me.deotime.kpoetdsl.metadata.rawName
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance
import org.springframework.stereotype.Component

// todo this NEEDS to be removed at some point
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