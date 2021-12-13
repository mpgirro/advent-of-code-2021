class Day13 : AdventDay(day = "day13") {

    override fun part1(input: List<String>): Long {
        val (initialPaper, instructions) = parse(input)
        val resultPaper = initialPaper.fold(instructions[0])
        return resultPaper.dots.size.toLong()
    }

    override fun part2(input: List<String>): Long {
        val (initialPaper, instructions) = parse(input)
        val resultPaper = instructions.fold(initialPaper) { paper, instruction -> paper.fold(instruction) }
        println(resultPaper)
        return resultPaper.dots.size.toLong()
    }

    override fun printResult(input: List<String>) {
        println("\n--- Day 13: Transparent Origami ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private fun parse(input: List<String>): Pair<Paper, List<FoldInstruction>> {
        val dots = mutableListOf<Pair<Int,Int>>()
        val foldInstructions = mutableListOf<FoldInstruction>()
        for (line in input) {
            if (line == "") {
                continue
            } else if (line.startsWith("fold along")) {
                val instruction = if (line.startsWith("fold along x=")) {
                    VerticalFoldInstruction(line.split("fold along x=")[1].toInt())
                } else if (line.startsWith("fold along y=")) {
                    HorizontalFoldInstruction(line.split("fold along y=")[1].toInt())
                } else {
                    null
                }
                if (instruction == null) continue
                foldInstructions.add(instruction)
            } else {
                val (x,y) = line.split(",")
                dots.add(Pair(x.toInt(),y.toInt()))
            }
        }

        val positions: List<Dot> = dots.map { (x, y) -> Dot(x,y) }
        return Pair(Paper(positions), foldInstructions)
    }

    private data class Dot(val x: Int, val y: Int)

    private data class Paper(val dots: List<Dot>) {

        private val sizeX: Int = dots.map { it.x }.maxOrNull() ?: 0
        private val sizeY: Int = dots.map { it.y }.maxOrNull() ?: 0

        fun fold(instruction: FoldInstruction): Paper {
            val newDots = dots
                .map { dot -> instruction.fold(dot) }
                .distinct()
            return Paper(newDots)
        }

        private fun contains(x: Int, y: Int): Boolean = dots.contains(Dot(x,y))

        override fun toString(): String = buildString {
            for (y in 0..sizeY) {
                for (x in 0..sizeX) {
                    val symbol = if (contains(x,y)) '█' else '.'
                    append(symbol)
                }
                append("\n")
            }
        }
    }

    private sealed interface FoldInstruction {
        fun fold(dot: Dot): Dot
    }
    private data class VerticalFoldInstruction(val x: Int) : FoldInstruction {
        override fun fold(dot: Dot): Dot =
            if (dot.x > x) {
                Dot(2 * x - dot.x, dot.y)
            } else {
                dot
            }
    }

    private data class HorizontalFoldInstruction(val y: Int) : FoldInstruction {
        override fun fold(dot: Dot): Dot =
            if (dot.y > y) {
                Dot(dot.x, 2 * y - dot.y)
            } else {
                dot
            }
    }
}