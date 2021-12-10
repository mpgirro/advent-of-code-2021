import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day10Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day10().example.part1()).isEqualTo(26397)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day10().puzzle.part1()).isEqualTo(215229)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day10().example.part2()).isEqualTo(288957)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day10().puzzle.part2()).isEqualTo(1105996483)
    }

}