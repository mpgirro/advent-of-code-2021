import Util.Companion.readFileFromClasspath

class Day01 {

    private val measurements: List<Int> = readFileFromClasspath("day01_input.txt")
        .split("\n")
        .map { it.toInt() }

    private fun sum(i: Int, j: Int): Int = measurements.subList(i, j).sum()

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

    fun challenge2() {
        var count = 0
        var i = 0

        while (i + 3 < measurements.size) {

            val sumA = sum(i, i+2)
            val sumB = sum(i+1, i+3)

            if (sumA < sumB) count += 1

            i += 1
        }

        println("(challenge2) measurements that are larger than the previous measurement: $count")
    }

}