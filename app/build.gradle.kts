plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.all_in_onecontroller"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.all_in_onecontroller"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    doFirst {
        source.forEach { file ->
            if (file.exists() && file.isFile) {
                val bytes = file.readBytes()
                if (bytes.size >= 3 && bytes[0] == 0xEF.toByte() && bytes[1] == 0xBB.toByte() && bytes[2] == 0xBF.toByte()) {
                    file.writeBytes(bytes.copyOfRange(3, bytes.size))
                }
            }
        }
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.camerax.core)
    implementation(libs.camerax.camera2)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}