package com.deotime.dekotpiler.jar.storage.impl

import com.deotime.dekotpiler.jar.storage.KotlinLibraryProvider
import com.deotime.dekotpiler.jar.storage.libraries
import org.koin.core.annotation.Single

@Single
class KotlinStandardLibrary : KotlinLibraryProvider {


    override fun provide() = libraries("org.jetbrains.kotlin", "$Version") {
        +"kotlin-stdlib"
        +"kotlin-stdlib-common"
    }

    companion object {
        // TODO setup through config
        val Version = KotlinVersion(1, 7, 10)
    }
}