dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "nbagames"

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
