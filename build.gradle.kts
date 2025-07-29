plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("one.cafebabe.dukedance.DukeDanceApp")
}

group = "one.cafebabe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}