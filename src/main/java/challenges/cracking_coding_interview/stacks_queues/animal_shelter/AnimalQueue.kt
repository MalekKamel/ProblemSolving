package challenges.cracking_coding_interview.stacks_queues.animal_shelter

import java.util.*

class AnimalQueue {
    var dogs: LinkedList<Dog> = LinkedList<Dog>()
    var cats: LinkedList<Cat> = LinkedList<Cat>()
    private var order = 0

    fun enqueue(a: Animal) {
        a.order = order
        order++

        if (a is Dog) {
            dogs.addLast(a)
        } else if (a is Cat) {
            cats.addLast(a)
        }
    }

    fun dequeueAny(): Animal {
        if (dogs.isEmpty()) {
            return dequeueCats()
        } else if (cats.isEmpty()) {
            return dequeueDogs()
        }
        val dog: Dog = dogs.peek()
        val cat: Cat = cats.peek()
        return if (dog.isOlderThan(cat)) dogs.poll() else cats.poll()
    }

    fun peek(): Animal {
        if (dogs.isEmpty()) {
            return cats.peek()
        } else if (cats.isEmpty()) {
            return dogs.peek()
        }
        val dog: Dog = dogs.peek()
        val cat: Cat = cats.peek()
        return if (dog.isOlderThan(cat)) dog else cat
    }

    fun size(): Int {
        return dogs.size + cats.size
    }

    fun dequeueDogs(): Dog {
        return dogs.poll()
    }

    fun peekDogs(): Dog {
        return dogs.peek()
    }

    fun dequeueCats(): Cat {
        return cats.poll()
    }

    fun peekCats(): Cat {
        return cats.peek()
    }
}