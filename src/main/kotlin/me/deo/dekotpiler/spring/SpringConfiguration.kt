package me.deo.dekotpiler.spring

import kotlinx.metadata.jvm.KotlinClassMetadata
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class SpringConfiguration {
    @Bean
    fun kotlinMetadataReader() = KotlinClassMetadata.Companion
}