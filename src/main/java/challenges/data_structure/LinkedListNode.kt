package challenges.data_structure


class LinkedListNode {
    var next: LinkedListNode? = null
        set(n) {
            field = n
            if (this === last) {
                last = n
            }
            if (n != null && n.prev !== this) {
                n.prev = this
            }
        }
    var prev: LinkedListNode? = null
        set(p) {
            field = p
            if (p != null && p.next !== this) {
                p.next = this
            }
        }
    var last: LinkedListNode? = null
    var data = 0

    constructor(d: Int, n: LinkedListNode?, p: LinkedListNode?) {
        data = d
        next = n
        prev = p
    }

    constructor(d: Int) {
        data = d
    }

    constructor() {}

    fun printForward(): String {
        return if (next != null) {
            data.toString() + "->" + next!!.printForward()
        } else {
            data.toString()
        }
    }

    fun clone(): LinkedListNode {
        var next2: LinkedListNode? = null
        if (next != null) {
            next2 = next!!.clone()
        }
        return LinkedListNode(data, next2, null)
    }
}