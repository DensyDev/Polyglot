plugins {
    `maven-publish`
    `java-library`
    id("java")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

allprojects {
    group = "com.luminiadev.polyglot"
    version = "1.0.2-SNAPSHOT"
}

subprojects {
    apply {
        plugin("java-library")
        plugin("maven-publish")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnlyApi("org.projectlombok:lombok:1.18.38")
        annotationProcessor("org.projectlombok:lombok:1.18.38")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group.toString()
                artifactId = project.name.lowercase()
                version = project.version.toString()
                from(components["java"])
            }
        }
        repositories {
            maven {
                name = "luminiadev"
                url = uri("https://repo.luminiadev.com/private")
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }
}