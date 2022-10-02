package challenges.cracking_coding_interview.sorting_and_searching.find_duplicates

class BitSet(size: Int) {
    var bitset: IntArray

    init {
        bitset = IntArray((size shr 5) + 1) // divide by 32
    }

    operator fun get(pos: Int): Boolean {
        val wordNumber = pos shr 5 // divide by 32
        val bitNumber = pos and 0x1F // mod 32
        return bitset[wordNumber] and (1 shl bitNumber) != 0
    }

    fun set(pos: Int) {
        val wordNumber = pos shr 5 // divide by 32
        val bitNumber = pos and 0x1F // mod 32
        bitset[wordNumber] = bitset[wordNumber] or (1 shl bitNumber)
    }
}