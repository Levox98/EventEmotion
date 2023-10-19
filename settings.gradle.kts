pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:8.1.2")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.9.10")
            }
            if(requested.id.id.startsWith("com.google.dagger")) {
                useVersion("2.48")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EventEmotion"
include(":app")
include(":base-ui")
include(":common")
include(":core-navigation")
include(":data-events")
include(":feature-event")
include(":feature-main")
