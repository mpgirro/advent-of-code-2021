class Day09 : AdventDay(day = "day09") {

    override fun part1(input: List<String>): Long {
        val points = heightMap(input)
        return points
            .mapEachField { x, y -> points.riskLevel(x,y) }
            .sum()
            .toLong()
    }

    override fun part2(input: List<String>): Long {
        val points = heightMap(input)
        val basins = mutableListOf<Int>()
        points.forEachField { x, y ->
            if (points.isLowPoint(x, y)) {
                basins.add(points.basinSize(x, y))
            }
        }
        return basins
            .sortedDescending()
            .take(3)
            .reduce(Math::multiplyExact)
            .toLong()
    }

    override fun printResult(input: List<String>) {
        println("\n--- Day 9: Smoke Basin ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private fun heightMap(input: List<String>): HeightMap {
        val fields = input
            .map { it.toList() }
            .map { it.map(Character::getNumericValue) }
            .map { it.toIntArray() }
            .toTypedArray()

        return HeightMap(fields)
    }

    private data class Point(val x: Int, val y: Int, val height: Int) {
        constructor(x: Int, y: Int, heightInitializer: (Int,Int) -> Int) : this(x, y, heightInitializer(x,y))
    }

    private class HeightMap(private val fields: Array<IntArray>) {
        
        fun riskLevel(x: Int, y: Int): Int =
            if (isLowPoint(x, y)) {
                1 + fields[y][x]
            } else {
                0
            }

        fun mapEachField(block: (Int,Int) -> Int): List<Int> {
            val list = mutableListOf<Int>()
            for (y in fields.indices) {
                for (x in fields[y].indices) {
                    list.add(block(x,y))
                }
            }
            return list
        }

        fun forEachField(block: (Int, Int) -> Unit) {
            for (y in fields.indices) {
                for (x in fields[y].indices) {
                    block(x,y)
                }
            }
        }

        private fun height(x: Int, y: Int): Int =
            fields.getOrNull(y)?.getOrNull(x) ?: -1

        fun isLowPoint(x: Int, y: Int): Boolean {
            val point = fields[y][x]
            val up    = height(x, y - 1)
            val down  = height(x, y + 1)
            val right = height(x + 1, y)
            val left  = height(x - 1, y)

            if (up in 0..point) return false
            if (down in 0..point) return false
            if (right in 0..point) return false
            if (left in 0..point) return false

            return true
        }

        fun basinSize(
            x: Int,
            y: Int,
            basinMap: MutableMap<Point, Boolean> = mutableMapOf()
        ): Int {
            listOf(
                Point(x, y - 1, ::height), // up
                Point(x, y + 1, ::height), // down
                Point(x + 1, y, ::height), // right
                Point(x - 1, y, ::height)  // left
            ).forEach {
                if (it.height in 0..8 && !basinMap.containsKey(it)) {
                    basinMap[it] = true
                    basinSize(it.x, it.y, basinMap)
                }
            }

            return basinMap.size
        }
    }

}