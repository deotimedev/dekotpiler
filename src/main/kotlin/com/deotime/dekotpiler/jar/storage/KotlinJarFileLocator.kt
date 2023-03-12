package com.deotime.dekotpiler.jar.storage

import java.io.File

interface KotlinJarFileLocator {
    fun locate(lib: KotlinLibrary): File?
}