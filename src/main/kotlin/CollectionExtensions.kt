
fun <T> List<T>.head(): T = head(1)[0]

fun <T> List<T>.head(i: Int) = if (size >= i) subList(0, i) else emptyList()

fun <T> List<T>.tail() = drop(1)

fun BooleanArray.allTrue(): Boolean = all { it }

fun List<Boolean>.anyTrue(): Boolean = any { it }

fun Array<BooleanArray>.getRow(index: Int): BooleanArray = this[index]

fun Array<BooleanArray>.getColumn(index: Int): BooleanArray = map { row -> row[index] }.toBooleanArray()