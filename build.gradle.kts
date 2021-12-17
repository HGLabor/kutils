plugins {
    kotlin("jvm") version "1.6.0"
    id("io.papermc.paperweight.userdev") version "1.3.2"
}

group = "de.hglabor.utils"
version = "0.0.1"

repositories {
    mavenCentral()
    maven("https://mvn.intellectualsites.com/content/repositories/releases/")
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    implementation(kotlin("reflect"))
    implementation("net.axay:kspigot:1.18.0")
    implementation("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit:1.17-418")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}