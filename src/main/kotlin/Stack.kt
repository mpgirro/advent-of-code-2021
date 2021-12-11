internal interface Stack<E> {
    fun push(element: E): Boolean
    fun peek(): E
    fun pop(): E
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean = !isEmpty()
    fun size(): Int
    fun toList(): List<E>
}