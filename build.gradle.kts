plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "2.0.0"
    id("maven-publish")
}

group = "com.github.topanim"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("com.github.jitpack:gradle-simple:2.0")
    implementation("com.google.guava:guava:32.0.0-android")
}

kotlin {
    jvmToolchain(20)
}