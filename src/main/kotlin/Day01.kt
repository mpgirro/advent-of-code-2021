import Util.Companion.readFileFromClasspath

class Day01 {

    private val measurements: List<Int> = readFileFromClasspath("day01_input.txt")
        .split("\n")
        .map { it.toInt() }
    
    /** Correct result is: 1624 */
    fun challenge1() {
        var prev = -1
        var count = 0

        for (measurement in measurements) {
            if (prev != -1 && prev < measurement) {
                count += 1
            }
            prev = measurement
        }

        println("(challenge1) measurements that are larger than the previous measurement: $count")
    }

    /** Correct result is: 1653 */
    fun challenge2() {
        println("(challenge2) measurements that are larger than the previous measurement: ${countWindows(measurements)}")
    }

    private fun countWindows(ls: List<Int>): Int {

        val a = ls.head3()
        val b = ls.tail().head3()

        return if (a.isEmpty() || b.isEmpty()) {
          0
        } else if (a.sum() < b.sum()) {
            1 + countWindows(ls.tail())
        } else {
            countWindows(ls.tail())
        }
    }

    private fun <T> List<T>.head3() = if (size >= 3) subList(0, 3) else emptyList()

    private fun <T> List<T>.tail() = drop(1)

}