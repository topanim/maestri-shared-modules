plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "2.0.0"
}

group = "org.maestri"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}