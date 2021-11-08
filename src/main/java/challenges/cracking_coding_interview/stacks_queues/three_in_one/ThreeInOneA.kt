package challenges.cracking_coding_interview.stacks_queues.three_in_one

import challenges.util.AssortedMethods


object ThreeInOneA {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stacks = FixedMultiStack(4)
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
    }

    private fun printStacks(stacks: FixedMultiStack) {
        println(AssortedMethods.arrayToString(stacks.values))
    }

}