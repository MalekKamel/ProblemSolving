package challenges.cracking_coding_interview.moderate.q5_factorial_zeros

object QuestionB {
    private fun countFactZeros(num: Int): Int {
        var count = 0
        if (num < 0) {
            println("Factorial is not defined for negative numbers")
            return 0
        }
        var i = 5
        while (num / i > 0) {
            count += num / i
            i *= 5
        }
        return count
    }

    fun factorial(num: Int): Int {
        return if (num == 1) {
            1
        } else if (num > 1) {
            num * factorial(num - 1)
        } else {
            -1 // Error
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 1..11) {
            println(i.toString() + "! (or " + factorial(i) + ") has " + countFactZeros(i) + " zeros")
        }
    }
}