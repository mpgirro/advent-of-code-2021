fun main(args: Array<String>) {

    println("\n--- Day 1: Sonar Sweep ---\n")
    val day01 = Day01("day01_input.txt")
    day01.printPart1()
    day01.printPart2()

    println("\n--- Day 2: Dive! ---\n")
    val day02 = Day02()
    day02.task()

    println("\n--- Day 3: Binary Diagnostic ---\n")
    val day03 = Day03()
    day03.task1()
    day03.task2()

    println("\n--- Day 4: Giant Squid ---\n")
    val day04 = Day04("day04_input.txt")
    day04.printTask1()
    day04.printTask2()
}