package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory
import org.springframework.stereotype.Component
import java.io.File

@Component
class CFRDecompilerEngine : DecompilerEngine {

    override val name = "CFR"

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
                    if (sinkType == OutputSinkFactory.SinkType.JAVA)
                        append(it)
                }
            })
            .build().analyse(listOf(file.absolutePath))
    }
}