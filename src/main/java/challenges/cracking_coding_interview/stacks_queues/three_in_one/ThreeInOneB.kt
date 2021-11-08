package challenges.cracking_coding_interview.stacks_queues.three_in_one

import challenges.util.AssortedMethods


object ThreeInOneB {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stacks = MultiStack(3, 4)
        printStacks(stacks)
        stacks.push(0, 10)
        printStacks(stacks)
        stacks.push(1, 20)
        printStacks(stacks)
        stacks.push(2, 30)
        printStacks(stacks)
        stacks.push(1, 21)
        printStacks(stacks)
        stacks.push(0, 11)
        printStacks(stacks)
        stacks.push(0, 12)
        printStacks(stacks)
        stacks.pop(0)
        printStacks(stacks)
        stacks.push(2, 31)
        printStacks(stacks)
        stacks.push(0, 13)
        printStacks(stacks)
        stacks.push(1, 22)
        printStacks(stacks)
        stacks.push(2, 31)
        printStacks(stacks)
        stacks.push(2, 32)
        printStacks(stacks)
        stacks.push(2, 33)
        printStacks(stacks)
        stacks.push(2, 34)
        printStacks(stacks)
        stacks.pop(1)
        printStacks(stacks)
        stacks.push(2, 35)
        printStacks(stacks)
        println("Final Stack: " + AssortedMethods.arrayToString(stacks.values))
    }

    private fun printStacks(stacks: MultiStack) {
//        println(stacks.stackToString(0))
//        println(stacks.stackToString(1))
//        println(stacks.stackToString(2))
        println(AssortedMethods.arrayToString(stacks.values))
    }

}