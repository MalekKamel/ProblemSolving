package challenges.cracking_coding_interview.moderate.q5_factorial_zeros

object QuestionA {
    private fun factorsOf5(_i: Int): Int {
        var i = _i
        var count = 0
        while (i % 5 == 0) {
            count++
            i /= 5
        }
        return count
    }

    private fun countFactZeros(num: Int): Int {
        var count = 0
        for (i in 2..num) {
            count += factorsOf5(i)
        }
        return count
    }

    private fun factorial(num: Int): Int {
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