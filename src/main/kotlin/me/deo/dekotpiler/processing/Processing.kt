package me.deo.dekotpiler.processing

import kotlin.reflect.KClass

interface Processing {

    fun <T : Any> processors(mode: Processor.Mode, type: KClass<T>): List<Processor<T>>

    companion object {
        inline fun <reified T : Any> Processing.processors(mode: Processor.Mode) = processors(mode, T::class)
    }

}