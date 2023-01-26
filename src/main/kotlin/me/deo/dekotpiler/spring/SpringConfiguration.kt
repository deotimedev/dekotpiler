package me.deo.dekotpiler.spring

import kotlinx.metadata.jvm.KotlinClassMetadata
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal open class SpringConfiguration {
    @Bean
    open fun kotlinMetadataReader() = KotlinClassMetadata.Companion
}