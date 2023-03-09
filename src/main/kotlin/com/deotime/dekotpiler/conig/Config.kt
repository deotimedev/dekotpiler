package com.deotime.dekotpiler.conig

import com.deotime.dekotpiler.util.resolve

interface Config {
    val explicitVariableDeclarationType: Boolean
    val typesDefaultNullable: Boolean

    // use only when neccessary
    companion object : Config by _config
}

private val _config: Config by resolve()