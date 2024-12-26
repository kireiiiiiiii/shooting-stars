val mainClassName = "kireiiiiiiii.shooting_stars.AppMain"
val projectName = "ShootingStars"

plugins {
    id("application")
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    //---- JSON file manipulation ----
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
    //---- XLSX spreadsheet manipulation ----
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Use Java 21
    }
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass.set(mainClassName)
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.processResources {
    from("../LICENSES") {
        into("licenses")
    }
}

tasks.shadowJar {
    mergeServiceFiles()
    archiveClassifier.set("")
    archiveFileName.set("$projectName.jar")
}
