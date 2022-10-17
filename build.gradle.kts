import com.android.build.gradle.BaseExtension
import com.android.build.gradle.BasePlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // FIXME: Remove once KTIJ-19369 is fixed

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.kover)
}

allprojects {

    apply(plugin = rootProject.libs.plugins.kover.get().pluginId)

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs.plus(listOf("-opt-in=kotlin.RequiresOptIn"))
        }
    }
}

subprojects {
    plugins.withType<BasePlugin>().configureEach {
        extensions.configure<BaseExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())
            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                targetSdk = libs.versions.targetSdk.get().toInt()
            }
        }
    }
}

koverMerged {
    enable()

    filters {
        classes {
            excludes.addAll(
                listOf(
                    "**Response*",
                    "br.com.nbagames.model.**",
                    "**BuildConfig**",
                    "**\$**",
                    "**Entity**",
                )
            )
        }
        annotations {
            excludes.addAll(listOf("androidx.compose.runtime.Composable"))
        }
    }
}