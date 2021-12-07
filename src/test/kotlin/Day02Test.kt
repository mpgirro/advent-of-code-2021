import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day02Test {

    @Test
    fun testExampleTask1() {
        assertThat(Day02().example.part1()).isEqualTo(150)
    }

    @Test
    fun testInputTask1() {
        assertThat(Day02().puzzle.part1()).isEqualTo(1746616)
    }

    @Test
    fun testExampleTask2() {
        assertThat(Day02().example.part2()).isEqualTo(900)
    }

    @Test
    fun testInputTask2() {
        assertThat(Day02().puzzle.part2()).isEqualTo(1741971043)
    }

}