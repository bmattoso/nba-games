package br.com.nbagames.model

enum class GameStatus(val code: Int) {
    SCHEDULED(1), RUNNING(2), FINISHED(3), HALF_TIME(4)
}
