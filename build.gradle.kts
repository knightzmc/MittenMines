import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

plugins {
    kotlin("jvm") version "1.5.30"
}

subprojects {
    apply<KotlinPlatformJvmPlugin>()

    group = "me.bristermitten.mittenmines"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/central")
        maven("http://nexus.okkero.com/repository/maven-releases/")
    }

    dependencies {
        // Core dependencies
        compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
        implementation("com.okkero.skedule:skedule:1.2.6")

        // Libraries
        implementation("com.google.inject:guice:5.0.1")
        implementation("dev.misfitlabs.kotlinguice4:kotlin-guice:1.5.0")
    }

    tasks {
        compileKotlin {
            kotlinOptions.javaParameters = true
        }
    }
}
