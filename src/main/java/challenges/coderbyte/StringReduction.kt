package challenges.coderbyte

/*
Have the function StringReduction(str) take the str parameter being passed and return the smallest
number you can get through the following reduction method. The method is: Only the letters a, b, and c
will be given in str and you must take two different adjacent characters and replace it with the third.
For example "ac" can be replaced with "b" but "aa" cannot be replaced with anything.
This method is done repeatedly until the string cannot be further reduced,
and the length of the resulting string is to be outputted.

For example: if str is "cab", then "ca" can be reduced to "b" and you get "bb" (you can also reduce it to "cc").
The reduction is done so the output should be 2. If str is
"bcab", "bc" reduces to "a", so you have "aab", then "ab" reduces to "c", and the final string "ac"
is reduced to "b" so the output should be 1.
 */
object StringReduction {

    private fun stringReduction(str: String): String {
        val sum = 'a'.toInt() + 'b'.toInt() + 'c'.toInt()
        return reduce(str, sum).length.toString()
    }

    private fun reduce(str: String, sum: Int): String {
        if (str.length == 1) return str
        var result = ""
        var i = 0

        val length = str.length

        while (i < length) {
            val current = str[i]

            if (i + 1 < length && current != str[i + 1]) {
                val new = sum - current.toInt() - str[i + 1].toInt()
                result += new.toChar()
                i += 2
                continue
            }

            result += current
            i++
        }

        // if the result is equal to str, this means all chars are equal
        // So we don't need further processing
        if (result == str) return str
        return reduce(result, sum)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(stringReduction("abcabc")) // 2
        println(stringReduction("cccc")) // 4
    }
}