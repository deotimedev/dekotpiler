package me.deo.dekotpiler

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component

@Component
class Main(

) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println(Double.NaN.hashCode())
    }
}

fun main(args: Array<String>) {
    val context = SpringApplicationBuilder(App::class.java)
        .web(WebApplicationType.NONE)
        .bannerMode(Banner.Mode.OFF)
        .logStartupInfo(false)
        .run()
}

@SpringBootApplication
open class App