package challenges.cracking_coding_interview.sorting_and_searching.sorted_search_no_size


class Listy(arr: IntArray) {
    var array: IntArray

    init {
        array = arr.clone()
    }

    fun elementAt(index: Int): Int {
        return if (index >= array.size) {
            -1
        } else array[index]
    }
}