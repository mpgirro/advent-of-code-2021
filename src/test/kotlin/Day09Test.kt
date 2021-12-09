import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day09Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day09().example.part1()).isEqualTo(15)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day09().puzzle.part1()).isEqualTo(607)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day09().example.part2()).isEqualTo(1134)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day09().puzzle.part2()).isEqualTo(900864)
    }

}