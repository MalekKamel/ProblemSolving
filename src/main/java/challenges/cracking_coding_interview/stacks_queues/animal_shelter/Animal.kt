package challenges.cracking_coding_interview.stacks_queues.animal_shelter

abstract class Animal(protected var name: String) {
    var order = 0
    abstract fun name(): String?
    fun isOlderThan(a: Animal): Boolean {
        return order < a.order
    }
}