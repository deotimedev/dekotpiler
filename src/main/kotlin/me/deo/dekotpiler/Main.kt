package me.deo.dekotpiler

import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.file.FileSelector
import me.deo.dekotpiler.metadata.KotlinMetadataReader
import me.deo.dekotpiler.metadata.MetadataResolver
import org.springframework.boot.ApplicationRunner
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
    private val reader : KotlinMetadataReader
) : CommandLineRunner {
    // This will eventually be replaced by command argument processor
    override fun run(vararg args: String): Unit = runBlocking {
        val file = fileSelector.selectFile() ?: return@runBlocking
        val meta = metadataResolver.resolve(file)
        val converted = reader.read(meta) ?: return@runBlocking
        println(converted::class.java)
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