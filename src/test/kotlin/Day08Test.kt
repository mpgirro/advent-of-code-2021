import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day08Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day08().example.part1()).isEqualTo(26)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day08().puzzle.part1()).isEqualTo(375)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day08().example.part2()).isEqualTo(61229)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day08().puzzle.part2()).isEqualTo(1019355)
    }

}