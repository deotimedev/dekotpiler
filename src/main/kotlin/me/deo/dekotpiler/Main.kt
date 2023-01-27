package me.deo.dekotpiler

import com.github.javaparser.JavaParser
import com.github.javaparser.utils.SourceRoot
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.decompile.JavaDecompiler
import me.deo.dekotpiler.file.FileSelector
import me.deo.dekotpiler.metadata.MetadataReader
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deotime.kpoetdsl.ExperimentalKotlinPoetDSL
import me.deotime.kpoetdsl.FunctionBuilder.Initializer.invoke
import me.deotime.kpoetdsl.kotlin
import me.deotime.kpoetdsl.metadata.toSpec
import org.benf.cfr.reader.CfrDriverImpl
import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory
import org.benf.cfr.reader.api.SinkReturns.Token
import org.benf.cfr.reader.api.SinkReturns.TokenType
import org.benf.cfr.reader.util.output.DumperFactory
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
    private val javaDecompiler: JavaDecompiler
) : CommandLineRunner {
    // This will eventually be replaced by command argument processor
    @OptIn(ExperimentalKotlinPoetDSL::class)
    override fun run(vararg args: String): Unit = runBlocking {
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