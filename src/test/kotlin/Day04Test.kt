import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day04Test {

    private val exampleFile = "day04-example.txt"
    private val inputFile = "day04-input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day04(exampleFile).part1()).isEqualTo(4512)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day04(inputFile).part1()).isEqualTo(25023)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day04(exampleFile).part2()).isEqualTo(1924)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day04(inputFile).part2()).isEqualTo(2634)
    }

}