# Dependencies DSL for Gradle projects

Instead of repeating `implementation` or `api` countless times in your Gradle build scripts,
you can use this DSL to write it just once per set of dependencies.

## Before

`api`, `implementation`, and `testImplementation` are repeated, adding unwanted noise.

```kotlin
dependencies {
    api("com.example.whatever:what-you-need:x.x.x")
    api(libs.coolFramework) // From version catalog
    api(KotlinX.coroutines) // From refreshVersions built-in dependency notations
    implementation(libs.something) // From version catalog
    implementation(libs.stuff) // From version catalog
    implementation("com.example.whatever:yet-another-thing:x.x.x")
    implementation("com.example.whatever:your-favorite-library:x.x.x")
    implementation("com.example.whatever:utils-of-course:x.x.x")
    testImplementation(Kotlin.test.junit)
    testImplementation(Testing.kotest.assertions.core)
    testImplementation(KotlinX.coroutines.test)
}
```

## After

Straight to the dependency notations, minimal noise!

```kotlin
dependencies {
    api {
        "com.example.whatever:what-you-need:x.x.x"()
        libs.coolFramework()
        KotlinX.coroutines()
    }
    implementation {
        libs.something()
        libs.stuff()
        "com.example.whatever:yet-another-thing:x.x.x"()
        "com.example.whatever:your-favorite-library:x.x.x"()
        "com.example.whatever:utils-of-course:x.x.x"()
    }
    testImplementation {
        Kotlin.test.junit()
        Testing.kotest.assertions.core()
        KotlinX.coroutines.test()
    }
}
```

## Important

Don't forget to add the parentheses to the dependency notations or it won't work!

## Extra features

### Arbitrary configurations support

We support custom or less-standard configurations as well:

```kotlin
dependencies {
    this("anotherConfiguration") {
        libs.something()
    }
}
```

### Convenient exclude overload

You can put a plain dependency notation into exclude, which can be especially handy when using refreshVersions:

```kotlin
AndroidX.wear.remoteInteractions {
    //TODO: Remove when https://issuetracker.google.com/issues/275580742 is fixed.
    exclude(KotlinX.coroutines.guava)
}
```

## How to add to your project

Go in the `settings.gradle.kts` file, located at the root of your Gradle project.

Add the following line into the `plugins { … }` block (add one at the top of the file it there's none):

```gradle.kts
id("org.splitties.dependencies-dsl") version "0.1.0"
```

It can also work if you add it in the `build.gradle.kts` file of the module you want to try this DSL in.

## License

This library is published under Apache License version 2.0 which you can see [here](LICENSE).
