repositories {
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}
dependencies {


    // Plugin dependencies
    compileOnly("me.clip:placeholderapi:2.10.10")

    // Libraries
    implementation("net.kyori:adventure-api:4.9.0")
    implementation("net.kyori:adventure-platform-bukkit:4.0.0-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")

    implementation("dev.triumphteam:triumph-gui:3.0.3")

    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
}
