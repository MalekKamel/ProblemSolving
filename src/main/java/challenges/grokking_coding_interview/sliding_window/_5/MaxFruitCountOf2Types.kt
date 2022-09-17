package challenges.grokking_coding_interview.sliding_window._5

/**
 * You are visiting a farm to collect fruits. The farm has a single row of fruit trees.
 * You will be given two baskets, and your goal is to pick as many fruits as possible to be placed in the given baskets.
 * You will be given an array of characters where each character represents a fruit tree.
 * The farm has the following restrictions:
 * Each basket can have only one type of fruit. There is no limit to how many fruit a basket can hold.
 * You can start with any tree, but you can’t skip a tree once you have started.
 * You will pick exactly one fruit from every tree until you cannot, i.e., you will stop
 * when you have to pick from a third fruit type.
 * Write a function to return the maximum number of fruits in both baskets.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/Bn2KLlOR0lQ
 */
internal object MaxFruitCountOf2Types {
    private fun findLength(arr: CharArray): Int {
        var windowStart = 0
        var maxLength = 0
        val fruitFrequencyMap: MutableMap<Char, Int> = HashMap()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in arr.indices) {
            fruitFrequencyMap[arr[windowEnd]] = fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1
            // shrink the sliding window, until we are left with '2' fruits in the frequency map
            while (fruitFrequencyMap.size > 2) {
                fruitFrequencyMap[arr[windowStart]] = fruitFrequencyMap[arr[windowStart]]!! - 1
                if (fruitFrequencyMap[arr[windowStart]] == 0) {
                    fruitFrequencyMap.remove(arr[windowStart])
                }
                windowStart++ // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Maximum number of fruits: " +
                    findLength(charArrayOf('A', 'B', 'C', 'A', 'C'))
        )
        println(
            "Maximum number of fruits: " +
                    findLength(charArrayOf('A', 'B', 'C', 'B', 'B', 'C'))
        )
    }
}