plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.bristermitten"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/groups/central")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))

    implementation("net.kyori:adventure-api:4.8.1")
    implementation("net.kyori:adventure-platform-bukkit:4.0.0-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")

    implementation("com.google.inject:guice:5.0.1")
    implementation("dev.misfitlabs.kotlinguice4:kotlin-guice:1.5.0")

    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.javaParameters = true
    }

    compileJava {
        options.compilerArgs.add("-parameters")
        options.isFork = true
    }
    test {
        useJUnitPlatform()
    }

    shadowJar {
        listOf(
            "com.google",
            "co.aikar.commands",
            "co.aikar.locales"
        ).forEach {
            relocate(it, "me.bristermitten.mittenmines.libs.$it")
        }
    }
}
