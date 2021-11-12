package challenges.cracking_coding_interview.stacks_queues.animal_shelter

class Dog(n: String) : Animal(n) {
    override fun name(): String {
        return "Dog: $name"
    }
}