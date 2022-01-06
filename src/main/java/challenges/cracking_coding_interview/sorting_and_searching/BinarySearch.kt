package challenges.cracking_coding_interview.sorting_and_searching


object BinarySearch {
    private fun binarySearch(a: IntArray, x: Int): Int {
        var low = 0
        var high = a.size - 1
        var mid: Int
        while (low <= high) {
            mid = low + (high - low) / 2
            if (a[mid] < x) {
                low = mid + 1
            } else if (a[mid] > x) {
                high = mid - 1
            } else {
                return mid
            }
        }
        return -1
    }

    private fun binarySearchRecursive(a: IntArray, x: Int, low: Int, high: Int): Int {
        if (low > high) return -1 // Error
        val mid = (low + high) / 2
        return if (a[mid] < x) {
            binarySearchRecursive(a, x, mid + 1, high)
        } else if (a[mid] > x) {
            binarySearchRecursive(a, x, low, mid - 1)
        } else {
            mid
        }
    }

    // Recursive algorithm to return the closest element
    private fun binarySearchRecursiveClosest(
        a: IntArray,
        x: Int,
        low: Int,
        high: Int
    ): Int {
        if (low > high) { // high is on the left side now
            if (high < 0) return low
            if (low >= a.size) return high
            return if (x - a[high] < a[low] - x) high else low
        }
        val mid = (low + high) / 2
        return if (a[mid] < x) {
            binarySearchRecursiveClosest(a, x, mid + 1, high)
        } else if (a[mid] > x) {
            binarySearchRecursiveClosest(a, x, low, mid - 1)
        } else {
            mid
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(3, 6, 9, 12, 15, 18)
        for (i in 0..19) {
            val loc = binarySearch(array, i)
            val loc2 = binarySearchRecursive(array, i, 0, array.size - 1)
            val loc3 = binarySearchRecursiveClosest(array, i, 0, array.size - 1)
            println("$i: $loc $loc2 $loc3")
        }
    }
}