package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Calculating Permutations
 */

internal object PermutationCycles {

    private fun findPermutationCycles(permutation: List<Int>): List<List<Int>> {
        val n = permutation.size
        val visited = BooleanArray(n)
        val cycles = mutableListOf<List<Int>>()

        for (i in 0 until n) {
            if (visited[i]) continue
            var current = i
            val cycle = mutableListOf<Int>()
            while (!visited[current]) {
                visited[current] = true
                cycle.add(current)
                current = permutation[current]
            }
            cycles.add(cycle)
        }

        return cycles
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = listOf(2, 0, 1, 4, 3)

        println(findPermutationCycles(array))

        array = listOf(2, 0, 3, 1)
        println(findPermutationCycles(array))
    }
}