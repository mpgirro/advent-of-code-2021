import kotlin.math.abs

class Day07 : AdventDay(day = "day07") {

    override fun part1(input: List<String>): Long =
        findMinimalFuelRequirements(initialPositions(input), ::identity).toLong()

    override fun part2(input: List<String>): Long =
        findMinimalFuelRequirements(initialPositions(input), ::sumOfOneToN).toLong()

    override fun printResult(input: List<String>) {
        println("\n--- Day 7: The Treachery of Whales ---\n")
        println("Part 1: the horizontal position with the least constant fuel requirement is ${part1(input)}")
        println("Part 2: the horizontal position with the least growing fuel requirement is ${part2(input)}")
    }

    private fun initialPositions(input: List<String>): List<Int> = input
        .flatMap { it.split(",") }
        .map { it.toInt() }

    private fun positionRange(positions: List<Int>): IntRange =
        (positions.minOrZero()..positions.maxOrZero())

    private fun findMinimalFuelRequirements(positions: List<Int>, cost: (Int) -> Int): Int =
        positionRange(positions)
            .map { targetPosition -> totalFuelRequirements(positions, targetPosition, cost) }
            .minOrZero()

    private fun totalFuelRequirements(
        positions: List<Int>,
        targetPosition: Int,
        cost: (Int) -> Int
    ): Int = positions.sumOf { aPosition -> cost(abs(aPosition - targetPosition)) }

    private fun <T> identity(t: T): T = t

    /** Sum of 1 to n, courtesy of Gauss */
    private fun sumOfOneToN(n: Int): Int = n * (n + 1) / 2

    private fun List<Int>.minOrZero(): Int = minOrNull() ?: 0
    private fun List<Int>.maxOrZero(): Int = maxOrNull() ?: 0

}