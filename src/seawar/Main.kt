fun main() {
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    player.placeShip(row = 3, col = 2, ship = ShipType.BATTLESHIP, isHorizontal = true)

    enemy.placeShip(row = 5, col = 7, ship = ShipType.CRUISER, isHorizontal = false)

    printBothFields(player, enemy)
    println()

    player.registerShot(hit = true)  // попал
    player.registerShot(hit = false) // промах
    player.registerShot(hit = true)  // попал

    println(player.status())
    println("Выстрелов: ${player.shots}")
    println("Попаданий: ${player.hits}")

    player.reset()
    println(player.status())

    println("\nПоле после сброса:")
    printBothFields(player, enemy)
}

fun printBothFields(player: Player, enemy: Player) {
    println("=== Поле игрока ${player.name} ===      === Поле игрока ${enemy.name} ===")

    print("   ")
    for (c in 0..9) print("$c ")
    print("     ")
    print("   ")
    for (c in 0..9) print("$c ")
    println()
    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) {
            print("${player.field()[r][c].toChar()} ")
        }

        print("     ")

        print("$r  ")
        for (c in 0..9) {
            print("${enemy.field()[r][c].toChar()} ")
        }
        println()
    }
    println("\n--- Свой ENUM-класс ---")
    val currentState = GameState.PLAYER_TURN
    println("Статус игры: $currentState")
    println("Описание: ${currentState.describe()}")

    println("Линкор большой? ${ShipType.BATTLESHIP.isBig()}")
    println("Катер большой? ${ShipType.BOAT.isBig()}")
}
