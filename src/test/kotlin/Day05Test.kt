import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day05().example.part1()).isEqualTo(5)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day05().puzzle.part1()).isEqualTo(6572)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day05().example.part2()).isEqualTo(12)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day05().puzzle.part2()).isEqualTo(21466)
    }

}