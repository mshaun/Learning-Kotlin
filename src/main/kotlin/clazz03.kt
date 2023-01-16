class IntArrayList() {
    private var array: Array<Int> = arrayOf<Int>()

    var size: Int = 0
        private set

    private fun addArray (position: Int,value: Int):Array<Int> { // Make a new array, and move the elements
        val newArray: Array<Int> = Array<Int>(this.size + 1) {0}
        for (i in position until this.size) {
            newArray[i+1] = this.array[i]
        }
        for (i in 0 until position) {
            newArray[i] = this.array[i]
        }
        newArray[position] = value
        return newArray
    }

    private fun addAfterTail (position: Int,value: Int):Array<Int> { // Make a new array, and move the elements
        val newArray: Array<Int> = Array<Int>(this.size+1) {0}
        for (i in 0 until this.size) {
            newArray[i] = this.array[i]
        }
        newArray[position] = value
        return newArray
    }

    private fun subTail(position: Int):Array<Int> { // Make a new array, and move the elements
        val newArray: Array<Int> = Array<Int>(position){0}
        for (i in 0 until position) {
            newArray[i] = this.array[i]
        }
        return newArray
    }

    private fun subArray(position: Int):Array<Int> { // Make a new array, and move the elements
        val newArray: Array<Int> = Array<Int>(this.size-1){0}
        for (i in 0 until position) {
            newArray[i] = this.array[i]
        }
        for (i in position until this.size-1) {
            newArray[i] = this.array[i+1]
        }
        return newArray
    }


    fun add(value: Int, atPosition: Int) {
        require(atPosition in 0..this.size) { "Position must between the correct range (0 and size)" }
        if (this.array.isEmpty()) {
            val newArray: Array<Int> = Array<Int>(1){0} // Make a new array that size is 1, element is 0
            newArray[atPosition] = value //Assign the value to newArray
            this.array = newArray
            this.size = 1
        }
        else if (atPosition in 0 until this.size) { //Add integer between head and tail
            this.array = addArray(atPosition,value) // Make a new array, and assign to this.array
            this.size += 1
        }
        else if (atPosition == this.size) { //Add integer after tail
            this.array = addAfterTail(atPosition,value) // Make a new array, and assign to this.array
            this.size += 1
        }
    }

    fun remove(atPosition: Int) {
        require(this.array.isNotEmpty()) {"Array is empty, you have to add integer first"}
        require(atPosition in 0 until this.size) {"atPosition is not between the correct range (0 and size-1)"}
        if (this.array.size==1) {
            val emptyArray: Array<Int> = arrayOf<Int>() // Make a new array that is empty
            this.array = emptyArray //Assign an emptyArray to this.array
            this.size = 0
        }
        else if (atPosition == this.size-1) { //Remove integer at tail
            this.array = subTail(atPosition) //Make a new array, and assign to this.array
            this.size -= 1
        }
        else { //Remove integer between head and tail-1
            this.array = subArray(atPosition) //Make a new array, and assign to this.array
            this.size -= 1
        }
    }

    fun get(position: Int): Int {
        return if (this.array.isEmpty()) {
            print("Array is empty, you have to add an integer at position ")
            0
        }
        else {
            require(position in 0 until this.size)
            { "Position is not between the correct range (0 and size-1)" }
            this.array[position]
        }
    }
    fun println() {
        print("Size : [${this.size}] ")
        if (this.array.isEmpty()) {
            println("Array is empty")
        }
        else {
        for(i in 0 until size) {
            print("${get(i)} ")
        }
            print("\n")
        }
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
//------------------------------------------------------------------------
class IntLinkedList2 {
    private var head: IntNode? = null
    private var tail: IntNode? = null
    var size: Int = 0
        private set

    fun add(value: Int) {
        val node = IntNode(value, null)

        val head = this.head
        if (head == null) {
            this.head = node
        } else {
            //Connect the last node after tail
            this.tail?.next= node
        }
        this.tail = node
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
            this.tail = null
        } else {
            val prev: IntNode = findNode(atPosition - 1)
            prev.next = prev.next?.next
            if (prev.next == null) {
                this.tail = prev
            }
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
//-------------------------------------------------------------------------------
class IntLinkedList3 {
    private var head: IntNode? = null
    var size: Int = 0
        private set

    fun add(value: Int) {
        val node = IntNode(value, this.head)

        val head = this.head
        if (head == null) {
            node.next = node
            this.head = node
        }
        else {
            // 1. Find tail
            var tail: IntNode = head
            if (this.size > 1) {
                for (i in 0 until size-1) {
                    val next = tail.next
                    tail = next!!
                }
            }
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

        if(atPosition == 0 && this.size == 1) {
            head = null
        }
        else if (atPosition == 0) {
            head = head?.next
        }
        else {
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
        if (this.head == null) {
            println("[$size] ")
        }
        else {
            print("[$size] ")
            var node = head
            for (i in 0 until size) {
                print("${node?.value} ")
                node = node?.next
            }
            print("\n")
        }
    }
}
//---------------------------------------------------------------------------
class IntLinkedList4 {
    private var head: IntDNode? = null
    var size: Int = 0
        private set

    fun add(value: Int) {
        val node = IntDNode(null, value, null)

        val head = this.head
        if (head == null) {
            this.head = node
        }
        else {
            // 1. Find tail
            var tail: IntDNode = head
            while (true) {
                val next = tail.next ?: break
                tail = next
            }
            // 2. Connect the last node
            tail.next = node
            node.last = tail
        }
        size += 1
    }

    fun insert(value: Int, atPosition: Int) {
        require(atPosition in 0..size)

        if (atPosition == size) {
            add(value)
        } else {
            if (atPosition == 0) {
                head = IntDNode(null, value, head)
                head?.next?.last = head
            } else {
                // 1. Find prev
                val prev = findNode(atPosition - 1)
                val node = IntDNode(prev, value, prev.next)
                prev.next = node
                node.next?.last = node
            }
            size += 1
        }
    }

    fun remove(atPosition: Int) {
        require(atPosition in 0 until size)
        if (atPosition == 0 && this.size == 1) {
            this.head = null
        }
        else if (atPosition == 0) {
            head = head?.next
            head?.last = null
        }
        else if (atPosition == this.size-1) {
            val prev: IntDNode = findNode(atPosition-1)
            prev.next = prev.next?.next
        }
        else {
            val prev: IntDNode = findNode(atPosition - 1)
            prev.next = prev.next?.next
            prev.next?.last = prev
        }
        size -= 1
    }

    private fun findNode(atPosition: Int): IntDNode {
        var prev: IntDNode = head!!
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
        while (true) {
            print("${node?.value} ")
            node = node?.next ?: break
        }
        print("\n Turn around ")
        while (node != null) {
            print("${node.value} ")
            node = node.last
        }
        print("\n")
    }
}
//-------------------------------------------------------------------------
class IntLinkedList5 {
    private var head: IntDNode? = null
    var size: Int = 0
        private set

    fun add(value: Int) {
        val node = IntDNode(head?.last, value, head)

        val head = this.head
        if (head == null) {
            node.last = node
            node.next = node
            this.head = node
        }
        else {
            // 1. Find tail
            val tail: IntDNode? = head.last
            // 2. Connect the last node
            tail?.next = node
            head.last = node
        }
        size += 1
    }

    fun insert(value: Int, atPosition: Int) {
        require(atPosition in 0..size)

        if (atPosition == size) {
            add(value)
        } else {
            if (atPosition == 0) {
                head = IntDNode(head?.last, value, head)
                head?.last?.next = head
                head?.next?.last = head
            }
            else {
                // 1. Find prev
                val prev = findNode(atPosition - 1)
                val node = IntDNode(prev, value, prev.next)
                prev.next = node
                node.next?.last = node
            }
            size += 1
        }
    }

    fun remove(atPosition: Int) {
        require(atPosition in 0 until size)
        if (atPosition == 0 && this.size == 1) {
            this.head = null
        }
        else if (atPosition == 0) {
            head?.next?.last = head?.last
            head?.last?.next = head?.next
            head = head?.next
        }
        else {
            val prev: IntDNode = findNode(atPosition - 1)
            prev.next = prev.next?.next
            prev.next?.last = prev
        }
        size -= 1
    }

    private fun findNode(atPosition: Int): IntDNode {
        var prev: IntDNode = head!!
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
        if ( this.size == 0) {
            print("[$size] ")
        }
        else {
            print("[$size] ")
            var node = head
            for (i in 0 until size) {
                print("${node?.value} ")
                node = node?.next
            }
            print("\n Turn around ")
            node = node?.last
            for ( i in 0 until size) {
                print("${node?.value} ")
                node = node?.last
            }
        }
        print("\n")
    }
}

class IntNode(
    val value: Int,
    var next: IntNode?
)

class IntDNode(
    var last: IntDNode?,
    val value: Int,
    var next: IntDNode?
)

fun main() {
//    val arrayList:IntArrayList = IntArrayList()
//
//
//    arrayList.println()              //Size : [0] Array is empty
//
//    println(arrayList.get(0))        // Array is empty, you have to add an integer at position 0
//    // Add 0 to position 0, 1 to position 1, 2 to position 2, 3 to position 3
//    repeat(4) {
//        arrayList.add(it,it)
//    }
//
//    arrayList.println()              //Size : [4] 0 1 2 3
//
//    println(arrayList.get(2))        // 2
//    // Add 4 after tail (3)
//    arrayList.add(4,4)
//
//    arrayList.println()              //Size : [5] 0 1 2 3 4
//    //Add integer between head and tail
//    arrayList.add(8,3)
//
//    arrayList.println()              //Size : [6] 0 1 2 8 3 4
//    // Remove the tail
//    arrayList.remove(5)
//
//    arrayList.println()              //Size : [5] 0 1 2 8 3
//    // Remove integer between head and tail-1
//    arrayList.remove(3)
//
//    arrayList.println()              //Size : [4] 0 1 2 3
//    // Remove integer at position 0 four times, and array become empty
//    repeat(4) {
//        arrayList.remove(0)
//    }
//
//    arrayList.println()              //Size : [0] Array is empty
//
//    arrayList.remove(0)              // "You have to add integer first"
//
//    arrayList.add(0,-1)            // "Position must between the correct range (0 and size)"
//                        //If you want to test that line of arrayList.add(0,-1) , please delete the line above arrayList.add(0,-1)
//-------------------------------------------------------------------------------------

    //Modify the number to test Homework 2~5
    val l = IntLinkedList2()

    l.println() // [0]
    //Add integer after tail five times
    repeat(5) {
        l.add(it)
    }
    l.println() // [5] 0 1 2 3 4
    //Remove an integer at position 0
    l.remove(0)
    l.println() // [4] 1 2 3 4
    //Remove an integer at position 1 (between head and tail)
    l.remove(1)
    l.println() // [3] 1 3 4
    //Add an integer at position 0
    l.insert(0, 0)
    l.println() // [4] 0 1 3 4
    //Add an integer at position 2 (between head and tail)
    l.insert(2, 2)
    l.println() // [5] 0 1 2 3 4
    //Remove an integer at tail
    l.remove(4)
    l.println() // [4] 0 1 2 3
    //Add an integer at position 4 (after tail)
    l.insert(l.size, l.size)
    l.println() // [5] 0 1 2 3 4

}

