pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "nbagames"
rootProject.buildFileName = "build.gradle.kts"

include(
    ":app",
    ":model",
    ":core",
    ":designsystem",
    ":game",
    ":usecase",
    ":calendar",
    ":repository",
    ":remote",
    ":local",
    ":team"
)
