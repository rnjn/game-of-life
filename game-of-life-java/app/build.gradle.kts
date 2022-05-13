import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

sourceSets {
    main {
        java.srcDir("src/core/java")
    }

}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")

}
tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events(FAILED, STANDARD_ERROR, SKIPPED)
            exceptionFormat = FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
}

application {
    // Define the main class for the application.
    mainClass.set("in.rnjn.App")
}
