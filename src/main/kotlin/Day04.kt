import Util.Companion.readFileFromClasspath

class Day04(inputFileName: String) {

    private val puzzle: List<String> = readFileFromClasspath(inputFileName)
        .split("\n")

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

    private class BingoBoard(input: List<List<Int>>) {
        private val board: Array<IntArray> = input.map { it.toIntArray() }.toTypedArray()
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

        override fun toString(): String {
            return "BingoBoard(board=${board.contentToString()}, marks=${marks.contentToString()}, lastMark=$lastMark)"
        }
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

        fun getWinningBoard(): BingoBoard = boards.filter { it.isWin() }.first()

        fun getLoosingBoards(): List<BingoBoard> = boards.filter { !it.isWin() }

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

    fun task1(): Int {
        val input = InputParser(puzzle).parse()
        val bingo = BingoSubsystem(input)
        bingo.markUntilFirstWinner()
        return bingo.getWinningBoard().score()
    }

    fun printTask1() {
        println("the winning score is: ${task1()}")
    }

    fun task2(): Int {
        val input = InputParser(puzzle).parse()
        var bingo = BingoSubsystem(input)

        while (true) {
            val rest = bingo.markUntilFirstWinner()
            if (bingo.boards.size > 1) {
                bingo = BingoSubsystem(
                    numbers = rest,
                    boards = bingo.getLoosingBoards()
                )
                continue
            } else {
                return bingo.getWinningBoard().score()
            }
        }
    }

    fun printTask2() {
        println("the final score of the last board is: ${task2()}")
    }

}