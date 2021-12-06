class Day06(fileName: String): AdventDay(fileName) {

    private val initialFish: List<Int> = puzzle
        .flatMap { it.split(",") }
        .map { it.toInt() }

    override fun part1(): Long {
        val fishMap = FishMap(initialFish)
        return fishMap.cycle(80)
    }

    override fun part2(): Long {
        val fishMap = FishMap(initialFish)
        return fishMap.cycle(256)
    }

    override fun printResult() {
        println("\n--- Day 6: Lanternfish ---\n")
        println("Part 1: after 80 days the amount of lanternfish would be: ${part1()}")
        println("Part 2: after 256 days the amount of lanternfish would be: ${part2()}")
    }

    private class FishMap(initial: List<Int>) {
        private var timerMap: Map<Int, Long> = timerMap(initial)

        fun cycle(days: Int): Long {
            for (day in 0 until days) {
                tick()
            }
            return timerMap.values.sum()
        }

        private fun tick() {
            val updatedMap = mutableMapOf<Int, Long>()
            for (timer in 0..8) {
                when (timer) {
                    6 -> updatedMap[timer] = timerMap.valueOrZero(7) + timerMap.valueOrZero(0)
                    8 -> updatedMap[timer] = timerMap.valueOrZero(0)
                    else -> updatedMap[timer] = timerMap.valueOrZero(timer + 1)
                }
            }
            timerMap = updatedMap
        }

        private fun timerMap(fish: List<Int>): Map<Int, Long> = fish.groupingBy { it }.eachCount() as Map<Int, Long>

        private fun Map<Int, Long>.valueOrZero(key: Int) = this.getOrDefault(key, 0L)

    }

}