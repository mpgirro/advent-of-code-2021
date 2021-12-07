import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day03Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day03().example.part1()).isEqualTo(198)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day03().puzzle.part1()).isEqualTo(4139586)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day03().example.part2()).isEqualTo(230)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day03().puzzle.part2()).isEqualTo(1800151)
    }

}