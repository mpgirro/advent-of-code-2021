import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Integer.parseInt

class Day05(fileName: String): AdventDay(fileName) {

    private val lines: List<Line> = puzzle
        .map { line -> Line(line) }

    private val grid: Grid = Grid(lines.maxCoordinateX(), lines.maxCoordinateY())

    private data class Grid(val sizeX: Int, val sizeY: Int) {
        private val matrix: Array<Array<Int>> = Array(sizeY+1) { Array(sizeX+1) { 0 } }
        fun get(x: Int, y: Int): Int = matrix[y][x]
        fun set(x: Int, y: Int, value: Int) {
            matrix[y][x] = value
        }
        fun inc(x: Int, y: Int) {
            matrix[y][x] += 1
        }

        fun overlapCount(): Int = matrix.flatten().filter { it > 1 }.count()

        override fun toString(): String =
            matrix.joinToString(separator = "\n") { line ->
                line.joinToString(separator = " ") {
                    it.toString().padStart(3)
                }
            }
    }

    override fun part1(): Int {
        lines.forEach(::fillLine)
        return grid.overlapCount()
    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }

    override fun printResult() {
        println("\n--- Day 5: Hydrothermal Venture ---\n")
        println("Part 1: how many points do at least two lines overlap ${part1()}")
//        println("Part 2: the final score of the last board is ${part2()}")
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

    private fun fillLine(line: Line) {
        val (p1, p2) = line
        if (p1.y == p2.y) {
            val y = p1.y // horizontal: a.y == b.y
            for (x in min(p1.x, p2.x)..max(p1.x, p2.x)) {
                grid.inc(x,y)
            }
        } else if (p1.x == p2.x) {
            // vertical: a.x == b.x
            val x = p1.x
            for (y in min(p1.y, p2.y)..max(p1.y, p2.y)) {
                grid.inc(x,y)
            }
        }
    }

}