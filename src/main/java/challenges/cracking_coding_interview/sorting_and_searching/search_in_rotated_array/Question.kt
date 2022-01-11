package challenges.cracking_coding_interview.sorting_and_searching.search_in_rotated_array

import kotlin.jvm.JvmStatic

object Question {
    fun search(a: IntArray, x: Int): Int {
        return search(a, 0, a.size - 1, x)
    }

    fun search(a: IntArray, left: Int, right: Int, x: Int): Int {
        val mid = (left + right) / 2
        if (x == a[mid]) { // Found element
            return mid
        }
        if (right < left) {
            return -1
        }

        /* While there may be an inflection point due to the rotation, either the left or 
		 * right half must be normally ordered.  We can look at the normally ordered half
		 * to make a determination as to which half we should search. 
		 */
        if (a[left] < a[mid]) { // Left is normally ordered.
            if (x >= a[left] && x < a[mid]) {
                return search(a, left, mid - 1, x)
            }
            return search(a, mid + 1, right, x)
        }

        if (a[mid] < a[right]) { // Right is normally ordered.
            if (x > a[mid] && x <= a[right]) {
                return search(a, mid + 1, right, x)
            }
            return search(a, left, mid - 1, x)
        }

        if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) { // If right half is different, search there
                return search(a, mid + 1, right, x)
            }
            // Else, we have to search both halves
            val result = search(a, left, mid - 1, x)
            if (result == -1) {
                return search(a, mid + 1, right, x)
            }
            return result
        }
        return -1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = intArrayOf(2, 3, 1, 2, 2, 2, 2, 2, 2, 2)
        println(search(a, 2))
        println(search(a, 3))
        println(search(a, 4))
        println(search(a, 1))
        println(search(a, 8))
    }
}