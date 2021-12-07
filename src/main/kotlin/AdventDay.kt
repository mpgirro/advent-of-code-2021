abstract class AdventDay protected constructor(
    private val day: String
) {

    val example = object : AdventDaySolution {
        val input: List<String> = readPuzzleFromClasspath("$day-example.txt")
        override fun part1(): Long = part1(input)
        override fun part2(): Long = part2(input)
        override fun printResult(): Unit = this@AdventDay.printResult(input)
    }

    val puzzle = object : AdventDaySolution {
        val input: List<String> = readPuzzleFromClasspath("$day-puzzle.txt")
        override fun part1(): Long = part1(input)
        override fun part2(): Long = part2(input)
        override fun printResult(): Unit = this@AdventDay.printResult(input)
    }

    abstract fun part1(input: List<String>): Long
    abstract fun part2(input: List<String>): Long
    abstract fun printResult(input: List<String>)

    private fun readPuzzleFromClasspath(path: String): List<String> =
        this::class.java.getResource(path)
            .readText(Charsets.UTF_8)
            .split("\n")

}