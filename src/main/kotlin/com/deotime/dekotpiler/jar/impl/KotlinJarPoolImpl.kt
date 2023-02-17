package com.deotime.dekotpiler.jar.impl

import com.deotime.dekotpiler.jar.KotlinJar
import com.deotime.dekotpiler.jar.KotlinJarPool
import com.deotime.dekotpiler.model.type.KtReferenceType
import org.springframework.stereotype.Component

@Component
internal class KotlinJarPoolImpl : KotlinJarPool {

    private val jars = mutableSetOf<KotlinJar>()
    override fun locate(type: String) =
        jars.firstNotNullOfOrNull {
            it.type(type)
        }

    override fun load(type: KtReferenceType) =
        jars.find { type in it }?.load(type)

    override fun locateJar(type: KtReferenceType) =
        jars.find { type in it }

    override fun register(jar: KotlinJar) {
        jars += jar
    }

    override fun standardLibrary(): KotlinJar {
        TODO("Not yet implemented")
    }

}