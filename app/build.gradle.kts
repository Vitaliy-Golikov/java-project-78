plugins {
    jacoco
    id("java")
    id("checkstyle")
    id("org.sonarqube") version "7.3.0.8198"
}

group = "hexlet.code"
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
    // ДОБАВЬТЕ ЭТУ СЕКЦИЮ:
    testLogging {
        events("passed", "skipped", "failed")  // Какие события выводить
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL // Полный стек ошибок
        showStandardStreams = true // Выводить System.out.println из тестов
    }
}

sonar {
    properties {
        property("sonar.projectKey", "Vitaliy-Golikov_java-project-78")
        property("sonar.organization", "vitaliy-golikov")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
