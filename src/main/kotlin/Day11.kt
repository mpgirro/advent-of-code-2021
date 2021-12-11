class Day11 : AdventDay(day = "day11") {

    override fun part1(input: List<String>): Long = LightSimulation(grid(input)).simulateN(100).toLong()

    override fun part2(input: List<String>): Long = LightSimulation(grid(input)).simulateUntilAllFlash().toLong()

    override fun printResult(input: List<String>) {
        println("\n--- Day 11: Dumbo Octopus ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private class Octopus(val x: Int, val y: Int, initialEnergy: Int) {
        private var energyLevel = initialEnergy
        fun energyLevel(): Int = energyLevel
        fun increaseEnergy() = apply { energyLevel += 1 }
        fun isFlash(): Boolean = energyLevel > 9
        fun hasFlashed(): Boolean = energyLevel == 0
        fun reset() {
            energyLevel = 0
        }
    }

    private class LightSimulation(private val grid: Array<Array<Octopus>>) {

        fun simulateN(steps: Int): Int = if (steps > 0){
            simulateStep() + simulateN(steps-1)
        } else {
            0
        }

        fun simulateUntilAllFlash(): Int {
            var count = 0
            while (!allFlash()) {
                simulateStep()
                count += 1
            }
            return count
        }

        private fun simulateStep(): Int {

            // Step 1) Increase energy level of each octopus by 1
            forEachOctopus { octopus ->
                octopus.increaseEnergy()
            }

            // Step 2) Flash each octopus at most once if energy level is high enough
            val flashedOctopuses = mutableSetOf<Octopus>()
            forEachOctopus { octopus ->
                if (octopus.isFlash() && octopus !in flashedOctopuses) {
                    val remainingOctopuses = mutableStackOf(octopus)
                    flashedOctopuses.add(octopus)
                    while (remainingOctopuses.isNotEmpty()) {
                        val nextOctopus = remainingOctopuses.pop()
                        adjacent(nextOctopus)
                            .filter { it !in flashedOctopuses }
                            .map { it.increaseEnergy() }
                            .filter { it.isFlash() }
                            .forEach {
                                remainingOctopuses.push(it)
                                flashedOctopuses.add(it)
                            }
                    }
                }
            }

            // Step 3) Set energy level of all flashed octopuses to 0
            forEachOctopus { octopus ->
                if (octopus.isFlash()) {
                    octopus.reset()
                }
            }

            // Report how many flashed
            return flashedOctopuses.size
        }

        private fun forEachOctopus(block: (Octopus) -> Unit) {
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    val octopus = grid[y][x]
                    block(octopus)
                }
            }
        }

        fun <T> mapEachOctopus(block: (Octopus) -> T): List<T> {
            val list = mutableListOf<T>()
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    val octopus = grid[y][x]
                    list.add(block(octopus))
                }
            }
            return list
        }

        private fun octopus(x: Int, y: Int): Octopus? {
            if (x < 0 || x > 9) return null
            if (y < 0 || y > 9) return null
            return grid[y][x]
        }

        private fun adjacent(octopus: Octopus): List<Octopus> =
            listOfNotNull(
                octopus(octopus.x - 1, octopus.y - 1),
                octopus(octopus.x, octopus.y - 1),
                octopus(octopus.x + 1, octopus.y - 1),
                octopus(octopus.x - 1, octopus.y),
                octopus(octopus.x + 1, octopus.y),
                octopus(octopus.x - 1, octopus.y + 1),
                octopus(octopus.x, octopus.y + 1),
                octopus(octopus.x + 1, octopus.y + 1)
            )

        private fun allFlash(): Boolean = mapEachOctopus { it.hasFlashed() }.all { it }

        override fun toString(): String =
            grid.joinToString(separator = "\n") { line ->
                line.joinToString(separator = "", transform = { "${it.energyLevel()}" })
            }
    }

    private fun grid(input: List<String>): Array<Array<Octopus>> {
        val energy: Array<IntArray> = input
            .map { it.toList() }
            .map { it.map(Character::getNumericValue) }
            .map { it.toIntArray() }
            .toTypedArray()
        return Array(10) { y ->
            Array(10) { x ->
                Octopus(x,y,energy[y][x])
            }
        }
    }

}