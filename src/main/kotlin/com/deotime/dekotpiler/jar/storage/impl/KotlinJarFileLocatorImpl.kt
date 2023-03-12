package com.deotime.dekotpiler.jar.storage.impl

import com.deotime.dekotpiler.jar.storage.KotlinJarFileLocator
import com.deotime.dekotpiler.jar.storage.KotlinJarRepository
import com.deotime.dekotpiler.jar.storage.KotlinLibrary
import org.springframework.stereotype.Component
import java.io.File
import java.net.URL
import kotlin.io.path.toPath

@Component
internal class KotlinJarFileLocatorImpl(
    private val repos: List<KotlinJarRepository>
) : KotlinJarFileLocator {
    override fun locate(lib: KotlinLibrary): File? = repos.firstNotNullOfOrNull { repo ->
        val ver = lib.version
        val fileExpectedName = "${lib.artifact}-$ver"
        val groupLocation = repo.location
            .resolve("${repo.groupDirectory(lib.group)}/$ver/$fileExpectedName")
        groupLocation.toPath().toFile().takeIf { it.exists() }
    }
}