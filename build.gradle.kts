import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm") version "2.4.21"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "pl.funnyskaddon"
version = "3.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.kawusia.cloud/releases") {
        mavenContent {
            releasesOnly()
        }
    }
    maven("https://maven.kawusia.cloud/snapshots") {
        mavenContent {
            snapshotsOnly()
        }
    }
    maven("https://repo.panda-lang.org/releases")
    maven("https://repo.panda-lang.org/snapshots")
    maven("https://repo.panda-lang.org/jitpack")
    maven("https://maven.enginehub.org/repo")
    maven("https://repo.mikeprimm.com")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://storehouse.okaeri.eu/repository/maven-public") {
        mavenContent {
            releasesOnly()
        }
    }
}

// VaultAPI 1.7 is not on any live repo; jitpack's MilkBowl fork provides the same artifact
configurations.compileClasspath {
    resolutionStrategy.dependencySubstitution {
        substitute(module("net.milkbowl.vault:VaultAPI")).using(module("com.github.MilkBowl:VaultAPI:1.7.1"))
    }
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("commons-io:commons-io:2.22.0")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.13")
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("net.dzikoysk.funnyguilds:plugin:5.0.1-SNAPSHOT")
    compileOnly("com.github.SkriptLang:Skript:2.17.1") {
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
