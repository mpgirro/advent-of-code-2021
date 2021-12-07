import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day01().example.part1()).isEqualTo(7)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day01().puzzle.part1()).isEqualTo(1624)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day01().example.part2()).isEqualTo(5)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day01().puzzle.part2()).isEqualTo(1653)
    }

}