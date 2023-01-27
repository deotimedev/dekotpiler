package me.deo.dekotpiler.file

import java.io.File

interface FileSelector {
    suspend fun selectFile(
        prompt: String = "Select file",
        description: String = "All Files",
        vararg allowed: String = arrayOf("*.*")
    ): File?
}