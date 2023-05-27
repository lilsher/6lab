
plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.insert-koin:koin-core:3.3.3")
}

tasks.test {
    useJUnitPlatform()
}


application {
    mainClass.set("MainKt")
}