
fun <T> List<T>.head(): T = head(1)[0]

fun <T> List<T>.head(i: Int) = if (size >= i) subList(0, i) else emptyList()

fun <T> List<T>.tail() = drop(1)