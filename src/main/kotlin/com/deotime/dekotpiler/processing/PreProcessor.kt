package com.deotime.dekotpiler.processing

interface PreProcessor<T : Any> : Processor<T> {
    override val mode get() = Processor.Mode.Pre
}