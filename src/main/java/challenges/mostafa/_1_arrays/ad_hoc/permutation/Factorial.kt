package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Write a factorial function
 */

internal object Factorial {

    private fun factorial(n: Int): Long {
        return if (n < 2) 1 else n * factorial(n - 1)
    }

    /**
     * Kotlin supports tail recursion, which is an optimization where the recursive call is the last
     * operation performed in the function.
     * When a function is tail-recursive, the Kotlin compiler can optimize it into a loop, preventing
     * stack overflow errors for deep recursion.
     * Use the tailrec keyword in Kotlin to indicate that a function is intended to be tail-recursive.
     */
    private tailrec fun factorialTailRecursive(n: Int, accumulator: Int = 1): Int {
        return if (n == 0) {
            accumulator // Base case: return the accumulated result
        } else {
            factorialTailRecursive(n - 1, accumulator * n) // Tail-recursive call
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(factorial(3))
        println(factorialTailRecursive(3))
    }
}