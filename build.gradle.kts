import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "pl.funnyskaddon"
version = "2.2.1"

tasks.withType<ShadowJar> {
    classifier = ""

    //Kotlin
    relocate("kotlin", "pl.funnyskaddon.libs.kotlin")
    //BStats
    relocate("org.bstats.bukkit", "pl.funnyskaddon.bstats")
    //JetBrains
    relocate("org.intellij", "pl.funnyskaddon.libs.intellij")
    relocate("org.jetbrains", "pl.funnyskaddon.libs.jetbrains")
    //Apache
    relocate("org.apache", "pl.funnyskaddon.libs.apache")

    minimize()
}

repositories {
    jcenter()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/" )
    maven("https://oss.sonatype.org/content/repositories/snapshots/" )
    maven("https://repo.panda-lang.org/")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.bstats:bstats-bukkit:2.2.1")
    implementation("commons-io:commons-io:2.11.0")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.dzikoysk:funnyguilds:4.9.7")
    compileOnly("com.github.SkriptLang:Skript:2.5.3")
}
