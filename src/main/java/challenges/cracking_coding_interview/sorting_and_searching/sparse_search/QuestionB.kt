package challenges.cracking_coding_interview.sorting_and_searching.sparse_search

object QuestionB {
    fun searchI(strings: Array<String>, str: String?, first: Int, last: Int): Int {
        var first = first
        var last = last
        while (first <= last) {
            /* Move mid to the middle */
            var mid = (last + first) / 2

            /* If mid is empty, find closest non-empty string */if (strings[mid].isEmpty()) {
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
            val res = strings[mid].compareTo(str!!)
            if (res == 0) { // Found it!
                return mid
            } else if (res < 0) { // Search right
                first = mid + 1
            } else { // Search left
                last = mid - 1
            }
        }
        return -1
    }

    private fun searchR(strings: Array<String>, str: String, first: Int, last: Int): Int {
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
            searchR(
                strings,
                str,
                mid + 1,
                last
            )
        } else { // Search left
            searchR(
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
        } else searchR(
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