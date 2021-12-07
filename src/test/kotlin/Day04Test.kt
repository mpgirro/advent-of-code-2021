import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day04Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day04().example.part1()).isEqualTo(4512)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day04().puzzle.part1()).isEqualTo(25023)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day04().example.part2()).isEqualTo(1924)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day04().puzzle.part2()).isEqualTo(2634)
    }

}