package org.gradle.kotlin.dsl

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

fun DependencyHandler.implementation(block: ConfigurationDependencyScope.() -> Unit) {
    this("implementation", block)
}

fun DependencyHandler.api(block: ConfigurationDependencyScope.() -> Unit) {
    this("api", block)
}

fun DependencyHandler.testImplementation(block: ConfigurationDependencyScope.() -> Unit) {
    this("testImplementation", block)
}

operator fun DependencyHandler.invoke(
    configurationName: String,
    block: ConfigurationDependencyScope.() -> Unit
) {
    ConfigurationDependencyScope(this, configurationName).apply(block)
}

class ConfigurationDependencyScope internal constructor(
    private val handler: DependencyHandler,
    private val configurationName: String
) {
    operator fun Any.invoke(dependencyConfiguration: Action<ExternalModuleDependency>) {
        addDependencyTo(
            handler, configurationName, this, dependencyConfiguration
        )
    }

    operator fun Any.invoke() {
        handler.add(configurationName, this)
    }
}

fun <T : ModuleDependency> T.exclude(dependencyNotation: CharSequence): T {
    val text = dependencyNotation.toString()
    return exclude(
        group = text.substringBefore(':'),
        module = text.substringAfter(':').let {
            if (':' in it) it.substringBefore(':') else it
        }
    )
}
