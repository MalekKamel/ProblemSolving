package challenges.cracking_coding_interview.arrays_strings

object OneWay {

    private fun oneEditAway(first: String, second: String): Boolean {
        if (first.length == second.length)
            return oneEditReplace(first, second)

        if (first.length + 1 == second.length)
            return oneEditInsert(first, second)

        if (first.length - 1 == second.length)
            return oneEditInsert(second, first)
        return false
    }

    private fun oneEditReplace(s1: String, s2: String): Boolean {
        var foundDifference = false
        for (i in s1.indices) {
            if (s1[i] == s2[i]) continue
            if (foundDifference) return false
            foundDifference = true
        }
        return true
    }

    /**
     *  Check if you can insert a character into s1 to make s2.
     *  length of s1 + 1 == length of s2
     *  */
    private fun oneEditInsert(s1: String, s2: String): Boolean {
        var index1 = 0
        var index2 = 0
        while (index2 < s2.length && index1 < s1.length) {
            if (s1[index1] == s2[index2]) {
                index1++
                index2++
                continue
            }
            if (index1 != index2) return false
            index2++
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var a = "pse"
        var b = "pale"
        println("$a, $b: ${oneEditAway(a, b)}") // false

        a = "pale"
        b = "ple"
        println("$a, $b: ${oneEditAway(a, b)}") // true

        a = "pales"
        b = "pale"
        println("$a, $b: ${oneEditAway(a, b)}") // true

        a = "pale"
        b = "bale"
        println("$a, $b: ${oneEditAway(a, b)}") // true

        a = "pale"
        b = "bae"
        println("$a, $b: ${oneEditAway(a, b)}") // false
    }
}