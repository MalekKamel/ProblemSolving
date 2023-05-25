package challenges.util

class PrintHyphens {

    companion object {
        fun repeat(string: String, count: Int = 100): String {
            val result = StringBuilder("")
            for (i in 0 until count) {
                result.append(string)
            }
            return result.toString()
        }
    }
}