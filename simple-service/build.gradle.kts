plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Deps.retrofit)
    implementation(Deps.gson)
    implementation(Deps.interceptor)
    implementation(Deps.kotlinx_coroutines_core)
    implementation(Deps.kotlinx_coroutines_android)
    implementation(Deps.koin_core)
    implementation(project(mapOf("path" to ":simple-domain")))
}