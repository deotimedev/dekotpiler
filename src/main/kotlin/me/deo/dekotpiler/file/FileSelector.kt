package me.deo.dekotpiler.file

import java.io.File

interface FileSelector {
    suspend fun selectFile(
        prompt: String = "Select file",
        files: Boolean = true,
        directories: Boolean = true,
        filter: (File?) -> Boolean = { true }
    ): File?
}