# nba-games
Simple Compose app to follow NBA games

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![codecov](https://codecov.io/gh/bmattoso/nba-games/branch/main/graph/badge.svg?token=ZG87GNZSL9)](https://codecov.io/gh/bmattoso/nba-games)
[![CI](https://github.com/bmattoso/nba-games/actions/workflows/android.yml/badge.svg?branch=main)](https://github.com/bmattoso/nba-games/actions/workflows/android.yml)

## Framework and Libraries

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI
- [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - Navigate between composables while taking advantage of the Navigation component’s infrastructure and features.
- [OkHttp](https://github.com/square/okhttp/) - Library to xchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and Java.
- [Room](https://developer.android.com/training/data-storage/room) - Library to help to work with use case to cache relevant pieces of data
- [Koin](https://insert-koin.io/) - Kotlin injection library (You can actually say it's not a dependency injection library like Dagger or Hilt)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Compiler plugin that generates visitor code for serializable classes
- [Kotlinx Coroutines (Flow)](https://developer.android.com/kotlin/flow) - Cold asynchronous stream with flow builder and comprehensive operator set (filter, map, etc)


## Architecture

I opted to use an architecture similar to Clean Architecture looking for a maximus separation of layers. <br/>
We can see some particularities of this project, looking for a Game model we can see 3 different class to handle different proposes even that they are a Game.

---

### GameResponse

Looking from request layer until the view, the first model class we have is [GameResponse](https://github.com/bmattoso/nba-games/blob/main/remote/src/main/java/br/com/nbagames/remote/game/response/GameResponse.kt) responsible to parse json responses into kotlin model, here we have the kotlin serialization annotations.<br/><br/>
In these type of model, we have the most primitive types to objects like String to represent the game Date, and then in mappers we parse the String to Date type, like in [GameMapper](https://github.com/bmattoso/nba-games/blob/main/remote/src/main/java/br/com/nbagames/remote/game/mapper/GameMapper.kt)

> **Important:** These types must respect the API type response, if you map it wrong you'll receive a SerializationException. 

```kotlin
    @Serializable
    data class GameResponse(
        val id: Int,
        val status: GameStatusResponse,
        val periods: GamePeriodsResponse,
        val teams: GameTeamsResponse,
        val scores: GameScoreResponse
    )
```
---
### Game 

These type of model are the representativity of a real [Game](https://github.com/bmattoso/nba-games/blob/main/model/src/main/java/br/com/nbagames/model/Game.kt), with subtypes that we can manipulate and are easy to work with. Here we can use Enums, Date, BigDecimal and other types you prefer.<br/><br/>
This model will be used by repository, Use cases and the viewModel.  

```kotlin
data class Game(
    val id: Int,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homePoints: Int,
    val visitorPoints: Int,
    val currentClock: String?,
    val quarter: Quarter
)
```
---
### LiveGamePresentation

These type of model are used only to display information to composable functions so here we prefer to use 2 primitive types of variables: Strings and Int (or Double/Float if the number has decimal values).<br/><br/>
To create these models we use another mapper([LiveGamePresentationMapper](https://github.com/bmattoso/nba-games/blob/main/game/src/main/java/br/com/nbagames/game/mapper/LiveGamePresentationMapper.kt)) to parse our complex data information to the most simple information, we do that to simplify our compose logic and do not let the viewModel handle all responsibilities.

```kotlin
data class LiveGamePresentation(
    val id: Int,
    val homeTeam: Team,
    val visitantTeam: Team,
    val homePoints: Int,
    val visitantPoints: Int,
    val gameClock: String?,
    val quarter: Int
)
```

## Code Coverage Report

In this project I'm using the new kotlin code coverage plugin, [Kover](https://github.com/Kotlin/kotlinx-kover).<br/><br/>
Since it's in beta version, we can't create an merged coverage report with instrumented tests and unit tests. 

The layers covered by unit test are: 
    - Remote Data Source
    - Local Data Source
    - Repository
    - Use Case
    - Extensions/Helpers
    - Presentation (ViewModel)
    
And in the other side, the instrumented tests runs simulating an common user, validating the composition of components and the communication of each others.<br/><br/>
So the covered layer is the View (Composable components and Activities).
---
### Code coverage limitations

Kover is still in beta version, we have some limitations about what can be covered or what's been covered.
---
#### Kotlinx Serialization

The first issue is that coverage from Response models are considering autogenerated code from kotlinx.serialization.
> Follow [#121](https://github.com/Kotlin/kotlinx-kover/issues/121) issue and wait the fix.
---
#### Integration with instrumented tests

Kover doesn't support instrumentation in Android Devices, it's in roadmap but it's not in work in progress. Then the CodeCov badge shows only the unit test coverage.
> Follow [#96](https://github.com/Kotlin/kotlinx-kover/issues/96) to check it has been done. 


## Author

Designed and developed by Bruno Mattoso

## License

MIT License

Copyright (c) 2022 Bruno Mattoso Gonçalves

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.