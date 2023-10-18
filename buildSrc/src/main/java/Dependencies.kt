object Dependencies {

    object Kotlin {
        const val version = "1.9.10"
        private const val coroutinesVersion = "1.7.0"
        private const val collectionsVersion = "0.3.5"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val collections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:$collectionsVersion"
    }

    object Compose {
        private const val bomVersion = "2023.10.00"
        const val compilerVersion = "1.5.3"

        const val bom = "androidx.compose:compose-bom:$bomVersion"
        const val ui = "androidx.compose.ui:ui"
        const val graphics = "androidx.compose.ui:ui-graphics"
        const val debugUiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"
        const val iconsExtended = "androidx.compose.material:material-icons-extended"
        val util = "androidx.compose.ui:ui-util"
    }

    object Core {
        private const val core_version = "1.12.0"
        private const val appcompat_version = "1.6.1"

        val coreKtx = "androidx.core:core-ktx:$core_version"
        val appCompat = "androidx.appcompat:appcompat:$appcompat_version"
    }

    object Lifecycle {
        private const val lifecycle_version = "2.6.2"
        private const val viewmodel_version = "2.4.0"
        private const val activity_compose_version = "1.8.0"
        private const val activity_version = "1.8.0-beta01"

        val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
        val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:$viewmodel_version"
        val activityCompose = "androidx.activity:activity-compose:$activity_compose_version"
        val activity = "androidx.activity:activity-ktx:$activity_version"
    }

    object Hilt {
        const val version = "2.48.1"
        const val navigationVersion = "1.0.0"

        const val hilt = "com.google.dagger:hilt-android:$version"
        const val kapt = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$navigationVersion"
    }

    object Room {
        private const val version = "2.5.2"

        const val api = "androidx.room:room-runtime:$version"
        const val kapt = "androidx.room:room-compiler:$version"
        const val room = "androidx.room:room-ktx:$version"
    }

    object Navigation {
        private const val composeNavVersion = "2.6.0"
        private const val hiltNavVersion = "1.0.0"

        const val compose = "androidx.navigation:navigation-compose:$composeNavVersion"
        const val hilt = "androidx.hilt:hilt-navigation-compose:$hiltNavVersion"
    }
}