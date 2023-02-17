package me.deo.dekotpiler.jar

import me.deo.dekotpiler.model.type.KtReferenceType

// todo think of better name for this
// also design for this is kind of wonky... in order to get metadata for a type you would need to do:
// jarPool.find(type)?.metadata(type) which is kind of odd
interface KotlinJarPool {
    fun locate(type: KtReferenceType): KotlinJar?
    fun register(jar: KotlinJar)

    fun standardLibrary(): KotlinJar
}