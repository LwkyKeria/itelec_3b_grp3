plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.barngyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.barngyapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main") {
            java {
                srcDirs("src/main/java", "src/main/java/2", "src/main/java/com/example/barngyapp/backendapi")
            }
        }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.filament.android)
    testImplementation(libs.junit)
    implementation(libs.retrofit.v290) // Correct dependency syntax
    implementation(libs.converter.gson.v290) // Correct dependency syntax
    androidTestImplementation(libs.ext.junit)
    //noinspection GradleDependency
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.sqlite)
    implementation(libs.retrofit) // If using version from `libs`
    implementation(libs.converter.gson) // If using version from `libs`
}
