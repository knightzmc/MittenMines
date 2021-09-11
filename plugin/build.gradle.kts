plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}
repositories {
    maven("https://repo.aikar.co/content/groups/aikar/")
}
dependencies {
    // Core dependencies
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation(project(":common"))
    implementation(project(":legacy-worldedit"))
    implementation(project(":modern-worldedit"))
}


tasks {
    register<Copy>("copyJarToServerPlugins") {
        from(getByPath("shadowJar"))
        into(layout.projectDirectory.dir("../server/plugins"))
    }

    compileJava {
        options.compilerArgs.add("-parameters")
        options.isFork = true
    }

    shadowJar {
        minimize()
        this.archiveFileName.set("MittenMines.jar")
        listOf(
//            "com.google.inject",
            "com.google.common",
//            "co.aikar.commands",
//            "co.aikar.locales"
        ).forEach {
            relocate(it, "me.bristermitten.mittenmines.libs.$it")
        }
    }
}
