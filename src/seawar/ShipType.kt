enum class ShipType(val size: Int) {
    BATTLESHIP(4), // Линкор
    CRUISER(3),    // Крейсер
    DESTROYER(2),  // Эсминец
    BOAT(1);       // Катер
    fun isBig(): Boolean = size >= 3
}
