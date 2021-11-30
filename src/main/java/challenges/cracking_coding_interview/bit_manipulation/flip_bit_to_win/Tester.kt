package challenges.cracking_coding_interview.bit_manipulation.flip_bit_to_win

object Tester {

    private fun checkRange(start: Int, range: Int): Boolean {
        for (i in 0 until range) {
            val value = start + i
            val seqA = QuestionA.longestSequence(value)
            val seqB = QuestionB.longestSequence(value)
            val seqC = QuestionC.longestSequence(value)
            val seqD = QuestionC.longestSequence(value)
            if (seqA != seqB || seqB != seqC || seqC != seqD) {
                println("FAILURE on value $value")
                val xs = Integer.toBinaryString(value)
                println(xs)
                println("A: $seqA")
                println("B: $seqB")
                println("C: $seqC")
                println("D: $seqD")
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val ranges =
            arrayOf(intArrayOf(Int.MIN_VALUE, 1000), intArrayOf(Int.MAX_VALUE - 2333, 5333), intArrayOf(-10000, 20000))
        for (range in ranges) {
            if (!checkRange(range[0], range[1])) {
                println("ERROR")
            } else {
                val end = range[0] + range[1]
                println("SUCCESS: " + range[0] + " -> " + end)
            }
        }
    }
}