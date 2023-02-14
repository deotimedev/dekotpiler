package me.deo.dekotpiler.processing.impl

import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.processing.Processing
import me.deo.dekotpiler.processing.Processor
import me.deo.dekotpiler.util.resolveTypeParameter
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