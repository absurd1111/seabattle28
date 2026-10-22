class Player(val name: String) {

    private val _field: Array<Array<CellState>> =
        Array(10) { Array(10) { CellState.EMPTY } }


    fun field(): Array<Array<CellState>> = _field
    private var _shots: Int = 0
    private var _hits: Int = 0

    val shots: Int
        get() = _shots
    val hits: Int
        get() = _hits


    fun accuracy(): Double {
        return if (_shots == 0) 0.0 else _hits * 100.0 / _shots
    }

    fun registerShot(hit: Boolean) {
        _shots++
        if (hit) _hits++
    }

    fun placeShip(row: Int, col: Int, ship: ShipType, isHorizontal: Boolean = true) {
        for (i in 0 until ship.size) {
            val r = if (isHorizontal) row else row + i
            val c = if (isHorizontal) col + i else col
            if (r in 0..9 && c in 0..9) {
                if (_field[r][c] == CellState.EMPTY) {
                    _field[r][c] = CellState.SHIP
                }
            }
        }
    }

    fun markShot(row: Int, col: Int, hit: Boolean) {
        if (row in 0..9 && col in 0..9) {
            _field[row][col] = if (hit) CellState.HIT else CellState.MISS
        }
    }

    fun reset() {
        _shots = 0
        _hits = 0
        for (r in 0..9) {
            for (c in 0..9) {
                _field[r][c] = CellState.EMPTY
            }
        }
    }

    fun status(): String {
        val acc = String.format("%.1f", accuracy())
        return "Игрок $name: выстрелов $_shots, попаданий $_hits, точность $acc%"
    }
}
