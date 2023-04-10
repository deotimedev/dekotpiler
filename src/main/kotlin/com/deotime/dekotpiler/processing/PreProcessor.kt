package com.deotime.dekotpiler.processing

import org.koin.core.annotation.Single

interface PreProcessor<T : Any> : Processor<T> {
    override val mode get() = Processor.Mode.Pre
}