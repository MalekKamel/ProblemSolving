package challenges.cracking_coding_interview.sorting_and_searching.sorted_merge

import challenges.util.AssortedMethods.arrayToString


object SortedMerge {

    /** Merges array
     * @param a first array
     * @param b second array
     * @param countA number of "real" elements in a
     * @param countB number of "real" elements in b
     */
    private fun merge(a: IntArray, b: IntArray, countA: Int, countB: Int) {
        var indexMerged = countB + countA - 1 /* Index of last location of merged array */
        var indexA = countA - 1 /* Index of last element in array b */
        var indexB = countB - 1 /* Index of last element in array a */

        /* Merge a and b, starting from the last element in each */
        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of A is bigger than end of B */
                a[indexMerged] = a[indexA] // copy element
                indexA--
            } else {
                a[indexMerged] = b[indexB] // copy element
                indexB--
            }
            indexMerged-- // move indices
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = intArrayOf(2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0)
        val b = intArrayOf(1, 4, 6, 7, 7, 11)
        merge(a, b, 8, 6)
        println(arrayToString(a))
    }
}