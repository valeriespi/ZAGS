plugins {
    id("java")
    id("io.qameta.allure") version "4.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val aspectJVersion = "1.9.25"
val agent = configurations.create("agent") {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.51.0")

    testImplementation(platform("org.junit:junit-bom:5.14.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(platform("io.qameta.allure:allure-bom:3.0.1"))
    implementation("io.qameta.allure:allure-jupiter:3.0.1")

    testImplementation(platform("org.apache.logging.log4j:log4j-bom:2.25.1"))
    testImplementation("org.apache.logging.log4j:log4j-api")
    testRuntimeOnly("org.apache.logging.log4j:log4j-core")

    add("agent", "org.aspectj:aspectjweaver:$aspectJVersion")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf("-javaagent:${agent.singleFile}")
    finalizedBy("allureReport")
}

tasks.named("allureReport") {
    dependsOn(tasks.test)
}