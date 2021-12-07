class Day01 : AdventDay(day = "day01") {

    override fun part1(input: List<String>): Long = countWindows(1, measurements(input))

    override fun part2(input: List<String>): Long = countWindows(3, measurements(input))

    override fun printResult(input: List<String>) {
        println("\n--- Day 1: Sonar Sweep ---\n")
        println("Part 1: the measurements that are larger than the previous measurement is ${part1(input)}")
        println("Part 2: the measurements that are larger than the previous measurement is ${part2(input)}")
    }

    private fun measurements(input: List<String>): List<Int> = input.map { it.toInt() }

    private fun countWindows(n: Int, ls: List<Int>): Long {
        val a = ls.head(n)
        val b = ls.tail().head(n)

        return if (a.isEmpty() || b.isEmpty()) {
          0
        } else if (a.sum() < b.sum()) {
            1 + countWindows(n, ls.tail())
        } else {
            countWindows(n, ls.tail())
        }
    }

}