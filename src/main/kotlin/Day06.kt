class Day06 : AdventDay(day = "day06") {

    override fun part1(input: List<String>): Long = LanternfishEvolution(initialFish(input)).cycle(80)

    override fun part2(input: List<String>): Long = LanternfishEvolution(initialFish(input)).cycle(256)

    override fun printResult(input: List<String>) {
        println("\n--- Day 6: Lanternfish ---\n")
        println("Part 1: after 80 days the amount of Lanternfish would be: ${part1(input)}")
        println("Part 2: after 256 days the amount of Lanternfish would be: ${part2(input)}")
    }

    private fun initialFish(input: List<String>): List<Int> = input
        .flatMap { it.split(",") }
        .map { it.toInt() }

    private class LanternfishEvolution(private val fish: List<Int>) {

        fun cycle(days: Int): Long = tick(days, timerMap(fish)).values.sum()

        private tailrec fun tick(day: Int, timerMap: Map<Int, Long>): Map<Int, Long> {
            if (day == 0) return timerMap
            val updatedMap = mutableMapOf<Int, Long>()
            for (timer in 0..8) {
                when (timer) {
                    6 -> updatedMap[timer] = timerMap.valueOrZero(7) + timerMap.valueOrZero(0)
                    8 -> updatedMap[timer] = timerMap.valueOrZero(0)
                    else -> updatedMap[timer] = timerMap.valueOrZero(timer + 1)
                }
            }
            return tick(day-1, updatedMap)
        }

        private fun timerMap(fish: List<Int>): Map<Int, Long> = fish.groupingBy { it }.eachCount() as Map<Int, Long>

        private fun Map<Int, Long>.valueOrZero(key: Int) = this.getOrDefault(key, 0L)

    }

}