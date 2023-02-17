package com.deotime.dekotpiler.metadata

interface MetadataResolver {
    fun resolve(clazz: ByteArray): Metadata
}