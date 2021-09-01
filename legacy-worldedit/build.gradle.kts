repositories {
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":compat"))
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:6.1"){
        exclude("org.bukkit")
    }
    compileOnly("com.sk89q:worldguard:6.1") {
        exclude("com.sk89q", "worldedit")
    }
}
