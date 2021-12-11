internal class MutableStack<E>(vararg items: E) : Stack<E> {
    private val elements = items.toMutableList()
    override fun push(element: E) = elements.add(element)
    override fun peek(): E = elements.last()
    override fun pop(): E = elements.removeAt(elements.size - 1)
    override fun isEmpty() = elements.isEmpty()
    override fun size() = elements.size
    override fun toList(): List<E> = elements
    override fun toString() = "MutableStack(${elements.joinToString()})"
}