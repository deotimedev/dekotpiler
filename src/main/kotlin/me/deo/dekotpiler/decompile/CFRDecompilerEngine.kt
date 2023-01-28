package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory
import org.benf.cfr.reader.util.getopt.Options
import org.benf.cfr.reader.util.getopt.OptionsImpl
import org.springframework.stereotype.Component
import java.io.File

import org.benf.cfr.reader.util.getopt.OptionsImpl.*
import org.benf.cfr.reader.util.getopt.PermittedOptionProvider

@Component
class CFRDecompilerEngine : DecompilerEngine {

    override val name = "CFR"

    override fun decompile(file: File) = buildString {
        val config = cfrConfig {
            ANTI_OBF set false
        }
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
            .withOptions(config)
            .build()
            .analyse(listOf(file.absolutePath))
    }

    private fun cfrConfig(closure: CFRConfig.() -> Unit) =
        CFRConfig().apply(closure).options

    private class CFRConfig {
        val options = mutableMapOf<String, String>()

        infix fun <T> PermittedOptionProvider.Argument<T>.set(value: T) {
            options.put(name, value.toString())
        }
    }
}