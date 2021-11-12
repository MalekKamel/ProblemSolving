package challenges.cracking_coding_interview.stacks_queues.animal_shelter

class Cat(n: String) : Animal(n) {
    override fun name(): String {
        return "Cat: $name"
    }
}