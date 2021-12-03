import Util.Companion.readFileFromClasspath
import java.lang.Integer.parseInt

class Day03 {

    private val diagnosticReport = readFileFromClasspath("day03_input.txt")
        .split("\n")

    private class BinaryDiagnostic(val binaryReport: List<String>) {

        val gammaRate: Int by lazy { parseInt(gammaRate(0), 2) }

        val epsilonRate: Int by lazy { parseInt(epsilonRate(0), 2) }

        val oxygenGeneratorRating: Int by lazy { parseInt(oxygenGeneratorRating(0, binaryReport), 2) }

        val co2ScrubberRating: Int by lazy { parseInt(co2ScrubberRating(0, binaryReport), 2) }

        val powerConsumption: Int by lazy { gammaRate * epsilonRate }

        val lifeSupportRating: Int by lazy { oxygenGeneratorRating * co2ScrubberRating }

        // Assume all diagnostic values have the same length!
        private val lineLength: Int by lazy { binaryReport[0].length }

        private fun gammaRate(i: Int): String {
            if (i >= lineLength) return ""
            return mostCommonBitAtPosition(i) + gammaRate(i+1)
        }

        private fun epsilonRate(i: Int): String {
            if (i >= lineLength) return ""
            return leastCommonBitAtPosition(i) + epsilonRate(i+1)
        }

        private fun occurrencesAtPosition(position: Int, list: List<String>): Pair<Int, Int> {
            val occurrences = list
                .groupingBy { it[position] }
                .eachCount()

            val count1 = occurrences['1'] ?: throw IllegalStateException("No 1s counted")
            val count0 = occurrences['0'] ?: throw IllegalStateException("No 0s counted")

            return Pair(count1, count0)
        }

        /** Calculate the most common bit (MCB) of the respective position */
        private fun mostCommonBitAtPosition(position: Int): Char {
            val (count1, count0) = occurrencesAtPosition(position, binaryReport)
            return if (count1 > count0) '1' else '0'
        }

        /** Calculate the least common bit (LCB) of the respective position */
        private fun leastCommonBitAtPosition(position: Int): Char {
            val (count1, count0) = occurrencesAtPosition(position, binaryReport)
            return if (count1 > count0) '0' else '1'
        }

        private fun is1(i: Int): (String) -> Boolean = { s -> s[i] == '1' }

        private fun is0(i: Int): (String) -> Boolean = { s -> s[i] == '0' }

        private fun oxygenGeneratorRating(i: Int, list: List<String>): String {
            if (list.size == 1) return list[0]
            val (count1, count0) = occurrencesAtPosition(i, list)
            val criteria: (String) -> Boolean = if (count1 >= count0) is1(i) else is0(i)
            return oxygenGeneratorRating(i+1, list.filter(criteria))
        }

        private fun co2ScrubberRating(i: Int, list: List<String>): String {
            if (list.size == 1) return list[0]
            val (count1, count0) = occurrencesAtPosition(i, list)
            val criteria: (String) -> Boolean = if (count1 < count0) is1(i) else is0(i)
            return co2ScrubberRating(i+1, list.filter(criteria))
        }
    }

    fun task1() {
        val diagnostic = BinaryDiagnostic(diagnosticReport)
        println("the power consumption of the submarine is: ${diagnostic.powerConsumption}")
        println("\tgamma rate: ${diagnostic.gammaRate}")
        println("\tepsilon rate: ${diagnostic.epsilonRate}")
    }

    fun task2() {
        val diagnostic = BinaryDiagnostic(diagnosticReport)
        println("the life support rating of the submarine is: ${diagnostic.lifeSupportRating}")
        println("\toxygen generator rating: ${diagnostic.oxygenGeneratorRating}")
        println("\tCO2 scrubber rating: ${diagnostic.co2ScrubberRating}")
    }

}