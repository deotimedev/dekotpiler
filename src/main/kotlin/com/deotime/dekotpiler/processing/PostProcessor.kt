package com.deotime.dekotpiler.processing

interface PostProcessor<T : Any> : Processor<T> {
    override val mode get() = Processor.Mode.Post
}