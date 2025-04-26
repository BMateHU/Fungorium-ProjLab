plugins {
    id("java")
    id("application")
}

group = "com.beingchilling"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.beingchilling.Main"
}

tasks.withType(JavaExec::class) {
    standardInput = System.`in`
}

tasks.withType(Jar::class) {
    manifest {
        attributes["Manifest-Version"] = version
        attributes["Main-Class"] = "com.beingchilling.Main"
    }
}

tasks.withType(Test::class) {
    testLogging.showStandardStreams = true
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}