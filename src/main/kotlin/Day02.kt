class Day02 : AdventDay(day = "day02") {

    override fun part1(input: List<String>): Long {
        val sub = SubmarinePart1()
        commands(input).forEach { (command, value) ->
            when (command) {
                "forward" -> sub.forward(value)
                "up" -> sub.up(value)
                "down" -> sub.down(value)
            }
        }
        return sub.result()
    }

    override fun part2(input: List<String>): Long {
        val sub = SubmarinePart2()
        commands(input).forEach { (command, value) ->
            when (command) {
                "forward" -> sub.forward(value)
                "up" -> sub.up(value)
                "down" -> sub.down(value)
            }
        }
        return sub.result()
    }

    override fun printResult(input: List<String>) {
        println("\n--- Day 2: Dive! ---\n")
        println("Part 1: position after following the planned course is ${part1(input)}")
        println("Part 2: position after following the planned course is ${part2(input)}")
    }

    private fun commands(input: List<String>): List<Pair<String,Int>> = input
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

        fun result(): Long = (horizontal * depth).toLong()

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

        fun result(): Long = (horizontal * depth).toLong()

        override fun toString(): String = "Submarine(horizontal=$horizontal, depth=$depth, aim=$aim)"
    }

}