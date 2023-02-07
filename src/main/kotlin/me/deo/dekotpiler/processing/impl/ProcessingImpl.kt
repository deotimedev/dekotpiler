package me.deo.dekotpiler.processing.impl

import me.deo.dekotpiler.processing.Processing
import me.deo.dekotpiler.processing.Processor
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class ProcessingImpl(
    processors: List<Processor<*>>
) : Processing {
    private val groupedProcessors = processors.groupBy { it.mode }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> processors(mode: Processor.Mode, type: KClass<T>) =
        groupedProcessors[mode].orEmpty().filter { type.java.isAssignableFrom(it.clazz) } as List<Processor<T>>
}