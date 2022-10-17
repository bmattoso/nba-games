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
rootProject.buildFileName = "build.gradle"

include(":app")
include(":designsystem")
include(":game")
include(":model")
include(":usecase")
include(":calendar")
include(":repository")
include(":remote")
include(":local")
include(":core")
include(":team")
