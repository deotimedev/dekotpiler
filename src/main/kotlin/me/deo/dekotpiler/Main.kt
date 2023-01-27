package me.deo.dekotpiler

import kotlinx.coroutines.runBlocking
import me.deo.dekotpiler.decompile.CfrDecompilerEngine
import me.deo.dekotpiler.decompile.DecompilerEngine
import me.deo.dekotpiler.file.FileSelector
import me.deo.dekotpiler.metadata.MetadataReader
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deotime.kpoetdsl.ExperimentalKotlinPoetDSL
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component
import java.io.File

@Component
class Main(
    private val metadataResolver: MetadataResolver,
    private val fileSelector: FileSelector,
    private val reader : MetadataReader,
    private val engines: List<DecompilerEngine>
) : CommandLineRunner {
    // This will eventually be replaced by a CLI
    @OptIn(ExperimentalKotlinPoetDSL::class)
    override fun run(vararg args: String): Unit = runBlocking {
        // using cfr for testing
        val cfr = engines.filterIsInstance<CfrDecompilerEngine>().first()

        // testing
        val file = File(File("").absolutePath, "/build/classes/kotlin/main/me/deo/dekotpiler/TestKt.class")
    }

    private companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplicationBuilder(App::class.java)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .headless(false)
                .run()
        }

        @SpringBootApplication
        class App

    }
}