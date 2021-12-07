sealed class AdventDay(private val day: String) {

    interface AdventDaySolution {
        fun part1(): Long
        fun part2(): Long
        fun printResult()
    }

    /** Convenience wrapper for running the example input on this Advent Day solution */
    val example = object : AdventDaySolution {
        val input: List<String> = readPuzzleFromClasspath("$day-example.txt")
        override fun part1(): Long = part1(input)
        override fun part2(): Long = part2(input)
        override fun printResult(): Unit = printResult(input)
    }

    /** Convenience wrapper for running the puzzle input on this Advent Day solution */
    val puzzle = object : AdventDaySolution {
        val input: List<String> = readPuzzleFromClasspath("$day-puzzle.txt")
        override fun part1(): Long = part1(input)
        override fun part2(): Long = part2(input)
        override fun printResult(): Unit = printResult(input)
    }

    abstract fun part1(input: List<String>): Long
    abstract fun part2(input: List<String>): Long
    abstract fun printResult(input: List<String>)

    private fun readPuzzleFromClasspath(path: String): List<String> =
        this::class.java.getResource(path)
            .readText(Charsets.UTF_8)
            .split("\n")

}