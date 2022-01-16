package challenges.cracking_coding_interview.sorting_and_searching.rank_from_stream

class RankNode(d: Int) {
    private var leftSize = 0
    var left: RankNode? = null
    var right: RankNode? = null
    var data = 0

    init {
        data = d
    }

    fun insert(d: Int) {
        if (d <= data) {
            if (left != null) {
                left!!.insert(d)
            } else {
                left = RankNode(d)
            }
            leftSize++
        } else {
            if (right != null) {
                right!!.insert(d)
            } else {
                right = RankNode(d)
            }
        }
    }

    fun getRank(d: Int): Int {
        return if (d == data) {
            leftSize
        } else if (d < data) {
            if (left == null) {
                -1
            } else {
                left!!.getRank(d)
            }
        } else {
            val right_rank = if (right == null) -1 else right!!.getRank(d)
            if (right_rank == -1) {
                -1
            } else {
                leftSize + 1 + right_rank
            }
        }
    }
}