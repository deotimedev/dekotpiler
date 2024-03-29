import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("com.google.devtools.ksp") version "1.8.20-1.0.10"
    application
}

group = "com.deotime.dekotpiler"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://repo.q64.io/deotime")
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

    // cfr
    implementation("org.benf:cfr-kt:0.153-SNAPSHOT")

    // asm
    implementation("org.ow2.asm:asm:9.4")

    // metadata
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.6.0")

    // ui (temp??)
    implementation("org.openjfx:javafx-base:19")

    // kpoet
    implementation("com.squareup:kotlinpoet:1.12.0")

    // reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20-Beta")

    // vision
    implementation("com.deotime:vision:1.0.5")

    // kpoetdsl
    val kotlinPoetDslVersion = "2.0.6"
    implementation("me.deotime:kotlin-poet-dsl-dsl:$kotlinPoetDslVersion")
    implementation("me.deotime:kotlin-poet-dsl-metadata:$kotlinPoetDslVersion")

    // di
    implementation("io.insert-koin:koin-core:3.4.0")
    implementation("io.insert-koin:koin-annotations:1.2.0")
    ksp("io.insert-koin:koin-ksp-compiler:1.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.languageVersion = "1.9"
    kotlinOptions.freeCompilerArgs = listOf(
        "-Xskip-prerelease-check",
//        "-Xuse-k2",
        "-Xuse-experimental",
        "-XXLanguage:+EnumEntries",
        "-Xcontext-receivers"
    )
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.deotime.dekotpiler.Main"
    }
}
sourceSets.main {
    java.srcDirs("build/generated/ksp/main")
}

application {
    mainClass.set("MainKt")
    applicationName = "dekotpiler"
}