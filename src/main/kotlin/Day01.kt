import Util.Companion.readFileFromClasspath

class Day01 {

    private val measurements: List<Int> = readFileFromClasspath("day01_input.txt")
        .split("\n")
        .map { it.toInt() }

    /** Correct result is: 1624 */
    fun part1() {
        println("(challenge1) measurements that are larger than the previous measurement: ${countWindows(1, measurements)}")
    }

    /** Correct result is: 1653 */
    fun part2() {
        println("(challenge2) measurements that are larger than the previous measurement: ${countWindows(3, measurements)}")
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