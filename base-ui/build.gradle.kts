plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.eventemotion.base_ui"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }
}

dependencies {

    implementation(Dependencies.Kotlin.collections)

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)

    implementation(Dependencies.Lifecycle.activity)

    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.util)
    implementation(Dependencies.Compose.uiToolingPreview)
    debugImplementation(Dependencies.Compose.debugUiTooling)
}