package challenges.cracking_coding_interview.linked_list

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods
import kotlin.math.abs

/**
 * Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node. Note that the intersection is defined based on reference, not value.
 * That is, if the kth node of the first linked list is the exact same node (by reference)
 * as the jth node of the second linked list, then they are intersecting.
 */
object Intersection {
    class Result(var tail: LinkedListNode?, var size: Int)

    private fun getTailAndSize(list: LinkedListNode?): Result {
        if (list == null) return Result(null, 0)
        var size = 1
        var current = list
        while (current!!.next != null) {
            size++
            current = current.next
        }
        return Result(current, size)
    }

    private fun findIntersection(list1: LinkedListNode?, list2: LinkedListNode?): LinkedListNode? {
        if (list1 == null || list2 == null) return null

        // Get tail and sizes. 
        val result1 = getTailAndSize(list1)
        val result2 = getTailAndSize(list2)

        // If different tail nodes, then there's no intersection.
        if (result1.tail != result2.tail) {
            return null
        }

        // Set pointers to the start of each linked list. 
        var shorter: LinkedListNode? = if (result1.size < result2.size) list1 else list2
        var longer: LinkedListNode? = if (result1.size < result2.size) list2 else list1

        // Advance the pointer for the longer linked list by the difference in lengths. 
        longer = getKthNode(
            longer,
            abs(result1.size - result2.size)
        )

        // Move both pointers until you have a collision.
        while (shorter != longer) {
            shorter = shorter?.next
            longer = longer?.next
        }

        // Return either one. 
        return longer
    }

    private fun getKthNode(head: LinkedListNode?, _k: Int): LinkedListNode? {
        var k = _k
        var current = head
        while (k > 0 && current != null) {
            current = current.next
            k--
        }
        return current
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create linked list 
        val values = intArrayOf(-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8)
        val list1: LinkedListNode = AssortedMethods.createLinkedListFromArray(values)

        val values2 = intArrayOf(12, 14, 15)
        val list2: LinkedListNode = AssortedMethods.createLinkedListFromArray(values2)

        list2.next!!.next = list1.next!!.next!!.next!!.next

        println(list1.printForward())
        println(list2.printForward())

        val intersection = findIntersection(list1, list2)
        println(intersection!!.printForward())
    }

}