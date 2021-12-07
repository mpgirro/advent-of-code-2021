import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day07Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day07().example.part1()).isEqualTo(37)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day07().puzzle.part1()).isEqualTo(336131)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day07().example.part2()).isEqualTo(168)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day07().puzzle.part2()).isEqualTo(92676646)
    }

}