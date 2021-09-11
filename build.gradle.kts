import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

plugins {
    kotlin("jvm") version "1.5.30"
    kotlin("plugin.serialization") version "1.5.30"
}

subprojects {
    apply<KotlinPlatformJvmPlugin>()
    apply<org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin>()

    group = "me.bristermitten.mittenmines"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/central")
        maven("https://repo.mattstudios.me/artifactory/public/")
    }

    dependencies {
        // Core dependencies
        compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
        implementation(kotlin("stdlib", "1.5.21"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

        // Libraries
        implementation("com.google.inject:guice:5.0.1")
        implementation("dev.misfitlabs.kotlinguice4:kotlin-guice:1.5.0")

        // Testing
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
        testImplementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    }

    tasks {
        compileKotlin {
            kotlinOptions.javaParameters = true
            kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }
        test {
            useJUnitPlatform()
        }
    }
}
