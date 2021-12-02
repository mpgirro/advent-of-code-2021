import Util.Companion.readFileFromClasspath

class Day02 {

    private class Submarine {
        private var horizontal = 0
            get() = field
        private var depth = 0
            get() = field
        private var aim = 0
            get() = field

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

        override fun toString(): String {
            return "Submarine(horizontal=$horizontal, depth=$depth, aim=$aim)"
        }
    }

    fun task() {
        val sub = Submarine()

        readFileFromClasspath("day02_input.txt")
            .split("\n")
            .map { it.split(" ") }
            .map { (first, second) -> Pair(first, second.toInt()) }
            .forEach { (command, value) ->
                when (command) {
                    "forward" -> sub.forward(value)
                    "up" -> sub.up(value)
                    "down" -> sub.down(value)
                }
            }

        println("position after following the planned course: ${sub.result()}")
    }

}