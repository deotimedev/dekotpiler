package me.deo.dekotpiler.util.helper

import java.io.File

interface FileSelector {
    suspend fun selectFile(
        prompt: String = "Select file",
        description: String = "All Files",
        vararg allowed: String = arrayOf("*.*")
    ): File?
}