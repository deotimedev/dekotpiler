package me.deo.dekotpiler.spring

import com.github.javaparser.JavaParser
import kotlinx.metadata.jvm.KotlinClassMetadata
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class DekotSpringConfiguration {
    @Bean
    fun kotlinMetadataReader() = KotlinClassMetadata.Companion

    // Might need configuration here at some point
    @Bean
    fun javaParser() = JavaParser()
}