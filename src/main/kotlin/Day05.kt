import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Integer.parseInt

class Day05(fileName: String): AdventDay(fileName) {

    private val lines: List<Line> = puzzle
        .map { line -> Line(line) }

    private val simpleMappingSystem = SimpleVentMappingSystem(lines.maxCoordinateX(), lines.maxCoordinateY())
    private val advancedMappingSystem = AdvancedVentMappingSystem(lines.maxCoordinateX(), lines.maxCoordinateY())

    private abstract class VentMappingSystem(val sizeX: Int, val sizeY: Int) {
        private val grid: Array<Array<Int>> = Array(sizeY+1) { Array(sizeX+1) { 0 } }

        abstract fun fillLine(line: Line)
        fun fillLines(lines: List<Line>) = lines.forEach(::fillLine)

        fun get(x: Int, y: Int): Int = grid[y][x]
        fun set(x: Int, y: Int, value: Int) {
            grid[y][x] = value
        }
        fun inc(x: Int, y: Int) {
            grid[y][x] += 1
        }

        fun overlapCount(): Int = grid.flatten().count { it > 1 }

        override fun toString(): String =
            grid.joinToString(separator = "\n") { line ->
                line.joinToString(separator = " ") {
                    it.toString().padStart(3)
                }
            }
    }

    private class SimpleVentMappingSystem(sizeX: Int, sizeY: Int) : VentMappingSystem(sizeX, sizeY) {
        override fun fillLine(line: Line) {
            val (p1, p2) = line
            if (p1.y == p2.y) {
                val y = p1.y // horizontal: a.y == b.y
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
    }

    private class AdvancedVentMappingSystem(sizeX: Int, sizeY: Int) : VentMappingSystem(sizeX, sizeY) {
        override fun fillLine(line: Line) {
            val (p1, p2) = line
            if (p1.y == p2.y) {
                val y = p1.y // horizontal: a.y == b.y
                for (x in min(p1.x, p2.x)..max(p1.x, p2.x)) {
                    inc(x,y)
                }
            } else if (p1.x == p2.x) {
                // vertical: a.x == b.x
                val x = p1.x
                for (y in min(p1.y, p2.y)..max(p1.y, p2.y)) {
                    inc(x,y)
                }
            } else {
                if (p1.x < p2.x && p1.y < p2.y) {
                    // inc x from p1.x -> p2.x, inc y from p1.y to p2.y
                    var x = p1.x
                    var y = p1.y
                    while (x <= p2.x && y <= p2.y) {
                        inc(x,y)
                        x += 1
                        y += 1
                    }
                } else if (p1.x < p2.x && p1.y > p2.y) {
                    // inc x from p1.x -> p2.x, inc y from p2.y to p1.y
                    var x = p1.x
                    var y = p1.y
                    while (x <= p2.x && y >= p2.y) {
                        inc(x,y)
                        x += 1
                        y -= 1
                    }
                } else if (p1.x > p2.x && p1.y < p2.y) {
                    // inc x from p2.x -> p1.x, inc y from p1.y to p2.y
                    var x = p1.x
                    var y = p1.y
                    while (x >= p2.x && y <= p2.y) {
                        inc(x,y)
                        x -= 1
                        y += 1
                    }
                } else if (p1.x > p2.x && p1.y > p2.y) {
                    // inc x from p2.x -> p1.x, inc y from p2.y to p1.y
                    var x = p1.x
                    var y = p1.y
                    while (x >= p2.x && y >= p2.y) {
                        inc(x,y)
                        x -= 1
                        y -= 1
                    }
                } else {
                    throw IllegalStateException("This should never happen")
                }
            }
        }
    }

    override fun part1(): Int {
        simpleMappingSystem.fillLines(lines)
        return simpleMappingSystem.overlapCount()
    }

    override fun part2(): Int {
        advancedMappingSystem.fillLines(lines)
        println(advancedMappingSystem)
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
        fun coordinates(): Pair<Point,Point> = Pair(p1, p2)
    }

    private data class Point(val x: Int, val y: Int) {
        constructor(line: String) : this(
            x = parseInt(line.split(",")[0]),
            y = parseInt(line.split(",")[1]),
        )
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