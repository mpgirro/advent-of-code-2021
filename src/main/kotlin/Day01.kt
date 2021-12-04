import Util.Companion.readFileFromClasspath

class Day01(inputFileName: String) {

    private val measurements: List<Int> = readFileFromClasspath(inputFileName)
        .split("\n")
        .map { it.toInt() }

    fun part1(): Int = countWindows(1, measurements)

    fun printPart1() {
        println("(challenge1) measurements that are larger than the previous measurement: ${part1()}")
    }

    fun part2(): Int = countWindows(3, measurements)

    fun printPart2() {
        println("(challenge2) measurements that are larger than the previous measurement: ${part2()}")
    }

    private fun countWindows(n: Int, ls: List<Int>): Int {
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