package me.deo.dekotpiler.processing

interface PreProcessor<T> : Processor<T> {
    override val mode get() = Processor.Mode.Pre
}