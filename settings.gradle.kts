plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.3"
    id("org.splitties.settings-include-dsl") version "0.2.6"
    id("org.splitties.version-sync") version "0.2.6"
}

rootProject.name = "dependencies-dsl"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

