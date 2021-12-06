package challenges.cracking_coding_interview.object_oriented_design.circular_array

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