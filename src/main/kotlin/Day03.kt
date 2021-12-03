import Util.Companion.readFileFromClasspath

class Day03 {

    private enum class Bit(public val bit: Char) {
        ZERO('0'), ONE('1');
    }

    private val diagnosticReport = readFileFromClasspath("day03_example.txt")
        .split("\n")

    private class BinaryDiagnostic(val binaryReport: List<String>) {

        // Assume all diagnostic values have the same length!
        private val lineLength: Int = binaryReport[0].length



        fun powerConsumption(): Int = gammaRate() * epsilonRate()

        fun lifeSupportRating(): Int = oxygenGeneratorRating() * cO2ScrubberRating()

        private fun gammaRate(): Int {
            val result = buildString {
                for (i in 0 until lineLength) {
                    append(mostCommonBit(i))
                }
            }

            return Integer.parseInt(result, 2)
        }

        private fun epsilonRate(): Int {
            val result = buildString {
                for (i in 0 until lineLength) {
                    append(leastCommonBit(i))
                }
            }

            return Integer.parseInt(result, 2)
        }

        private fun oxygenGeneratorRating(): Int = foo(0, binaryReport, ::mostCommonBit)

        private fun cO2ScrubberRating(): Int = foo(0, binaryReport, ::leastCommonBit)

        private fun foo(i: Int, xs: List<String>, bitCriteria: (Int) -> Char): Int {
            if (i > lineLength) throw IllegalStateException("Index got too large")
            val ys = xs.filter { it[i] == bitCriteria(i) }
            return if (ys.size > 1) {
                foo(i+1, ys, bitCriteria)
            } else if (ys.size == 1) {
                Integer.parseInt(ys[0], 2)
            } else {
                throw IllegalStateException("List must not be empty")
            }
        }

        /**
         * Calculate the most common bit (MCB) of the respective position
         */
        private fun mostCommonBit(position: Int): Char = occurrences(position).mostCommonBit().bit

        /**
         * Calculate the least common bit (LCB) of the respective position
         */
        private fun leastCommonBit(position: Int): Char = occurrences(position).leastCommonBit().bit

        private fun occurrences(position: Int): Map<Bit, Int> = binaryReport
            .groupingBy { it[position] }
            .eachCount()
            .mapKeys {
                when(it.key) {
                    '1' -> Bit.ONE
                    '0' -> Bit.ZERO
                    else -> throw IllegalStateException("Invalid character encountered")
                }
            }

        fun Map<Bit, Int>.moreImportantBit(comparator: (Int, Int) -> Boolean): Bit  {
            val ones = get(Bit.ONE) ?: throw IllegalStateException("No 1s counted")
            val zeros = get(Bit.ZERO) ?: throw IllegalStateException("No 0s counted")
            return if (comparator(ones, zeros)) Bit.ONE else Bit.ZERO
        }

        fun Map<Bit, Int>.mostCommonBit(): Bit  = moreImportantBit { ones, zero -> ones > zero }

        fun Map<Bit, Int>.leastCommonBit(): Bit  = moreImportantBit { ones, zero -> ones <= zero }
    }

    fun task1() {
        val diagnostic = BinaryDiagnostic(diagnosticReport)
        println("the power consumption of the submarine is: ${diagnostic.powerConsumption()}")
    }

    fun task2() {
        val diagnostic = BinaryDiagnostic(diagnosticReport)
        println("the life support rating of the submarine is: ${diagnostic.lifeSupportRating()}")
    }

}