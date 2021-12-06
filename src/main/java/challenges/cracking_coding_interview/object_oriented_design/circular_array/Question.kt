package challenges.cracking_coding_interview.object_oriented_design.circular_array

/**
 * Implement a CircularArray class that supports an array-like data structure which can be efficiently rotated.
 * If possible, the class should use a generic type (also called a template), and
 * should support iteration via the standard for (Obj o : circularArray)notation.
 */
object Question {
    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val size = 10
        val array = CircularArray<String>(size)
        for (i in 0 until size) {
            array[i] = i.toString()
        }
        array.rotate(3)
        for (i in 0 until size) {
            println(array[i])
        }
        println("")
        array.rotate(2)
        for (s in array) {
            println(s)
        }
    }
}