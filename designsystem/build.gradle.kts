@Suppress("DSL_SCOPE_VIOLATION") // FIXME: Remove once KTIJ-19369 is fixed

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = rootProject.file("ktlint.gradle"))
apply(from = rootProject.file("unit_test.gradle"))

android {
    namespace = "br.com.nbagames.designsystem"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
    packagingOptions {
        packagingOptions.resources.excludes += setOf(
            "**/attach_hotspot_windows.dll",
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
            "META-INF/licenses/ASM"
        )
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":core"))

    implementation(platform(libs.google.firebase.bom))

    implementation(libs.accompanist.pager.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.uicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.coil.compose)
    implementation(libs.compose.material.material)
    implementation(libs.compose.material.material.dynamic)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.google.material)
    implementation(libs.koin)
    implementation(libs.lottie)

    debugImplementation(libs.androidx.compose.ui.manifest)
    debugImplementation(libs.androidx.customview)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test)
}
