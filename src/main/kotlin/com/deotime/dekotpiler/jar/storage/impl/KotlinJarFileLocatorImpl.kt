package com.deotime.dekotpiler.jar.storage.impl

import com.deotime.dekotpiler.jar.storage.KotlinJarFileLocator
import com.deotime.dekotpiler.jar.storage.KotlinJarRepository
import org.springframework.stereotype.Component
import java.io.File
import java.net.URL
import kotlin.io.path.toPath

@Component
internal class KotlinJarFileLocatorImpl(
    private val repos: List<KotlinJarRepository>
) : KotlinJarFileLocator {
    override fun locate(group: String, artifact: String, version: String): File? = repos.firstNotNullOfOrNull { repo ->
        val fileExpectedName = "$artifact-$version"
        val groupLocation = repo.location
            .resolve("${repo.groupDirectory(group)}/$version/$fileExpectedName")
        groupLocation.toPath().toFile().takeIf { it.exists() }
    }
}