package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Write a factorial function
 */

internal object Factorial {

    private fun factorial(n: Int): Long {
        return if (n < 2) 1 else n * factorial(n - 1)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(factorial(3))
    }
}