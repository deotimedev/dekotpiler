package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory
import org.springframework.stereotype.Component
import java.io.File

@Component
class JavaDecompilerImpl : JavaDecompiler {
    override fun decompile(file: File) = buildString {
        CfrDriver.Builder()
            .withOutputSink(object : OutputSinkFactory {
                override fun getSupportedSinks(
                    sinkType: OutputSinkFactory.SinkType?,
                    available: MutableCollection<OutputSinkFactory.SinkClass>?
                ) = listOf(OutputSinkFactory.SinkClass.STRING)

                override fun <T : Any?> getSink(
                    sinkType: OutputSinkFactory.SinkType?,
                    sinkClass: OutputSinkFactory.SinkClass?
                ) = OutputSinkFactory.Sink<T> {
                    append(it)
                }
            })
            .build().analyse(listOf(file.absolutePath))
    }
}