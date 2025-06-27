plugins {
    id("java")
    id("dev.architectury.loom") version("1.9-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
    kotlin("jvm") version "2.1.0"
}

group = property("maven_group")!!
version = property("mod_version")!!

architectury {
    platformSetupLoomIde()
    neoForge()
}

loom {
    silentMojangMappingsLicense()
}

repositories {
    mavenCentral()
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
    maven("https://maven.neoforged.net")
    maven("https://maven.wispforest.io")
    maven("https://maven.su5ed.dev/releases")
}

dependencies {
    minecraft("net.minecraft:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    neoForge("net.neoforged:neoforge:${property("neoforge_version")}")

    modImplementation("com.cobblemon:neoforge:${property("cobblemon_version")}")
    //Needed for cobblemon
    implementation("thedarkcolour:kotlinforforge-neoforge:${property("kff_version")}") {
        exclude("net.neoforged.fancymodloader", "loader")
    }

    modImplementation("io.wispforest:owo-lib-neoforge:${property("owo_version")}")
    annotationProcessor("io.wispforest:owo-lib-neoforge:${property("owo_version")}")
    forgeRuntimeLibrary("io.wispforest:endec:0.1.8")
    forgeRuntimeLibrary("io.wispforest.endec:netty:0.1.4")
    forgeRuntimeLibrary("io.wispforest.endec:gson:0.1.5")
    forgeRuntimeLibrary("io.wispforest.endec:jankson:0.1.5")
    forgeRuntimeLibrary("blue.endless:jankson:1.2.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(project.properties)
    }
}