package challenges.cracking_coding_interview.stacks_queues.stack_min


object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val stack = StackMinA()
        val stack2 = StackWithMinB()
        val array = intArrayOf(2, 1, 3, 1)
        for (value in array) {
            stack.push(value)
            stack2.push(value)
            print("$value, ")
        }
        println('\n')
        for (i in array.indices) {
            println("Popped " + stack.pop().value.toString() + ", " + stack2.pop())
            println("New min is " + stack.min().toString() + ", " + stack2.min())
        }
    }
}