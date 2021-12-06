class Day02(fileName: String): AdventDay(fileName) {

    private val commands: List<Pair<String,Int>> = puzzle
        .map { it.split(" ") }
        .map { (first, second) -> Pair(first, second.toInt()) }

    override fun part1(): Long {
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

    override fun part2(): Long {
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

    override fun printResult() {
        println("\n--- Day 2: Dive! ---\n")
        println("Part 1: position after following the planned course is ${part1()}")
        println("Part 2: position after following the planned course is ${part2()}")
    }

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