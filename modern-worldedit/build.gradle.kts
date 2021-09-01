repositories {
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":compat"))
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.0.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.0") {
        exclude("com.sk89q", "worldedit")
    }
}
