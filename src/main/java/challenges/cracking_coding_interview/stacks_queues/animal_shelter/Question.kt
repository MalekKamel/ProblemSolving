package challenges.cracking_coding_interview.stacks_queues.animal_shelter


/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis.
 * People must adopt either the"oldest" (based on arrival time) of all animals at the shelter, or they can
 * select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
 * They cannot select which specific animal they would like.
 * Create the data structures to maintain this system and implement operations such as enqueue,
 * dequeueAny, dequeueDog, and dequeueCat. You may use the built-in Linked list data structure.
 */
object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val animals = AnimalQueue()
        animals.enqueue(Cat("Callie"))
        animals.enqueue(Cat("Kiki"))
        animals.enqueue(Dog("Fido"))
        animals.enqueue(Dog("Dora"))
        animals.enqueue(Cat("Kari"))
        animals.enqueue(Dog("Dexter"))
        animals.enqueue(Dog("Dobo"))
        animals.enqueue(Cat("Copa"))
        println(animals.dequeueAny().name())
        println(animals.dequeueAny().name())
        println(animals.dequeueAny().name())
        animals.enqueue(Dog("Dapa"))
        animals.enqueue(Cat("Kilo"))
        while (animals.size() != 0) {
            println(animals.dequeueAny().name())
        }
    }
}