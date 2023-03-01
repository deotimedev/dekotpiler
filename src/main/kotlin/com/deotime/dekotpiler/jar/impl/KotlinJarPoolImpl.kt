package com.deotime.dekotpiler.jar.impl

import com.deotime.dekotpiler.jar.KotlinClassContainer
import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.model.type.KtReferenceType
import kotlinx.metadata.jvm.KotlinClassMetadata
import org.benf.cfr.reader.entities.ClassFile
import org.springframework.stereotype.Component

@Component
internal class KotlinJarPoolImpl : KotlinJarPool {

    private val jars = mutableSetOf<KotlinClassContainer>()
    override fun standardLibrary(): KotlinClassContainer {
        TODO("Not yet implemented")
    }

    override fun register(jar: KotlinClassContainer) {
        jars += jar
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