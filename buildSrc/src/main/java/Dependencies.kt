object Deps {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.app_compat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}" }
    val navigation_fragment_ktx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}" }
    val navigation_ui_ktx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}" }

    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val coroutineTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutines}" }
    val coreTesting by lazy { "androidx.arch.core:core-testing:${Versions.core_testing}" }

    val koin_core by lazy { "io.insert-koin:koin-core:${Versions.koin_version}" }
    val koin_android by lazy { "io.insert-koin:koin-android:${Versions.koin_version}" }

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}" }
    val kotlinx_coroutines_android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines}" }
    val kotlinx_coroutines_core by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines}" }

    // View lib dependencies
    val simple_adapter by lazy { "com.github.trieulh-ict:SimpleGenericAdapter:${Versions.simple_adapter}" }
    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
}