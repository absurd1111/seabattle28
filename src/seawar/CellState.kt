enum class CellState{
    EMPTY,
    SHIP,
    HIT,
    MISS;

    fun toChar(): Char{
        return when (this){
            CellState.MISS -> 'O'
            CellState.EMPTY -> '.'
            CellState.SHIP -> '#'
            CellState.HIT -> 'X'
        }
    }
}