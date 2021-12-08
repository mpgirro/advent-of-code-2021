class Day08 : AdventDay(day = "day08") {

    override fun part1(input: List<String>): Long =
        input
            .map(::parse)
            .sumOf { it.uniqueSequenceNumberCount }
            .toLong()

    override fun part2(input: List<String>): Long =
        input
            .map(::parse)
            .sumOf { it.decodedValue }
            .toLong()

    override fun printResult(input: List<String>) {
        println("\n--- Day 8: Seven Segment Search ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private fun parse(inputLine: String): SevenSegmentDisplay {
        val (left, right) = inputLine.split('|').map { it.trim() }
        val segments = left.asDigits()
        val output = right.asDigits()

        return SevenSegmentDisplay(SevenSegmentWiring(segments), output)
    }

    private data class SevenSegmentDigit(val segments: Set<Char>) {
        val size: Int = segments.size
        fun subtract(other: SevenSegmentDigit): SevenSegmentDigit = SevenSegmentDigit(segments.subtract(other.segments))
    }

    private fun String.asDigits(): List<SevenSegmentDigit> =
        split(' ')
            .map { it.toSet() }
            .map { SevenSegmentDigit(it) }

    private class SevenSegmentWiring(digits: List<SevenSegmentDigit>) {

        private val digitMap: Map<Int, List<SevenSegmentDigit>> = digits.groupBy { it.size }
        private val lookupBuffer: MutableMap<Int,SevenSegmentDigit> = mutableMapOf()

        private fun digit(intValue: Int, producer: () -> SevenSegmentDigit): SevenSegmentDigit =
            lookupBuffer.getOrPut(intValue) { producer() }

        private val zero: SevenSegmentDigit by lazy {
            digit(0) {
                digitMap.getValue(6).first { it != six && it != nine }
            }
        }

        private val one: SevenSegmentDigit by lazy {
            digit(1) {
                digitMap.getValue(2).first()
            }
        }

        private val two: SevenSegmentDigit by lazy {
            digit(2) {
                digitMap.getValue(5).first { (it.subtract(nine)).size == 1 }
            }
        }

        private val three: SevenSegmentDigit by lazy {
            digit(3) {
                digitMap.getValue(5).first { (it.subtract(seven)).size == 2 }
            }
        }

        private val four: SevenSegmentDigit by lazy {
            digit(4) {
                digitMap.getValue(4).first()
            }
        }

        private val five: SevenSegmentDigit by lazy {
            digit(5) {
                digitMap.getValue(5).first { it != two && it != three }
            }
        }

        private val six: SevenSegmentDigit by lazy {
            digit(6) {
                digitMap.getValue(6).first { (it.subtract(one)).size == 5 }
            }
        }

        private val seven: SevenSegmentDigit by lazy {
            digit(7) {
                digitMap.getValue(3).first()
            }
        }

        private val eight: SevenSegmentDigit by lazy {
            digit(8) {
                digitMap.getValue(7).first()
            }
        }

        private val nine: SevenSegmentDigit by lazy {
            digit(9) {
                digitMap.getValue(6).first { (it.subtract(four).subtract(seven)).size == 1 }
            }
        }

        private val signalPattern: Map<SevenSegmentDigit, Int> =
            mapOf(
                zero  to 0,
                one   to 1,
                two   to 2,
                three to 3,
                four  to 4,
                five  to 5,
                six   to 6,
                seven to 7,
                eight to 8,
                nine  to 9
            )

        fun unknot(digit: SevenSegmentDigit): Int = signalPattern.getValue(digit)

    }

    private class SevenSegmentDisplay(segmentWiring: SevenSegmentWiring, output: List<SevenSegmentDigit>) {

        private val outputMap = output.groupBy { it.size }

        private fun count(value: Int): Int = outputMap[value]?.size ?: 0

        val uniqueSequenceNumberCount: Int =
            count(2) + count(3) + count(4) + count(7)

        val decodedValue: Int =
            output
                .map { segmentWiring.unknot(it) }
                .fold(0) { acc, n -> acc * 10 + n }

    }
}