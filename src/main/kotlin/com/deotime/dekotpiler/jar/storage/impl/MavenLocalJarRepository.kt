package com.deotime.dekotpiler.jar.storage.impl

import com.deotime.dekotpiler.jar.storage.KotlinJarRepository
import org.koin.core.annotation.Single
import java.io.File
import java.net.URI

// TODO this only accounts for windows atm
@Single
internal class MavenLocalJarRepository : KotlinJarRepository {
    override val location: URI
        get() = File("${System.getenv("userprofile")}\\.m2").toURI()

    override fun groupDirectory(group: String) = group.replace(".", "/")
}