import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "pl.funnyskaddon"
version = "2.2.19-Beta"

tasks.withType<ShadowJar> {
    archiveFileName.set("FunnySkAddon ${project.version}.jar")

    //Kotlin
    relocate("kotlin", "pl.funnyskaddon.libs.kotlin")
    //BStats
    relocate("org.bstats", "pl.funnyskaddon.bstats")
    //JetBrains
    relocate("org.intellij", "pl.funnyskaddon.libs.intellij")
    relocate("org.jetbrains", "pl.funnyskaddon.libs.jetbrains")
    //Apache
    relocate("org.apache", "pl.funnyskaddon.libs.apache")
    //Okaeri
    relocate("eu.okaeri", "pl.funnyskaddon.libs.okaeri")

    minimize()
}

tasks.withType<Copy> {
    delete(fileTree(project.buildDir) {
        include("resources/main/plugin.yml")
    })

    filter(ReplaceTokens::class, "tokens" to mapOf("version" to rootProject.version))
}

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.panda-lang.org/releases")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://storehouse.okaeri.eu/repository/maven-public")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    implementation("org.bstats:bstats-bukkit:3.0.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:3.4.2")
    shadow("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    shadow("net.dzikoysk.funnyguilds:plugin:4.10.0")
    shadow("com.github.SkriptLang:Skript:2.6.1") {
        isTransitive = false
    }
}
