plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.7.10"
}

group = "dev.peopo"
version = "1.0.0"
description = "A small library to add coroutine support to bukkit schedulers."

repositories {
    mavenCentral()
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.+")
}


tasks {
    compileKotlin{
        kotlinOptions.jvmTarget = "1.8"
    }
    wrapper {
        gradleVersion = "7.4"
        distributionType = Wrapper.DistributionType.ALL
    }
    compileJava {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "${project.group}"
            artifactId = rootProject.name
            version = "${project.version}"

            from(components["java"])
        }
    }
}