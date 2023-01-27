import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.spring") version "1.8.0"
    id("org.openjfx.javafxplugin") version "0.0.9"
    application
}

group = "me.deo.dekotpiler"
version = "1.0.0"

repositories {
    mavenCentral()

    maven {
        name = "kotlin-poet-dsl"
        url = uri("https://repo.q64.io/deotime")
        content {
            includeGroup("me.deotime")
        }
    }
}

javafx {
    version = "19"
    modules = listOf("javafx.controls")
}

dependencies {
    // cli
    implementation("com.github.ajalt.clikt:clikt:3.5.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // spring
    implementation("org.springframework.boot:spring-boot-starter")

    // cfr
    implementation("org.benf:cfr:0.152")

    // asm
    implementation("org.ow2.asm:asm:9.4")

    // metadata
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.6.0")

    // teting
    testImplementation(kotlin("test"))

    // ui (temp)
    implementation("org.openjfx:javafx-base:19")

    // code
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("me.deotime:kotlin-poet-dsl-dsl:2.0.0")
    implementation("me.deotime:kotlin-poet-dsl-metadata:2.0.0")
    implementation("com.github.javaparser:javaparser-core:3.24.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xskip-prerelease-check")
}

application {
    mainClass.set("MainKt")
}