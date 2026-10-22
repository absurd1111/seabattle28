enum class GameState {
    PLACING,
    PLAYER_TURN,
    ENEMY_TURN,
    FINISHED;

    fun describe(): String {
        return when (this) {
            PLACING -> "Игрок расставляет корабли на поле."
            PLAYER_TURN -> "Сейчас ваш ход! Выбирайте клетку для выстрела."
            ENEMY_TURN -> "Противник целится... Ждите."
            FINISHED -> "Игра завершена. Кто-то победил!"
        }
    }
}