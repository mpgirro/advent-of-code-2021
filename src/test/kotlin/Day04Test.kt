import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day04Test {

    private val exampleFile = "day04_example.txt"
    private val inputFile = "day04_input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day04(exampleFile).task1()).isEqualTo(4512)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day04(inputFile).task1()).isEqualTo(25023)
    }

}