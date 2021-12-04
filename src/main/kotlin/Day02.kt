import Util.Companion.readFileFromClasspath

class Day02(inputFileName: String) {

    private val commands: List<Pair<String,Int>> = readFileFromClasspath(inputFileName)
        .split("\n")
        .map { it.split(" ") }
        .map { (first, second) -> Pair(first, second.toInt()) }

    private class SubmarinePart1 {
        private var horizontal = 0
        private var depth = 0

        fun forward(x: Int) {
            horizontal += x
        }

        fun down(x: Int) {
            depth += x
        }

        fun up(x: Int) {
            depth -= x
        }

        fun result(): Int = horizontal * depth

        override fun toString(): String = "Submarine(horizontal=$horizontal, depth=$depth)"
    }

    private class SubmarinePart2 {
        private var horizontal = 0
        private var depth = 0
        private var aim = 0

        fun forward(x: Int) {
            horizontal += x
            depth += aim * x
        }

        fun down(x: Int) {
            aim += x
        }

        fun up(x: Int) {
            aim -= x
        }

        fun result(): Int = horizontal * depth

        override fun toString(): String = "Submarine(horizontal=$horizontal, depth=$depth, aim=$aim)"
    }

    fun part1(): Int {
        val sub = SubmarinePart1()
        commands.forEach { (command, value) ->
                when (command) {
                    "forward" -> sub.forward(value)
                    "up" -> sub.up(value)
                    "down" -> sub.down(value)
                }
            }
        return sub.result()
    }

    fun printPart1() {
        println("Part 1: position after following the planned course: ${part1()}")
    }

    fun part2(): Int {
        val sub = SubmarinePart2()
        commands.forEach { (command, value) ->
                when (command) {
                    "forward" -> sub.forward(value)
                    "up" -> sub.up(value)
                    "down" -> sub.down(value)
                }
            }
        return sub.result()
    }

    fun printPart2() {
        println("Part 2: position after following the planned course: ${part2()}")
    }

}