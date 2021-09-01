repositories {
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly(project(":api")) {
        exclude("org.spigotmc")
    }
    compileOnly(project(":compat"))
    compileOnly("org.spigotmc:spigot-api:1.13.1-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.0.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.0") {
        exclude("com.sk89q", "worldedit")
    }
}
