class IntArrayList(val size: Int = 3) {
    private var array: Array<Int>? = null

//    var size: Int = 0
//        private set

    private fun sub(array: Array<Int>, number: Int): Array<Int> {
        //sub array size (size != 1)
        val newSize: Int = number
        val newArray = Array<Int>(newSize) { 0 }
        for (i in 0 until number) {
            newArray[i] = array[i]
        }
        return newArray
    }

    private fun add(array: Array<Int>, number: Int): Array<Int> {
        //add array size
        val size: Int = number + 1
        val newArray = Array<Int>(size) { 0 }
        for (i in 0..number) {
            newArray[i] = array[i]
        }
        return newArray
    }

    fun add(value: Int, atPosition: Int) {
        // {1 ,2, 3}
        // size = 3
        // this.add(2, size)

        require(atPosition in 0..size) { "Position must be greater than zero" }

        // size = 1
        val a = arrayOf(4)

        // size = 0
        // array = {4}
        this.remove(0)

        // {4, 0, 0, 0, 1}
        this.add(1, 4)
        this.get(1)
    }


    fun remove(atPosition: Int) {
        require(this.array != null && atPosition in 0 until size)
        { "There's nothing you can delete or position is not in the correct range" }
        if (atPosition == this.size - 1 && this.size != 1) {
            val newArray = sub(this.array!!, atPosition)
        }
        if else (atPosition in 0 until this.size - 1) {

        }
        else {
        }
    }

    fun get(position: Int): Int {
        require(this.size > 0 && position in 0 until size)
        { "There's nothing you can get or position is not in the correct range" }
        return this.array?.get(position)!!
    }
}


//------------------------------------------------------------------------------
class IntLinkedList {
    private var head: IntNode? = null
    var size: Int = 0
        private set

    fun add(value: Int) {
        val node = IntNode(value, null)

        val head = this.head
        if (head == null) {
            this.head = node
        } else {
            // 1. Find tail
            var tail: IntNode = head
            while (true) {
                val next = tail.next ?: break
                tail = next
            }

//            while (tail.next != null) {
//                tail = tail.next!!
//            }

            // 2. Connect the last node
            tail.next = node
        }
        size += 1
    }

    fun insert(value: Int, atPosition: Int) {
        require(atPosition in 0..size)

        if (atPosition == size) {
            add(value)
        } else {
            if (atPosition == 0) {
                head = IntNode(value, head)
            } else {
                // 1. Find prev
                val prev = findNode(atPosition - 1)
                val node = IntNode(value, prev.next)
                prev.next = node
            }
            size += 1
        }
    }

    fun remove(atPosition: Int) {
        require(atPosition in 0 until size)

        if (atPosition == 0) {
            head = head?.next
        } else {
            val prev: IntNode = findNode(atPosition - 1)
            prev.next = prev.next?.next
        }
        size -= 1
    }

    private fun findNode(atPosition: Int): IntNode {
        var prev: IntNode = head!!
        for (i in 0 until atPosition) {
            prev = prev.next!!
        }
        return prev
    }

    fun get(position: Int): Int {
        require(position in 0 until size)

        return findNode(position).value
    }

    fun println() {
        print("[$size] ")
        var node = head
        while (node != null) {
            print("${node.value} ")
            node = node.next
        }
        print("\n")
    }
}

class IntLinkedList2 {
//    private var head: IntNode?
//    private var tail: IntNode?
//    var size: Int
//        private set
}

class IntNode(
    val value: Int,
    var next: IntNode?
)

fun main() {
    val a = IntArrayList()
    a.add(1, 4)

    val l = IntLinkedList()

    l.println() //
    repeat(5) {
        l.add(it)
    }
    l.println() // [5] 0 1 2 3 4

    l.remove(0)
    l.println() // [4] 1 2 3 4

    l.remove(1)
    l.println() // [3] 1 3 4

    l.insert(0, 0)
    l.println() // [4] 0 1 3 4

    l.insert(2, 2)
    l.println() // [5] 0 1 2 3 4

    l.remove(4)
    l.println() // [4] 0 1 2 3

    l.insert(l.size, l.size)
    l.println() // [5] 0 1 2 3 4
}

