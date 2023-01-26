import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    application
}

group = "me.deo.dekotpiler"
version = "1.0.0"

repositories {
    mavenCentral()
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
    implementation("org.ow2.asm:asm:7.3.1")

    // metadata
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.6.0")

    // teting
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}