class Day11 : AdventDay(day = "day11") {

    override fun part1(input: List<String>): Long = LightSimulation(grid(input)).simulateN(100).toLong()

    override fun part2(input: List<String>): Long = LightSimulation(grid(input)).simulateUntilAllFlash().toLong()

    override fun printResult(input: List<String>) {
        println("\n--- Day 11: Dumbo Octopus ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private fun grid(input: List<String>): Array<Array<Octopus>> {
        val energy: Array<IntArray> = input
            .map { it.toList() }
            .map { it.map(Character::getNumericValue) }
            .map { it.toIntArray() }
            .toTypedArray()
        return Array(10) { y ->
            Array(10) { x ->
                Octopus(x, y, energy[y][x])
            }
        }
    }

    private data class Octopus(val x: Int, val y: Int, private val initialEnergy: Int) {
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

        private val allOctopuses: List<Octopus> =  grid.map { line -> line.toList() }.toList().flatten()

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
            forEachOctopus {
                it.increaseEnergy()
            }

            // Step 2) Flash each octopus at most once if energy level is high enough
            val flashedOctopuses = mutableSetOf<Octopus>()
            forEachOctopus { octopus ->
                if (octopus.isFlash() && octopus !in flashedOctopuses) {
                    flashedOctopuses.add(octopus)

                    val remainingOctopuses = mutableStackOf(octopus)
                    while (remainingOctopuses.isNotEmpty()) {
                        adjacent(remainingOctopuses.pop())
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
            allOctopuses
                .filter { it.isFlash() }
                .forEach { it.reset() }

            // Report how many flashed
            return flashedOctopuses.size
        }

        private fun forEachOctopus(block: (Octopus) -> Unit) = allOctopuses.forEach(block)

        private fun <T> mapEachOctopus(block: (Octopus) -> T): List<T> = allOctopuses.map(block)

        private fun octopus(x: Int, y: Int): Octopus? {
            if (x < 0 || x > 9) return null
            if (y < 0 || y > 9) return null
            return grid[y][x]
        }

        private fun adjacent(octopus: Octopus): List<Octopus> {
            val (x,y) = octopus
            return listOfNotNull(
                octopus(x - 1, y - 1),
                octopus(x, y - 1),
                octopus(x + 1, y - 1),
                octopus(x - 1, y),
                octopus(x + 1, y),
                octopus(x - 1, y + 1),
                octopus(x, y + 1),
                octopus(x + 1, y + 1)
            )
        }

        private fun allFlash(): Boolean = mapEachOctopus { it.hasFlashed() }.all { it }

        override fun toString(): String =
            grid.joinToString(separator = "\n") { line ->
                line.joinToString(separator = "", transform = { "${it.energyLevel()}" })
            }
    }

}