import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day05Test {
    private val exampleFile = "day05-example.txt"
    private val inputFile = "day05-input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day05(exampleFile).part1()).isEqualTo(5)
    }


    @Test
    fun testInputTask1() {
        assertThat(Day05(inputFile).part1()).isEqualTo(6572)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day05(exampleFile).part2()).isEqualTo(12)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day05(inputFile).part2()).isEqualTo(21466)
    }

}