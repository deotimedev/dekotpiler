package com.deotime.dekotpiler.processing.impl

import com.deotime.dekotpiler.processing.PreProcessor
import com.deotime.dekotpiler.processing.Processing
import com.deotime.dekotpiler.processing.Processor
import com.deotime.dekotpiler.util.resolveTypeParameter
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single
internal class ProcessingImpl(
    processors: List<Processor<*>>,
) : Processing {
    private val processorsBytype = processors.groupBy { it.resolveTypeParameter<PreProcessor<*>>("T")!! }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> processors(mode: Processor.Mode, type: KClass<T>) =
        processorsBytype[type].orEmpty() as List<Processor<T>>
}