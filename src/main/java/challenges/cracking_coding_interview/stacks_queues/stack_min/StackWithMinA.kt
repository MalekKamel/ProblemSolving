package challenges.cracking_coding_interview.stacks_queues.stack_min

import java.util.*


class StackMinA : Stack<NodeWithMin>() {
    fun push(value: Int) {
        val newMin = value.coerceAtMost(min())
        super.push(NodeWithMin(value, newMin))
    }

    fun min(): Int {
        return if (this.isEmpty()) {
            Int.MAX_VALUE
        } else {
            peek().min
        }
    }
}

class NodeWithMin(var value: Int, var min: Int)