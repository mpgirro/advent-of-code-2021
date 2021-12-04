import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day02Test {

    private val exampleFile = "day02_example.txt"
    private val inputFile = "day02_input.txt"

    @Test
    fun testExampleTask1() {
        assertThat(Day02(exampleFile).part1()).isEqualTo(150)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day02(inputFile).part1()).isEqualTo(1746616)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day02(exampleFile).part2()).isEqualTo(900)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day02(inputFile).part2()).isEqualTo(1741971043)
    }

}