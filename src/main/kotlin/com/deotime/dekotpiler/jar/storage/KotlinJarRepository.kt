package com.deotime.dekotpiler.jar.storage

import java.net.URI

interface KotlinJarRepository {
    val location: URI
    fun groupDirectory(group: String): String
}