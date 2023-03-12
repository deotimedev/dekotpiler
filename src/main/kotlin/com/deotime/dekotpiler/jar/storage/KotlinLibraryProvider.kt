package com.deotime.dekotpiler.jar.storage

interface KotlinLibraryProvider {
    fun provide(): List<KotlinLibrary>
}