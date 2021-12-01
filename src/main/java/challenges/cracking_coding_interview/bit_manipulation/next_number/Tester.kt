package challenges.cracking_coding_interview.bit_manipulation.next_number

import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionA.getNextSlow
import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionA.getPrevSlow
import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionB.getNext
import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionB.getPrev
import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionC.getNextArith
import challenges.cracking_coding_interview.bit_manipulation.next_number.QuestionC.getPrevArith

object Tester {

    fun binPrint(i: Int) {
        println(i.toString() + ": " + Integer.toBinaryString(i))
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..199) {
            val p1 = getPrevSlow(i)
            val p2 = getPrev(i)
            val p3 = getPrevArith(i)
            val n1 = getNextSlow(i)
            val n2 = getNext(i)
            val n3 = getNextArith(i)
            if (p1 != p2 || p2 != p3 || n1 != n2 || n2 != n3) {
                binPrint(i)
                binPrint(p1)
                binPrint(p2)
                binPrint(p3)
                binPrint(n1)
                binPrint(n2)
                binPrint(n3)
                println("")
                break
            }
        }
        println("Done!")
    }

}