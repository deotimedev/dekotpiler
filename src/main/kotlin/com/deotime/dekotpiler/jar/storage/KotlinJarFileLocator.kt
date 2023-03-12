package com.deotime.dekotpiler.jar.storage

import java.io.File

interface KotlinJarFileLocator {
    fun locate(group: String, artifact: String, version: String): File?
}