abstract class AdventDay(fileName: String) {

    protected val puzzle: List<String> = readFileFromClasspath(fileName)
        .split("\n")

    abstract fun part1(): Long
    abstract fun part2(): Long
    abstract fun printResult()

    private fun readFileFromClasspath(path: String): String =
        this::class.java.getResource(path).readText(Charsets.UTF_8)

}