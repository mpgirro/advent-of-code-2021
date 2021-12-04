import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day03Test {

    private val exampleFile = "day03_example.txt"
    private val inputFile = "day03_input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day03(exampleFile).part1()).isEqualTo(198)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day03(inputFile).part1()).isEqualTo(4139586)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day03(exampleFile).part2()).isEqualTo(230)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day03(inputFile).part2()).isEqualTo(1800151)
    }

}