package me.deo.dekotpiler.processing

interface PostProcessor<T> : Processor<T> {
    override val mode get() = Processor.Mode.Post
}