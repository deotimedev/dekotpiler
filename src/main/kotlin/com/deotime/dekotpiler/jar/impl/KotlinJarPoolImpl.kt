package com.deotime.dekotpiler.jar.impl

import com.deotime.dekotpiler.jar.KotlinClassContainer
import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.model.type.KtReferenceType
import org.springframework.stereotype.Component

@Component
internal class KotlinJarPoolImpl : KotlinJarPool {

    private val jars = mutableSetOf<KotlinClassContainer>()

    override fun register(vararg jars: KotlinClassContainer) {
        this.jars += jars
    }

    override val types: List<KtReferenceType>
        get() = jars.flatMap { it.types }

    override fun type(name: String) =
        jars.firstNotNullOfOrNull { it.type(name) }

    override fun load(type: KtReferenceType) =
        jars.firstNotNullOfOrNull { it.load(type) }

    override fun metadata(type: KtReferenceType) =
        jars.firstNotNullOfOrNull { it.metadata(type) }

    override fun contains(type: KtReferenceType) =
        jars.any { type in it }

}