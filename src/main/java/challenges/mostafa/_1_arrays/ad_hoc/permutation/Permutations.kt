package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Calculating Permutations
 */

internal object Permutations {

    private fun permutationsRecursive(list: List<Int>): List<List<Int>> {
        if (list.size == 1) return listOf(list)

        val result = mutableListOf<List<Int>>()
        for (i in list.indices) {
            val element = list[i]
            val remainingList = list.toMutableList().also { it.removeAt(i) }
            val subPermutations = permutationsRecursive(remainingList)
            for (permutation in subPermutations) {
                result.add(listOf(element) + permutation)
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = listOf(1, 2, 3)

        println(permutationsRecursive(array))
    }
}