package challenges.coderbyte

object StringReduction {

    fun stringReductionEN(str: String): String {
        val sum = ('a'.toInt() + 'b'.toInt() + 'c'.toInt())
        return reduce(str, sum).length.toString()
    }

    private fun reduce(str: String, sum: Int): String {
        if (str.length == 1) return str
        var result = ""
        var i = 0
        while (i < str.length) {
            if (i + 1 < str.length && str[i] != str[i + 1]) {
                result += (sum - str[i].toInt() - str[i + 1].toInt()).toChar()
                i += 2
                continue
            }
            result += str[i]
            i++
        }
        // if the result is equal to str, this means all chars are equal
        // So we don't need further processing
        if (result == str) return str
        return reduce(result, sum)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(stringReductionEN("abcabc"))
        println(stringReductionEN("cccc"))
    }
}