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
            if (list.isEmpty()) return emptyList()
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

    private class BingoBoard(val rows: List<List<Int>>) {
        fun containsRows(): Boolean = rows.isNotEmpty()
        override fun toString(): String {
            return "BingoBoard(rows=$rows)"
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

    fun task1() {
        println(InputParser(puzzle).parse())
    }

}