import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day07Test {
    private val exampleFile = "day07-example.txt"
    private val inputFile = "day07-input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day07(exampleFile).part1()).isEqualTo(37)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day07(inputFile).part1()).isEqualTo(336131)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day07(exampleFile).part2()).isEqualTo(168)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day07(inputFile).part2()).isEqualTo(92676646)
    }

}