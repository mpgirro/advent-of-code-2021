import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day12().example.part1()).isEqualTo(10)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day12().puzzle.part1()).isEqualTo(3708)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day12().example.part2()).isEqualTo(36)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day12().puzzle.part2()).isEqualTo(93858)
    }

}