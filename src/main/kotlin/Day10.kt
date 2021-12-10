class Day10 : AdventDay(day = "day10") {

    override fun part1(input: List<String>): Long {
        val parserResult = parse(input)
        val characterMap = parserResult.illegalChars
            .groupBy { it }
            .map { Pair(it.key,it.value.size) }
        var result = 0

        for ((illegalChar, occurrence) in characterMap) {
            val score: Int = when(illegalChar) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
            result += occurrence * score
        }
        return result.toLong()
    }

    override fun part2(input: List<String>): Long {
        val parserResult = parse(input)
        val scores = parserResult.incompleteLines
            .map { incompleteLine ->
                var totalScore = 0L // Int would overflow for puzzle input!
                for (c in incompleteLine.closingSequence) {
                    val score: Int = when(c) {
                        ')' -> 1
                        ']' -> 2
                        '}' -> 3
                        '>' -> 4
                        else -> 0
                    }
                    totalScore = 5 * totalScore + score
                }
                totalScore
            }
            .sorted()

        return scores[(scores.size-1)/2].toLong()
    }

    override fun printResult(input: List<String>) {
        println("\n--- Day 10: Syntax Scoring ---\n")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }

    private data class ParserResult(
        val validLines: List<String>,
        val illegalChars: List<Char>,
        val incompleteLines: List<IncompleteLine>
    )

    private data class IncompleteLine(val line: String, val closingSequence: String)

    private fun parse(lines: List<String>): ParserResult {
        val validLines = mutableListOf<String>()
        val incompleteLines = mutableListOf<IncompleteLine>()
        val illegalChars = mutableListOf<Char>()

        lineLoop@for (line in lines) {
            val stack = mutableStackOf<Char>()
            for (c in line) {
                when (c) {
                    '(' -> stack.push(')')
                    '[' -> stack.push(']')
                    '{' -> stack.push('}')
                    '<' -> stack.push('>')
                    ')', ']', '}', '>' -> {
                        if (stack.pop() != c) {
                            illegalChars.add(c)
                            continue@lineLoop;
                        }
                    }
                }
            }
            validLines.add(line)

            if (!stack.isEmpty()) {
                incompleteLines.add(IncompleteLine(line, closingSequence(stack)))
            }
        }
        return ParserResult(validLines, illegalChars, incompleteLines)
    }

    private fun closingSequence(stack: Stack<Char>): String =
        stack
            .toList()
            .reversed()
            .joinToString(separator = "")

    private interface Stack<E> {
        fun push(element: E): Boolean
        fun peek(): E
        fun pop(): E
        fun isEmpty(): Boolean
        fun size(): Int
        fun toList(): List<E>
    }

    private class MutableStack<E>(vararg items: E) : Stack<E> {
        private val elements = items.toMutableList()
        override fun push(element: E) = elements.add(element)
        override fun peek(): E = elements.last()
        override fun pop(): E = elements.removeAt(elements.size - 1)
        override fun isEmpty() = elements.isEmpty()
        override fun size() = elements.size
        override fun toList(): List<E> = elements
        override fun toString() = "MutableStack(${elements.joinToString()})"
    }

    private fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

}