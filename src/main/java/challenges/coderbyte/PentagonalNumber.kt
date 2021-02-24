package challenges.coderbyte

/*
Have the function PentagonalNumber(num) read num which will be a positive integer and determine how many dots exist in
a pentagonal shape around a center dot on the Nth iteration. For example, in the image below you can see that on the
first iteration there is only a single dot, on the second iteration there are 6 dots, on the third there are 16 dots,
and on the fourth there are 31 dots.

Your program should return the number of dots that exist in the whole pentagon on the Nth iteration.
 */
object PentagonalNumber {

    private fun pentagonalNumberRecursive(num: Int): Int {
        return if (num == 1) 1 else (num - 1) * 5 + pentagonalNumber(num - 1)
    }

    private fun pentagonalNumber(n: Int): Int {
        return (5 * n * n - 5 * n + 2) / 2
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(pentagonalNumberRecursive(2)) // 6
        println(pentagonalNumber(5)) // 51
    }
}