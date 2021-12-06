import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day06Test {
    private val exampleFile = "day06-example.txt"
    private val inputFile = "day06-input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day06(exampleFile).part1()).isEqualTo(5934)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day06(inputFile).part1()).isEqualTo(362639)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day06(exampleFile).part2()).isEqualTo(26984457539)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day06(inputFile).part2()).isEqualTo(1639854996917)
    }

}