import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val exampleFile = "day01-example.txt"
    private val inputFile = "day01-input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day01(exampleFile).part1()).isEqualTo(7)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day01(inputFile).part1()).isEqualTo(1624)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day01(exampleFile).part2()).isEqualTo(5)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day01(inputFile).part2()).isEqualTo(1653)
    }
}