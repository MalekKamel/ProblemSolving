package challenges.cracking_coding_interview.object_oriented_design.hash_table

import kotlin.jvm.JvmStatic
import challenges.cracking_coding_interview.object_oriented_design.hash_table.Dummy
import challenges.cracking_coding_interview.object_oriented_design.hash_table.Hasher

object Question {
    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val bob = Dummy("Bob", 20)
        val jim = Dummy("Jim", 25)
        val alex = Dummy("Alex", 30)
        val tim = Dummy("Tim", 35)
        val maxwell = Dummy("Maxwell", 40)
        val john = Dummy("John", 45)
        val julie = Dummy("Julie", 50)
        val christy = Dummy("Christy", 55)
        val tim2 = Dummy("Tim", 100) // This should replace the first "tim"
        val dummies = arrayOf(bob, jim, alex, tim, maxwell, john, julie, christy, tim2)

        /* Test: Insert Elements. */
        val hash = Hasher<String, Dummy>(3)
        for (d in dummies) {
            println(hash.put(d.name, d))
        }
        hash.printTable()

        /* Test: Recall */for (d in dummies) {
            val name = d.name
            val dummy = hash[name]
            if (dummy == null) {
                println("Dummy named $name: null")
            } else {
                println("Dummy named $name: $dummy")
            }
            val d2 = hash.remove(name!!)
            if (d2 == null) {
                println("Dummy removed named $name: null")
            } else {
                println("Dummy removed named $name: $d2")
            }
        }
    }
}