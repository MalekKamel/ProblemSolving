package challenges.cracking_coding_interview.stacks_queues.stack_of_plates


/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack (that is,
 * pop() should return the same values as it would if there were just a single stack).
 * FOLLOW-UP
 * Implement a function popAt(int index)which performs a pop operation on a specific sub-stack.
 */
object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val capacityPerSubstack = 5
        val set = SetOfStacks(capacityPerSubstack)
        for (i in 0..33) {
            set.push(i)
        }
        for (i in 0..34) {
            println("Popped " + set.pop())
        }
    }
}