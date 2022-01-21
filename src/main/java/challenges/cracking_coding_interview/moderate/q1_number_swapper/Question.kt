package challenges.cracking_coding_interview.moderate.q1_number_swapper

object Question {
    fun swap(a: Int, b: Int) {
        // Example for a = 9, b = 4
        var a = a
        var b = b
        a = a - b // a = 9 - 4 = 5
        b = a + b // b = 5 + 4 = 9
        a = b - a // a = 9 - 5
        println("a: $a")
        println("b: $b")
    }

    fun swap_opt(a: Int, b: Int) {
        var a = a
        var b = b
        a = a xor b
        b = a xor b
        a = a xor b
        println("a: $a")
        println("b: $b")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = 1672
        val b = 9332
        println("a: $a")
        println("b: $b")
        swap(a, b)
        swap_opt(a, b)
    }
}