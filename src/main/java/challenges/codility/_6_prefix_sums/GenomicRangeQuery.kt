package challenges.codility._6_prefix_sums

class GenomicRangeQuery {
    /**
     * A DNA sequence can be represented as a string consisting of the letters A, C, G and T,
     * which correspond to the types of successive nucleotides in the sequence. Each nucleotide
     * has an impact factor, which is an integer. Nucleotides of types A, C, G and T have
     * impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries
     * of the form: What is the minimal impact factor of nucleotides contained in a particular
     * part of the given DNA sequence?
     *
     * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters.
     * There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
     * The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides
     * contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
     *
     * For example, consider string S = CAGCCTA and arrays P, Q such that:
     *
     * P[0] = 2     Q[0] = 4
     * P[1] = 5     Q[1] = 5
     * P[2] = 0     Q[2] = 6
     * The answers to these M = 3 queries are as follows:
     *
     * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice),
     * whose impact factors are 3 and 2 respectively, so the answer is 2.
     * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4,
     * so the answer is 4.
     * The part between positions 0 and 6 (the whole string) contains all nucleotides,
     * in particular nucleotide A whose impact factor is 1, so the answer is 1.
     * Write a function:
     *
     * fun solution(S: String, P: IntArray, Q: IntArray): IntArray
     *
     * that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q
     * consisting of M integers, returns an array consisting of M integers specifying the consecutive
     * answers to all queries.
     *
     * Result array should be returned as an array of integers.
     *
     * For example, given the string S = CAGCCTA and arrays P, Q such that:
     *
     * P[0] = 2     Q[0] = 4
     * P[1] = 5     Q[1] = 5
     * P[2] = 0     Q[2] = 6
     * the function should return the values [2, 4, 1], as explained above.
     *
     * Write an efficient algorithm for the following assumptions:
     *
     * N is an integer within the range [1..100,000];
     * M is an integer within the range [1..50,000];
     * each element of arrays P and Q is an integer within the range [0..N - 1];
     * P[K] ≤ Q[K], where 0 ≤ K < M;
     * string S consists only of upper-case English letters A, C, G, T.
     *
     * https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/
     */
    fun solution(S: String, P: IntArray, Q: IntArray): IntArray {
        // 1. Problem Explanation
        // The problem asks us to find the minimum impact factor within specified ranges of a DNA sequence.
        // The impact factors for 'A', 'C', 'G', and 'T' are 1, 2, 3, and 4, respectively. We need to process
        // multiple queries, each defined by a start and end index, and return an array of the minimum
        // impact factors for each query.

        // 2. Pattern Identification and Rationale
        // The problem involves answering multiple range queries on a static array (the DNA sequence).
        // For each query, we need to find the minimum impact factor present in the given range.
        // A suitable pattern for efficiently handling such range queries is using prefix sums or a similar
        // pre-computation technique. In this case, instead of summing, we can track the occurrences of
        // each nucleotide type up to each index. This will allow us to quickly determine if a particular
        // nucleotide exists within a given range.

        // 3. Solution Breakdown
        // a. Create prefix sum arrays for each nucleotide ('A', 'C', 'G', 'T'). Each array will store the count
        //    of that nucleotide up to a given index in the DNA sequence.
        // b. Iterate through the DNA sequence and populate these prefix sum arrays.
        // c. For each query (defined by P[i] and Q[i]), check for the presence of each nucleotide type
        //    in the range [P[i], Q[i]] using the prefix sum arrays.
        // d. Start checking from the nucleotide with the lowest impact factor ('A') and proceed upwards.
        // e. The impact factor of the first nucleotide found to be present in the range is the minimum impact factor for that query.

        val n = S.length
        val m = P.size
        val result = IntArray(m)

        // Create prefix count arrays for A, C, G, T
        val countA = IntArray(n + 1)
        val countC = IntArray(n + 1)
        val countG = IntArray(n + 1)
        val countT = IntArray(n + 1)

        // Populate the prefix count arrays
        for (i in 0 until n) {
            countA[i + 1] = countA[i]
            countC[i + 1] = countC[i]
            countG[i + 1] = countG[i]
            countT[i + 1] = countT[i]

            when (S[i]) {
                'A' -> countA[i + 1]++
                'C' -> countC[i + 1]++
                'G' -> countG[i + 1]++
                'T' -> countT[i + 1]++
            }
        }

        // Process each query
        for (i in 0 until m) {
            val start = P[i]
            val end = Q[i]

            // Check for the presence of each nucleotide in the range [start, end]
            if (countA[end + 1] - countA[start] > 0) {
                result[i] = 1
            } else if (countC[end + 1] - countC[start] > 0) {
                result[i] = 2
            } else if (countG[end + 1] - countG[start] > 0) {
                result[i] = 3
            } else if (countT[end + 1] - countT[start] > 0) {
                result[i] = 4
            }
        }

        return result
    }
}

fun main() {
    val s = "CAGCCTA"
    val p = intArrayOf(2, 5, 0)
    val q = intArrayOf(4, 5, 6)
    val solution = GenomicRangeQuery()
    val result = solution.solution(s, p, q)
    println(result.contentToString()) // Expected output: [2, 4, 1]
}