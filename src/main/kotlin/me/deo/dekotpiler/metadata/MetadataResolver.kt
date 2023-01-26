package me.deo.dekotpiler.metadata

import java.io.File

interface MetadataResolver {
    fun resolve(file: File): Metadata
}