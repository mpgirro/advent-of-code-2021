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
            for (x in 0 until 5) {
                for (y in 0 until 5) {
                    if (board[x][y] == number) {
                        marks[x][y] = true
                    }
                }
            }
        }
        fun isWin(): Boolean = hasWinningRow() || hasWinningColumn()
        fun score(): Int {
            var sum = 0
            for (x in 0 until 5) {
                for (y in 0 until 5) {
                    if (marks[x][y] == false) {
                        sum += board[x][y]
                    }
                }
            }
            return sum * lastMark;
        }
        private fun hasWinningRow(): Boolean = (0 until 5).map { marks.getRow(it).allTrue() }.anyTrue()
        private fun hasWinningColumn(): Boolean = (0 until 5).map { marks.getColumn(it).allTrue() }.anyTrue()
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

    private class BingoSubsystem(input: InputParserResult) {
        private val numbers: List<Int> = input.numbers
        private val boards: List<BingoBoard> = input.boards

        fun markUntilWinner() {
            for (number in numbers) {
                mark(number)
                if (hasWinningBoard()) break;
            }
        }

        fun mark(number: Int) {
            boards.forEach { it.mark(number) }
        }
        fun hasWinningBoard(): Boolean = boards.map { it.isWin() }.anyTrue()
        fun getWinningScore(): Int = boards.filter { it.isWin() }.first().score()
    }

    fun task1() {
        val input = InputParser(puzzle).parse()
        val bingo = BingoSubsystem(input)
        bingo.markUntilWinner()
        println("the winning score is: ${bingo.getWinningScore()}")
    }

}