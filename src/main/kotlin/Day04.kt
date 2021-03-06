class Day04 : AdventDay(day = "day04") {

    override fun part1(input: List<String>): Long {
        val initialState = InputParser(input).parse()
        val bingo = BingoSubsystem(initialState)
        bingo.markUntilFirstWinner()
        return bingo.winningBoard().score().toLong()
    }

    override fun part2(input: List<String>): Long {
        val initialState = InputParser(input).parse()
        return markUntilLastWinner(BingoSubsystem(initialState)).toLong()
    }

    override fun printResult(input: List<String>) {
        println("\n--- Day 4: Giant Squid ---\n")
        println("Part 1: the winning score is ${part1(input)}")
        println("Part 2: the final score of the last board is ${part2(input)}")
    }

    private class InputParser(val inputLines: List<String>) {
        fun parse(): InputParserResult = parseHead(inputLines)

        private fun parseHead(list: List<String>): InputParserResult = InputParserResult(
            numbers = list.head()
                .split(",")
                .map { it.toInt() },
            boards = parseBoards(BoardBuilder(), list.tail()).filter { it.containsRows() }
        )

        private fun parseBoards(builder: BoardBuilder, list: List<String>): List<BingoBoard> {
            if (list.isEmpty()) return listOf(builder.build())
            val line = list.head()
            return if (line.isEmpty()) {
                listOf(builder.build()) + parseBoards(BoardBuilder(), list.tail())
            } else {
                parseBoards(builder.add(line), list.tail())
            }
        }
    }

    private data class InputParserResult(
        val numbers: List<Int>,
        val boards: List<BingoBoard>
    )

    private class BingoBoard(initialState: List<List<Int>>) {
        private val board: Array<IntArray> = initialState.map { it.toIntArray() }.toTypedArray()
        private val marks: Array<BooleanArray> = Array(5) { BooleanArray(5) { false } }
        private var lastMark = 0

        fun containsRows(): Boolean = board.isNotEmpty()

        fun mark(number: Int) {
            lastMark = number
            forEachField { x, y ->
                if (board[x][y] == number) {
                    marks[x][y] = true
                }
            }
        }

        fun isWin(): Boolean = hasWinningRow() || hasWinningColumn()

        fun score(): Int {
            var sum = 0
            forEachField { x, y ->
                if (!marks[x][y]) {
                    sum += board[x][y]
                }
            }
            return sum * lastMark;
        }

        private fun hasWinningRow(): Boolean = (0 until 5).map { marks.getRow(it).allTrue() }.anyTrue()

        private fun hasWinningColumn(): Boolean = (0 until 5).map { marks.getColumn(it).allTrue() }.anyTrue()

        private fun forEachField(block: (x: Int, y: Int) -> Unit) {
            for (x in 0 until 5) {
                for (y in 0 until 5) {
                    block(x,y)
                }
            }
        }

        override fun toString(): String =
            "BingoBoard(board=${board.contentToString()}, marks=${marks.contentToString()}, lastMark=$lastMark)"
    }

    private class BoardBuilder() {

        private val rows: MutableList<List<Int>> = mutableListOf()

        fun add(line: String): BoardBuilder = apply {
            val row = line
                .split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
            rows.add(row)
        }

        fun build(): BingoBoard = BingoBoard(rows)
    }

    private class BingoSubsystem(
        val numbers: List<Int>,
        val boards: List<BingoBoard>
    ) {

        constructor(input: InputParserResult) : this(
            numbers = input.numbers,
            boards = input.boards
        )

        fun winningBoard(): BingoBoard = boards.first { it.isWin() }

        fun loosingBoards(): List<BingoBoard> = boards.filter { !it.isWin() }

        /** Returns remaining numbers not drawn yet */
        fun markUntilFirstWinner(): List<Int> = markUntilFirstWinner(numbers)

        private fun markUntilFirstWinner(list: List<Int>): List<Int> {
            if (list.isEmpty()) return emptyList()
            val rest = list.tail()
            mark(list.head())
            return if (hasWinningBoard()) {
                rest
            } else {
                markUntilFirstWinner(rest)
            }
        }

        private fun mark(number: Int) {
            boards.forEach { it.mark(number) }
        }

        private fun hasWinningBoard(): Boolean = boards.map { it.isWin() }.anyTrue()
    }

    private fun markUntilLastWinner(bingo: BingoSubsystem): Int {
        val rest = bingo.markUntilFirstWinner()
        return if (bingo.boards.size > 1) {
            markUntilLastWinner(BingoSubsystem(
                numbers = rest,
                boards = bingo.loosingBoards()
            ))
        } else {
            bingo.winningBoard().score()
        }
    }

}