package com.deotime.dekotpiler.jar.storage

import com.deotime.dekotpiler.jar.KotlinClassContainer
import com.deotime.dekotpiler.util.CollectionAssembler
import com.deotime.dekotpiler.util.assemble

data class KotlinLibrary(
    val group: String,
    val artifact: String,
    val version: String
)

fun libraries(group: String, version: String, artifacts: CollectionAssembler<String>) =
    artifacts.assemble().map { KotlinLibrary(group, it, version) }
