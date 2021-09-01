plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}
repositories {
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}
dependencies {
    // Core dependencies
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))
    implementation(project(":compat"))
    implementation(project(":api"))
    implementation(project(":legacy-worldedit"))
    implementation(project(":modern-worldedit"))

    // Plugin dependencies
    compileOnly("me.clip:placeholderapi:2.10.10")

    // Librarie
    implementation("net.kyori:adventure-api:4.8.1")
    implementation("net.kyori:adventure-platform-bukkit:4.0.0-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")

    implementation("com.google.inject:guice:5.0.1")
    implementation("dev.misfitlabs.kotlinguice4:kotlin-guice:1.5.0")

    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
}

tasks {
    test {
        useJUnitPlatform()
    }

    compileJava {
        options.compilerArgs.add("-parameters")
        options.isFork = true
    }

    shadowJar {
        minimize()
        listOf(
            "com.google",
            "co.aikar.commands",
            "co.aikar.locales"
        ).forEach {
            relocate(it, "me.bristermitten.mittenmines.libs.$it")
        }
    }
}
