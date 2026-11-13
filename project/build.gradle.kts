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
    testImplementation("io.rest-assured:rest-assured:6.3.0")
    testImplementation("net.datafaker:datafaker:2.7.0")
    testImplementation("io.cucumber:cucumber-java:7.34.9")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.34.9")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.20.1")
    testImplementation("org.postgresql:postgresql:42.7.7")
    testImplementation("org.hibernate.orm:hibernate-core:7.4.15.Final")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(platform("io.qameta.allure:allure-bom:3.0.1"))
    implementation("io.qameta.allure:allure-jupiter:3.0.1")

    testImplementation(platform("org.apache.logging.log4j:log4j-bom:2.25.1"))
    testImplementation("org.apache.logging.log4j:log4j-api")
    testRuntimeOnly("org.apache.logging.log4j:log4j-core")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl")

    add("agent", "org.aspectj:aspectjweaver:$aspectJVersion")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf("-javaagent:${agent.singleFile}")
    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "fixed")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.parallelism", "3")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.max-pool-size", "3")
    listOf("stand.user", "stand.password", "db.host", "db.port", "db.database", "db.user", "db.password", "selenoid", "selenoid.url")
        .forEach { name -> System.getProperty(name)?.let { systemProperty(name, it) } }
    doFirst {
        delete("build/allure-results")
    }
    finalizedBy("allureReport")
}

tasks.named("allureReport") {
    dependsOn(tasks.test)
}