package challenges.cracking_coding_interview.sorting_and_searching.rank_from_stream

class IntComparable : Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        return o1.compareTo(o2)
    }
}