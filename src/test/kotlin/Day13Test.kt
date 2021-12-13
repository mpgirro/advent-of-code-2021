import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day13().example.part1()).isEqualTo(17)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day13().puzzle.part1()).isEqualTo(765)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day13().example.part2()).isEqualTo(36)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day13().puzzle.part2()).isEqualTo(98) // placeholder, puzzle result is text in ascii art output
    }

}