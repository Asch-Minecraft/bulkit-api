import org.jetbrains.kotlin.gradle.utils.extendsFrom
import java.time.LocalDateTime

plugins {
    `java-library`
    `maven-publish`
    idea
    alias(libs.plugins.neoforged.moddev)
    kotlin("jvm") version "2.0.21"
}

tasks.named<Wrapper>("wrapper").configure {
    distributionType = Wrapper.DistributionType.BIN
}

val mod_id: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_name: String by project
val mod_license: String by project
val mod_authors: String by project
val mod_description: String by project

val parchment_mappings_version: String by project
val parchment_minecraft_version: String by project
val minecraft_version: String by project
val minecraft_version_range: String by project
val neo_version: String by project
val neo_version_range: String by project
val loader_version_range: String by project

version = mod_version
group = mod_group_id

repositories {
    mavenLocal()

    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }
}

base {
    archivesName = mod_id
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

sourceSets {
    register("datagen") {
        compileClasspath += main.get().output + main.get().compileClasspath
        runtimeClasspath += main.get().output + main.get().runtimeClasspath
    }
}

neoForge {
    version = neo_version

    parchment.mappingsVersion = parchment_mappings_version
    parchment.minecraftVersion = parchment_minecraft_version

    mods {
        register(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }

    runs {
        register("data") {
            data()
            systemProperty("forge.logging.markers", "REGISTRIES")
            programArguments.addAll(
                "--mod",
                mod_id,
                "--all",
                "--output",
                file("src/generated/resources/").absolutePath,
                "--existing",
                file("src/main/resources/").absolutePath
            )
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }
}

configurations {
    val localRuntime = register("localRuntime")
    named("runtimeClasspath").extendsFrom(localRuntime)
}

dependencies {
    implementation(libs.kotlinforforge)
}

val generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
    val replaceProperties = mapOf(
        "minecraft_version" to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "neo_version" to neo_version,
        "neo_version_range" to neo_version_range,
        "loader_version_range" to loader_version_range,
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_license" to mod_license,
        "mod_version" to mod_version,
        "mod_authors" to mod_authors,
        "mod_description" to mod_description
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

sourceSets.main.get().resources.srcDir(generateModMetadata)
neoForge.ideSyncTask(generateModMetadata)

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "bulkit-api"
        }
    }

    repositories {
        maven {
            name = "AschMaven"
            url = uri("https://repo.repsy.io/mvn/asch/bulkit-api")
            credentials {
                username = project.findProperty("publish.repsy_username").toString()
                password = project.findProperty("publish.repsy_pw").toString()
            }
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
