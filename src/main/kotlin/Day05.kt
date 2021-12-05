import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Integer.parseInt

class Day05(fileName: String): AdventDay(fileName) {

    private val lines: List<Line> = puzzle
        .map { line -> Line(line) }

    private val simpleMappingSystem = VentMappingSystem(lines)
    private val advancedMappingSystem = AdvancedVentMappingSystem(lines)

    override fun part1(): Int {
        simpleMappingSystem.fillLines(lines)
        return simpleMappingSystem.overlapCount()
    }

    override fun part2(): Int {
        advancedMappingSystem.fillLines(lines)
        return advancedMappingSystem.overlapCount()
    }

    override fun printResult() {
        println("\n--- Day 5: Hydrothermal Venture ---\n")
        println("Part 1: how many points do at least two lines overlap ${part1()}")
        println("Part 2: how many points do at least two lines overlap ${part2()}")
    }

    private data class Line(val p1: Point, val p2: Point) {
        constructor(line: String) : this(
            p1 = Point(line.split(" -> ")[0]),
            p2 = Point(line.split(" -> ")[1])
        )
    }

    private data class Point(val x: Int, val y: Int) {
        constructor(line: String) : this(
            x = parseInt(line.split(",")[0]),
            y = parseInt(line.split(",")[1]),
        )
    }

    private open class VentMappingSystem(private val input: List<Line>) {
        private val sizeX = input.maxCoordinateX() + 1
        private val sizeY = input.maxCoordinateY() + 1
        private val grid: Array<Array<Int>> = Array(sizeY) { Array(sizeX) { 0 } }

        fun overlapCount(): Int = grid.flatten().count { it > 1 }

        override fun toString(): String =
            grid.joinToString(separator = "\n") { line ->
                line.joinToString(separator = " ") {
                    it.toString().padStart(3)
                }
            }

        fun fillLines(lines: List<Line>) = lines.forEach(::fillLine)

        protected open fun fillLine(line: Line) {
            val (p1, p2) = line
            if (p1.y == p2.y) {
                // horizontal: a.y == b.y
                val y = p1.y
                for (x in min(p1.x, p2.x)..max(p1.x, p2.x)) {
                    inc(x,y)
                }
            } else if (p1.x == p2.x) {
                // vertical: a.x == b.x
                val x = p1.x
                for (y in min(p1.y, p2.y)..max(p1.y, p2.y)) {
                    inc(x,y)
                }
            }
        }

        protected fun inc(x: Int, y: Int) {
            grid[y][x] += 1
        }

        private fun List<Line>.maxCoordinate(find: (List<Point>) -> Int): Int {
            val list = mutableListOf<Point>()
            forEach { line ->
                run {
                    list.add(line.p1)
                    list.add(line.p2)
                }
            }
            return find(list)
        }

        private fun List<Line>.maxCoordinateX(): Int = maxCoordinate(::maxX)

        private fun List<Line>.maxCoordinateY(): Int = maxCoordinate(::maxY)

        private fun maxX(list: List<Point>): Int =
            list.map { it.x }.maxOrNull() ?: throw IllegalArgumentException("No coordinate points available")

        private fun maxY(list: List<Point>): Int =
            list.map { it.y }.maxOrNull() ?: throw IllegalArgumentException("No coordinate points available")
    }

    private class AdvancedVentMappingSystem(private val input: List<Line>) : VentMappingSystem(input) {
        override fun fillLine(line: Line) {
            super.fillLine(line)
            val (p1, p2) = line
            if (p1.x < p2.x && p1.y < p2.y) {
                var x = p1.x
                var y = p1.y
                while (x <= p2.x && y <= p2.y) {
                    inc(x,y)
                    x += 1
                    y += 1
                }
            } else if (p1.x < p2.x && p1.y > p2.y) {
                var x = p1.x
                var y = p1.y
                while (x <= p2.x && y >= p2.y) {
                    inc(x,y)
                    x += 1
                    y -= 1
                }
            } else if (p1.x > p2.x && p1.y < p2.y) {
                var x = p1.x
                var y = p1.y
                while (x >= p2.x && y <= p2.y) {
                    inc(x,y)
                    x -= 1
                    y += 1
                }
            } else if (p1.x > p2.x && p1.y > p2.y) {
                var x = p1.x
                var y = p1.y
                while (x >= p2.x && y >= p2.y) {
                    inc(x,y)
                    x -= 1
                    y -= 1
                }
            }
        }
    }
}