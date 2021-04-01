plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
}
android {
    compileSdkVersion(ProjectProperties.compileSdk)

    defaultConfig {
        minSdkVersion(ProjectProperties.minSdk)
        targetSdkVersion(ProjectProperties.targetSdk)
        applicationId = ProjectProperties.applicationId
        versionCode = ProjectProperties.versionCode
        versionName = ProjectProperties.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    // Configure Java compiler compatible with Java 1.8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Configure Kotlin compiler target Java 1.8 when compile Kotlin to bytecode
    kotlinOptions {
        this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Android
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.appCompat)
    implementation(Libs.constraintlayout)
    implementation(Libs.viewmodel)
    implementation(Libs.lifecycle)
    implementation(Libs.lifecycleRT)
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.testExt)
    androidTestImplementation(Libs.espresso)

    // Kotlin
    implementation(Libs.kotlinStdLib)
    implementation(Libs.ktxCore)
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesTest)
    implementation(Libs.coroutinesAndroid)

    // Network
    implementation(Libs.volley)
    implementation(Libs.gson)
    implementation(Libs.retrofit)
    implementation(Libs.converterMoshi)
    implementation(Libs.moshi)
    implementation(Libs.moshiCodeGen)

    // Image Display
    implementation(Libs.glide)
    annotationProcessor(Libs.glideCompiler)
}