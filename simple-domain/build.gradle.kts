plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Deps.kotlinx_coroutines_core)
    implementation(Deps.kotlinx_coroutines_android)
    implementation(Deps.koin_core)
}