plugins {
    id("java")
}

group = "com.github.losevskiyfz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("org.slf4j:slf4j-simple:1.7.32")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}