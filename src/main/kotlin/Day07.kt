import kotlin.math.abs

class Day07(fileName: String): AdventDay(fileName) {

    private val initialPositions: List<Int> = puzzle
        .flatMap { it.split(",") }
        .map { it.toInt() }

    override fun part1(): Long = findMinimalFuelRequirements(::identity).toLong()

    override fun part2(): Long = findMinimalFuelRequirements(::sumOfOneToN).toLong()

    override fun printResult() {
        println("\n--- Day 7: The Treachery of Whales ---\n")
        println("Part 1: the horizontal position with the least constant fuel requirement is ${part1()}")
        println("Part 2: the horizontal position with the least growing fuel requirement is ${part2()}")
    }

    private val positionRange: IntRange = (initialPositions.minOrZero()..initialPositions.maxOrZero())

    private fun findMinimalFuelRequirements(cost: (Int) -> Int): Int =
        positionRange
            .map { targetPosition -> totalFuelRequirements(targetPosition, cost) }
            .minOrZero()

    private fun totalFuelRequirements(targetPosition: Int, cost: (Int) -> Int): Int =
        initialPositions.sumOf { aPosition -> cost(abs(aPosition - targetPosition)) }

    private fun <T> identity(t: T): T = t

    /** Sum of 1 to n, courtesy of Gauss */
    private fun sumOfOneToN(n: Int): Int = n * (n + 1) / 2

    private fun List<Int>.minOrZero(): Int = minOrNull() ?: 0
    private fun List<Int>.maxOrZero(): Int = maxOrNull() ?: 0

}