package me.deo.dekotpiler.decompile

import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory
import org.benf.cfr.reader.util.getopt.Options
import org.benf.cfr.reader.util.getopt.OptionsImpl
import org.springframework.stereotype.Component
import java.io.File

import org.benf.cfr.reader.util.getopt.OptionsImpl.*
import org.benf.cfr.reader.util.getopt.PermittedOptionProvider
import kotlin.jvm.optionals.getOrNull

@Component
class CFRDecompilerEngine : DecompilerEngine {

    override val name = "CFR"

    private val config = cfrConfig {
        ANTI_OBF set false
    }
    override fun decompile(file: File) = CfrDriver.Builder()
        .withOptions(config)
        .build()
        .analyse(file.absolutePath).getOrNull()

    private fun cfrConfig(closure: CFRConfig.() -> Unit) =
        CFRConfig().apply(closure).options

    private class CFRConfig {
        val options = mutableMapOf<String, String>()

        infix fun <T> PermittedOptionProvider.Argument<T>.set(value: T) {
            options.put(name, value.toString())
        }
    }
}