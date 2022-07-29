plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("io.papermc.paperweight.userdev") version "1.3.8"

    `java-library`
    `maven-publish`
    signing
}

val repo = "HGLabor/kutils"

group = "de.hglabor.utils"
version = "1.0.0-alpha.2"

description = "Kotlin kspigot plugin utils"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    paperDevBundle("1.19.1-R0.1-SNAPSHOT")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")

    compileOnly("net.axay:kspigot:1.19.0")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.2.11-beta-01")
    compileOnly("eu.cloudnetservice.cloudnet:bridge:4.0.0-SNAPSHOT")
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

java {
    withSourcesJar()
    withJavadocJar()
}

signing {
    sign(publishing.publications)
}


publishing {
    repositories {
        maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
            name = "ossrh"
            credentials(PasswordCredentials::class)
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks.jar.get().outputs.files.single())

            this.groupId = project.group.toString()
            this.artifactId = project.name.toLowerCase()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("krxwallo")
                    }
                }

                licenses {
                    license {
                        name.set("GNU General Public License, Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }

                url.set("https://github.com/$repo")

                scm {
                    connection.set("scm:git:git://github.com/$repo.git")
                    url.set("https://github.com/$repo/tree/main")
                }
            }
        }
    }
}
