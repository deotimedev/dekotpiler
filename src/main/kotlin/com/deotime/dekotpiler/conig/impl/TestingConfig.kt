package com.deotime.dekotpiler.conig.impl

import com.deotime.dekotpiler.conig.Config
import org.springframework.stereotype.Component

@Component
internal class TestingConfig : Config {
    override val explicitVariableDeclarationType = false
    override val typesDefaultNullable = false
}