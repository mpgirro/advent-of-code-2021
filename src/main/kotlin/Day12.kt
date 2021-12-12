class Day12 : AdventDay(day = "day12") {

    override fun part1(input: List<String>): Long = caveSystem(input).pathsWithOneVisitOfSmallCaves().toLong()

    override fun part2(input: List<String>): Long = caveSystem(input).pathsWithOneExtraVisitOfSingleSmallCave().toLong()

    override fun printResult(input: List<String>) {
        println("\n--- Day 12: Passage Pathing ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private fun caveSystem(input: List<String>): CaveSystem {
        val caves = mutableListOf<Cave>()
        val connections = mutableMapOf<Cave, MutableList<Cave>>()
        input.map { line ->
            val (leftName, rightName) = line.split("-")
            val left = Cave(leftName)
            val right = Cave(rightName)

            caves.addIfNotPresent(left)
            caves.addIfNotPresent(right)

            connections.putIfNotPresent(left, right)
            connections.putIfNotPresent(right, left)
        }

        return CaveSystem(caves, connections)
    }

    private data class Cave(val name: String) {
        val isStart: Boolean = name == "start"
        val isEnd: Boolean = name == "end"
        fun isBigCave(): Boolean = name.all { it.isUpperCase() }
        fun isSmallCave(): Boolean = name.all { it.isLowerCase() }
    }

    private data class CaveSystem(
        val caves: List<Cave>,
        val connections: Map<Cave, List<Cave>>
    ) {
        private val start: Cave = caves.first { it.isStart }

        fun pathsWithOneVisitOfSmallCaves(): Int = depthsFirstSearch(start, listOf(), false).size

        fun pathsWithOneExtraVisitOfSingleSmallCave(): Int = depthsFirstSearch(start, listOf(), true).size

        private fun depthsFirstSearch(node: Cave, previousPath: List<Cave>, visitSmallTwice: Boolean): List<List<Cave>> {
            val path = previousPath + node
            if (node.isEnd) return listOf(path)
            return connections(node)
                .filterNot { next ->
                    next.isStart || path.notQualifiedForVisit(next, visitSmallTwice)
                }
                .flatMap {
                    depthsFirstSearch(it, path, visitSmallTwice)
                }
        }

        private fun connections(cave: Cave): List<Cave> = connections.getValue(cave)

        private fun List<Cave>.notQualifiedForVisit(cave: Cave, visitSmallTwice: Boolean): Boolean =
            cave in this && cave.isSmallCave() && mayVisitTwice(visitSmallTwice)

        private fun List<Cave>.mayVisitTwice(visitSmallTwice: Boolean): Boolean =
            if (visitSmallTwice) {
                this.filter { it.isSmallCave() }
                    .groupingBy { it }
                    .eachCount()
                    .values
                    .any { it > 1 }
            } else {
                true
            }

    }

    private fun MutableList<Cave>.addIfNotPresent(cave: Cave) {
        if (!contains(cave)) add(cave)
    }

    private fun MutableMap<Cave, MutableList<Cave>>.putIfNotPresent(key: Cave, value: Cave) {
        if (key !in this) put(key, mutableListOf())
        getValue(key).addIfNotPresent(value)
    }

}