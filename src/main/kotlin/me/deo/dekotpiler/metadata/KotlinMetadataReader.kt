package me.deo.dekotpiler.metadata

import kotlinx.metadata.jvm.KotlinClassMetadata
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

typealias KotlinMetadataReader = KotlinClassMetadata.Companion