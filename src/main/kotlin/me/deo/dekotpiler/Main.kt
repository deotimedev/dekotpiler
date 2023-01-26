package me.deo.dekotpiler

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
    private val metadataResolver: MetadataResolver
) : CommandLineRunner {
    override fun run(vararg args: String) {
        val file = File(File("").absolutePath, "/build/classes/kotlin/main/me/deo/dekotpiler/TestKt.class")
        val meta = metadataResolver.resolve(file)
        println(meta)
    }

    private companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplicationBuilder(App::class.java)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .run()
        }

        @SpringBootApplication
        open class App

    }
}