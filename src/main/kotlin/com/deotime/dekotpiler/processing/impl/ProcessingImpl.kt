package com.deotime.dekotpiler.processing.impl

import com.deotime.dekotpiler.processing.PreProcessor
import com.deotime.dekotpiler.processing.Processing
import com.deotime.dekotpiler.processing.Processor
import com.deotime.dekotpiler.util.resolveTypeParameter
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class ProcessingImpl(
    processors: List<Processor<*>>
) : Processing {
    private val processorsBytype = processors.groupBy { resolveTypeParameter(it::class, PreProcessor::class, "T")!! }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> processors(mode: Processor.Mode, type: KClass<T>) =
        processorsBytype[type].orEmpty() as List<Processor<T>>
}