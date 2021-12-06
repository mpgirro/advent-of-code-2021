class Day01(fileName: String): AdventDay(fileName) {

    private val measurements: List<Int> = puzzle.map { it.toInt() }

    override fun part1(): Long = countWindows(1, measurements)

    override fun part2(): Long = countWindows(3, measurements)

    override fun printResult() {
        println("\n--- Day 1: Sonar Sweep ---\n")
        println("Part 1: the measurements that are larger than the previous measurement is ${part1()}")
        println("Part 2: the measurements that are larger than the previous measurement is ${part2()}")
    }

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