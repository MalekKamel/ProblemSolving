package challenges.cracking_coding_interview.object_oriented_design.file_system

import challenges.cracking_coding_interview.object_oriented_design.file_system.Directory
import kotlin.jvm.JvmStatic

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = Directory("Food", null)
        val taco = File("Taco", root, 4)
        val hamburger = File("Hamburger", root, 9)
        root.addEntry(taco)
        root.addEntry(hamburger)
        val healthy = Directory("Healthy", root)
        val fruits = Directory("Fruits", healthy)
        val apple = File("Apple", fruits, 5)
        val banana = File("Banana", fruits, 6)
        fruits.addEntry(apple)
        fruits.addEntry(banana)
        healthy.addEntry(fruits)
        val veggies = Directory("Veggies", healthy)
        val carrot = File("Carrot", veggies, 6)
        val lettuce = File("Lettuce", veggies, 7)
        val peas = File("Peas", veggies, 4)
        veggies.addEntry(carrot)
        veggies.addEntry(lettuce)
        veggies.addEntry(peas)
        healthy.addEntry(veggies)
        root.addEntry(healthy)
        println(root.numberOfFiles())
        println(veggies.fullPath)
    }
}