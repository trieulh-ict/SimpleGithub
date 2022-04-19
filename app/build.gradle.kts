plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(ConfigData.compileSdkVersion)

    defaultConfig {
        applicationId = "io.github.simplegithub"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val token = System.getenv("GITHUB_TOKEN")
            buildConfigField("String", "GITHUB_TOKEN", '"' + token + '"')
        }
        getByName("debug") {
            val token = System.getenv("GITHUB_TOKEN")
            buildConfigField("String", "GITHUB_TOKEN", '"' + token + '"')
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":simple-service"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation(project(mapOf("path" to ":simple-domain")))
    implementation(Deps.kotlin)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.timber)
    implementation(Deps.constraintLayout)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_ui_ktx)

    testImplementation(Deps.junit)
    testImplementation(Deps.mockk)
    testImplementation(Deps.coroutineTest)
    testImplementation(Deps.coreTesting)

    implementation(Deps.koin_android)

    implementation(Deps.simple_adapter)
    implementation(Deps.coil)
}