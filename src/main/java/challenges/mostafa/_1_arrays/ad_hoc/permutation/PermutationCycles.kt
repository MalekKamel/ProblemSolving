package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Permutation cycles are a way to represent permutations of a set of elements. Here's a brief explanation:

A permutation cycle is a sequence of elements in a permutation where each element is mapped to
the next, and the last element is mapped back to the first. For example, consider
the permutation (1 2 3 4) -> (2 3 4 1). This can be represented as the permutation cycle (1 2 3 4).

The key properties of permutation cycles are:

Each element in the set appears in exactly one cycle.
The number of cycles in a permutation is equal to the number of orbits (distinct elements)
in the permutation.

The product of the lengths of the cycles is equal to the total number of elements in the set.
Permutation cycles provide a compact way to represent permutations and are commonly used in
abstract algebra, group theory, and other areas of mathematics. They can also be useful for
visualizing and working with permutations in problem-solving contexts.
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