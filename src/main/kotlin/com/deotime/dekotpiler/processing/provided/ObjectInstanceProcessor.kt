package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.metadata.MetadataLocator
import com.deotime.dekotpiler.model.expressions.KtObjectInstance
import com.deotime.dekotpiler.model.variable.KtStaticField
import com.deotime.dekotpiler.processing.PreProcessor
import kotlinx.metadata.Flag
import kotlinx.metadata.jvm.KotlinClassMetadata
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor(
    private val jarPool: KotlinJarPool
) : PreProcessor<KtStaticField> {

    // todo fixxers
    override fun KtStaticField.match() =
        (name == "INSTANCE" || name == "Companion") && ((jarPool.metadata(declaring) as? KotlinClassMetadata.Class)
            ?.toKmClass()?.flags?.let { flags ->
                println(flags)
                Flag.Class.IS_OBJECT(flags) || Flag.Class.IS_COMPANION_OBJECT(flags)
            } == true)

    // this is broken and needs to be a postprocessor.
    override fun replace(value: KtStaticField): KtObjectInstance {
        value.type = value.type.nullable(false)
        return KtObjectInstance(value)
    }

}