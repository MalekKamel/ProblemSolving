package challenges.cracking_coding_interview.sorting_and_searching.sparse_search

object QuestionA {
    fun search(strings: Array<String>, str: String, first: Int, last: Int): Int {
        if (first > last) {
            return -1
        }

        /* Move mid to the middle */
        var mid = (last + first) / 2

        /* If mid is empty, find closest non-empty string. */if (strings[mid].isEmpty()) {
            var left = mid - 1
            var right = mid + 1
            while (true) {
                if (left < first && right > last) {
                    return -1
                } else if (right <= last && !strings[right].isEmpty()) {
                    mid = right
                    break
                } else if (left >= first && !strings[left].isEmpty()) {
                    mid = left
                    break
                }
                right++
                left--
            }
        }

        /* Check for string, and recurse if necessary */return if (str == strings[mid]) { // Found it!
            mid
        } else if (strings[mid].compareTo(str) < 0) { // Search right
            search(
                strings,
                str,
                mid + 1,
                last
            )
        } else { // Search left
            search(
                strings,
                str,
                first,
                mid - 1
            )
        }
    }

    fun search(strings: Array<String>?, str: String?): Int {
        return if (strings == null || str == null || str.isEmpty()) {
            -1
        } else search(
            strings,
            str,
            0,
            strings.size - 1
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val stringList = arrayOf("apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower")
        println(search(stringList, "ac"))

        //for (String s : stringList) {
        //	String cloned = new String(s);
        //	println("<" + cloned + "> " + " appears at location " + search(stringList, cloned));
        //}
    }
}