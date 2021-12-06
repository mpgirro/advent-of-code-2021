class Day06(fileName: String): AdventDay(fileName) {

    private val initialFish: List<Lanternfish> = puzzle
        .flatMap { it.split(",") }
        .map { it.toInt() }
        .map { Lanternfish(it) }

    override fun part1(): Int = cycle(80, initialFish)

    override fun part2(): Int = cycle(256, initialFish)

    override fun printResult() {
        println("\n--- Day 6: Lanternfish ---\n")
        println("Part 1: after 80 days the amount of lanternfish would be: ${part1()}")
        println("Part 2: after 256 days the amount of lanternfish would be: ${part2()}")
    }

    private class Lanternfish(val timer: Int) {
        fun tick(): List<Lanternfish> {
            val newTimer = timer - 1
            return if (newTimer >= 0) {
                listOf(Lanternfish(newTimer))
            } else {
                listOf(Lanternfish(6), Lanternfish(8))
            }
        }
    }

    private fun cycle(daysLeft: Int, fish: List<Lanternfish>): Int {
        return if (daysLeft > 0) {
            cycle(daysLeft-1, fish.map { it.tick() }.flatten())
        } else {
            fish.size
        }
    }
}