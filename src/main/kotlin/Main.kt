import kotlin.reflect.full.createInstance

fun main() {

    AdventDay::class.sealedSubclasses.forEach {
        it.createInstance().puzzle.printResult()
    }

}