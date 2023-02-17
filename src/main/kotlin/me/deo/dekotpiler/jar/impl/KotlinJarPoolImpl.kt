package me.deo.dekotpiler.jar.impl

import me.deo.dekotpiler.jar.KotlinJar
import me.deo.dekotpiler.jar.KotlinJarPool
import me.deo.dekotpiler.model.type.KtReferenceType
import org.springframework.stereotype.Component

@Component
internal class KotlinJarPoolImpl : KotlinJarPool {

    private val jars = mutableSetOf<KotlinJar>()
    override fun locate(type: String) =
        jars.firstNotNullOfOrNull {
            it.type(type)
        }

    override fun containingJar(type: KtReferenceType) =
        jars.find { type in it }

    override fun register(jar: KotlinJar) {
        jars += jar
    }

    override fun standardLibrary(): KotlinJar {
        TODO("Not yet implemented")
    }

}