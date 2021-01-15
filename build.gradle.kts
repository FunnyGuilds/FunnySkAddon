plugins {
    kotlin("jvm") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "pl.funnyskaddon"
version = "2.0"

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    classifier = null
}

repositories {
    jcenter()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/" )
    maven("https://oss.sonatype.org/content/repositories/snapshots/" )
    maven("https://repo.panda-lang.org/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.dzikoysk:funnyguilds:4.8.1")
    compileOnly("com.github.SkriptLang:Skript:2.4.1")
    compileOnly(fileTree("libs") { include("*.jar") })
}
