import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day11Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day11().example.part1()).isEqualTo(1656)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day11().puzzle.part1()).isEqualTo(1669)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day11().example.part2()).isEqualTo(195)
    }
/*
    @Test
    fun testInputTask2() {
        assertThat(Day11().puzzle.part2()).isEqualTo(1105996483)
    }
*/
}