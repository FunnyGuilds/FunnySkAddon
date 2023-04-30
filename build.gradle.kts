import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "pl.funnyskaddon"
version = "2.5.0"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
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
    implementation(kotlin("stdlib"))
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("commons-io:commons-io:2.11.0")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.0-beta.5")
    shadow("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    shadow("net.dzikoysk.funnyguilds:plugin:4.11.0")
    shadow("com.github.SkriptLang:Skript:2.6.2") {
        isTransitive = false
    }
}

tasks.withType<ShadowJar> {
    archiveFileName.set("FunnySkAddon ${project.version}.jar")

    //Kotlin
    relocate("kotlin", "pl.funnyskaddon.libs.kotlin")
    //BStats
    relocate("org.bstats", "pl.funnyskaddon.bstats")
    //Apache
    relocate("org.apache", "pl.funnyskaddon.libs.apache")
    //Okaeri
    relocate("eu.okaeri", "pl.funnyskaddon.libs.okaeri")

    exclude("org/intellij/lang/annotations/**")
    exclude("org/jetbrains/annotations/**")

    minimize()
}

tasks.withType<Copy> {
    delete(fileTree(project.buildDir) {
        include("resources/main/plugin.yml")
    })

    filter(ReplaceTokens::class, "tokens" to mapOf("version" to rootProject.version))
}
