plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("io.papermc.paperweight.userdev") version "1.3.2"

    `java-library`
    `maven-publish`
    signing
}

val repo = "HGLabor/kutils"

group = "de.hglabor.utils"
version = "0.0.14"

description = "Kotlin kspigot plugin utils"

repositories {
    mavenCentral()
    maven("https://repo.cloudnetservice.eu/repository/snapshots/") // CloudNet
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    implementation(kotlin("reflect"))
    compileOnly("net.axay:kspigot:1.18.0")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.2.8")
    compileOnly("de.dytanic.cloudnet", "cloudnet-bridge", "3.4.0-SNAPSHOT")
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
            artifact(tasks.reobfJar)

            this.groupId = project.group.toString()
            this.artifactId = project.name.toLowerCase()
            this.version = project.version.toString()

            from(components["java"])

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
