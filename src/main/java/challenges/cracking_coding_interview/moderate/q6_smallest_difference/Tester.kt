package challenges.cracking_coding_interview.moderate.q6_smallest_difference

import challenges.util.AssortedMethods.randomArray

object Tester {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..99) {
            val size = (Math.random() * 100.0).toInt()
            val array1 = randomArray(size, i * -1, i)
            val array2 = randomArray(size, i * -1, i)
            var diffA = QuestionA.findSmallestDifference(array1, array2)
            var diffB = QuestionB.findSmallestDifference(array1, array2)
            var diffC = QuestionC.findSmallestDifference(array1, array2)
            if (diffA != diffB || diffB != diffC) {
                diffA = QuestionA.findSmallestDifference(array1, array2)
                diffB = QuestionB.findSmallestDifference(array1, array2)
                diffC = QuestionC.findSmallestDifference(array1, array2)
                println(diffA)
                println(diffB)
                println(diffC)
                println()
            }
        }
    }
}