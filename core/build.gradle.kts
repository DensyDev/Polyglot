plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))
    api("org.yaml:snakeyaml:2.4")
    api("com.google.code.gson:gson:2.13.1")
}