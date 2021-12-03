import Util.Companion.readFileFromClasspath

class Day03 {

    private class BinaryDiagnostic(val binaryReport: List<String>) {

        fun gammaRate(): Int {
            val line = binaryReport.get(0)
            val result = buildString {
                for (i in 0..line.length - 1) {
                    append(mcb(i))
                }
            }

            return Integer.parseInt(result, 2)
        }

        fun epsilonRate(): Int {
            val line = binaryReport.get(0)
            val result = buildString {
                for (i in 0..line.length - 1) {
                    append(lcb(i))
                }
            }

            return Integer.parseInt(result, 2)
        }

        fun powerConsumption(): Int = gammaRate() * epsilonRate()

        /**
         * Calculate the most common bit (MCB) of the respective position
         */
        private fun mcb(position: Int): Char {
            val occurrences = binaryReport
                .groupingBy { it[position] }
                .eachCount()

            val ones = occurrences.get('1') ?: throw IllegalStateException("No 1s counted")
            val zeros = occurrences.get('0') ?: throw IllegalStateException("No 0s counted")

            return if (ones > zeros) '1' else '0'
        }

        /**
         * Calculate the least common bit (LCB) of the respective position
         */
        private fun lcb(position: Int): Char {
            val occurrences = binaryReport
                .groupingBy { it[position] }
                .eachCount()

            val ones = occurrences.get('1') ?: throw IllegalStateException("No 1s counted")
            val zeros = occurrences.get('0') ?: throw IllegalStateException("No 0s counted")

            return if (ones > zeros) '0' else '1'
        }

    }

    fun task1() {
        val diagnosticReport = readFileFromClasspath("day03_input.txt")
            .split("\n")

        val diagnostic = BinaryDiagnostic(diagnosticReport)

        println("the power consumption of the submarine is: ${diagnostic.powerConsumption()}")
    }

}