import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

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

val api_version: String by project

val parchment_mappings_version: String by project
val parchment_minecraft_version: String by project
val minecraft_version: String by project
val minecraft_version_range: String by project
val neo_version: String by project
val neo_version_range: String by project
val loader_version_range: String by project

version = api_version
group = "org.asch.bulkit.api"

repositories {
    mavenLocal()
}

base {
    archivesName = "bulkit-api"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)
java.withSourcesJar()
java.withJavadocJar()

neoForge {
    version = neo_version

    parchment.mappingsVersion = parchment_mappings_version
    parchment.minecraftVersion = parchment_minecraft_version
}

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

tasks.withType<Jar> {
    manifest {
        val timestamp = SimpleDateFormat("MM-dd-yyyy_hh-mm").format(Date())
        attributes(
            "Specification-Title" to "Bulk It! - API",
            "Specification-Vendor" to "Neoforge",
            "Specification-Version" to "1",
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "asch",
            "Implementation-Timestamp" to timestamp,
            "Automatic-Module-Name" to "asch.bulkit.api",
            "FMLModType" to "GAMELIBRARY",
        )
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
