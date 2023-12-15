plugins {
    id("com.gradle.plugin-publish")
    `kotlin-dsl`
}

group = "org.splitties.gradle"

dependencies {
    compileOnly(gradleKotlinDsl())
}

gradlePlugin {
    vcsUrl = "https://github.com/Splitties/gradle-dependencies-dsl.git"
    website = "https://github.com/Splitties/gradle-dependencies-dsl"
    plugins {
        create(project.name) {
            id = "org.splitties.dependencies-dsl"
            displayName = "Dependencies DSL"
            description = "More concise dependencies declaration. Avoid repeating implementation, api, etc."
            tags = listOf("kotlin-dsl", "kotlin", "dependencies")
            implementationClass = "org.splitties.gradle.DependenciesDslPlugin"
        }
    }
}

kotlin {
    jvmToolchain(8)
}
