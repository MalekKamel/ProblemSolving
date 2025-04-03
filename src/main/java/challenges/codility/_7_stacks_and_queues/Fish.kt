package challenges.codility._7_stacks_and_queues

import java.util.Stack

/**
 * The Problem:
 *
 * You are given two non-empty arrays A and B consisting of N integers. Arrays A and B
 * represent N voracious fish in a river, ordered downstream along the flow of the river.
 *
 * The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially
 * upstream of fish Q. Initially, each fish has a unique position.
 *
 * Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. All its
 * elements are unique. Array B contains the directions of the fish. It contains only 0s and/or 1s,
 * where:
 *
 * 0 represents a fish flowing upstream,
 * 1 represents a fish flowing downstream.
 * If two fish move in opposite directions and there are no other (living) fish between them, they will
 * eventually meet each other. Then only one fish can stay alive − the larger fish eats the smaller one.
 * More precisely, we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and
 * there are no living fish between them. After they meet:
 *
 * If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
 * If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
 * We assume that all the fish are flowing at the same speed. That is, fish moving in the same direction
 * never meet. The goal is to calculate the number of fish that will stay alive.
 *
 * For example, consider arrays A and B such that:
 *
 *  A[0] = 4  B[0] = 0
 *  A[1] = 3  B[1] = 1
 *  A[2] = 2  B[2] = 0
 *  A[3] = 1  B[3] = 0
 *  A[4] = 5  B[4] = 0
 * Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets
 * fish number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish
 * number 4 and is eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay
 * alive.
 *
 * Write a function:
 *
 * fun solution(A: IntArray, B: IntArray): Int
 *
 * that, given two non-empty arrays A and B consisting of N integers, returns the number of fish that
 * will stay alive.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [0..1,000,000,000];
 * each element of array B is an integer that can have one of the following values: 0, 1;
 * the elements of A are all distinct.
 *
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
 */
class Fish {
    fun solution(A: IntArray, B: IntArray): Int {
        // 1. Problem Explanation
        // The problem describes a scenario with fish in a river. Each fish has a size and a
        // direction (upstream or downstream).
        // When a downstream fish encounters an upstream fish, the larger fish eats the smaller one.
        // The goal is to determine the number of fish that will remain alive after all possible
        // encounters.

        // 2. Pattern Identification and Rationale
        // The problem involves simulating interactions between adjacent fish moving in opposite
        // directions.
        // A stack data structure is suitable here to keep track of the downstream-moving fish.
        // When an upstream fish is encountered, we can compare it with the downstream fish at the
        // "front" of the flow (top of the stack).
        // This allows us to efficiently process the encounters in the correct order.

        // 3. Solution Breakdown
        // a. Initialize a stack to store the sizes of the downstream-moving fish.
        // b. Initialize a counter for the number of surviving fish. Initially, this will be the
        // total number of fish.
        // c. Iterate through the fish one by one.
        // d. For each fish:
        //    i. If the fish is moving downstream (B[i] == 1), push its size onto the stack.
        //    ii. If the fish is moving upstream (B[i] == 0):
        //       - While the stack of downstream fish is not empty:
        //         - Get the size of the last downstream fish (top of the stack).
        //         - If the current upstream fish is smaller than the downstream fish, the downstream
        //         fish eats it. The upstream fish is no longer alive in this encounter. Break the inner
        //         loop and move to the next fish.
        //         - If the current upstream fish is larger than the downstream fish, the upstream fish
        //         eats it. Pop the downstream fish from the stack and continue the comparison with
        //         the next downstream fish (if any).
        //       - If the inner loop completes and the stack is empty, it means the current upstream
        //       fish has survived all encounters with downstream fish. Increment the count of
        //       surviving fish.
        // e. After iterating through all the fish, the number of surviving fish will be the count of
        // upstream fish that survived plus the number of downstream fish remaining in the stack
        // (as they have not encountered any upstream fish).

        // 4. Efficient Implementation
        val downstreamFish = Stack<Int>()
        var survivors = 0
        val n = A.size

        for (i in 0 until n) {
            val size = A[i]
            val direction = B[i]

            if (direction == 1) { // Downstream
                downstreamFish.push(size)
            } else { // Upstream
                var eaten = false
                while (downstreamFish.isNotEmpty()) {
                    val downstreamSize = downstreamFish.peek()
                    if (downstreamSize > size) {
                        eaten = true
                        break
                    } else {
                        downstreamFish.pop()
                    }
                }
                if (!eaten) {
                    survivors++
                }
            }
        }

        survivors += downstreamFish.size
        return survivors
    }
}

fun main() {
    val solution = Fish()

    // Test case 1
    val A1 = intArrayOf(4, 3, 2, 1, 5)
    val B1 = intArrayOf(0, 1, 0, 0, 0)
    println(solution.solution(A1, B1)) // Expected output: 2

    // Test case 2
    val A2 = intArrayOf(1, 2)
    val B2 = intArrayOf(1, 0)
    println(solution.solution(A2, B2)) // Expected output: 1

    // Test case 3
    val A3 = intArrayOf(2, 1)
    val B3 = intArrayOf(1, 0)
    println(solution.solution(A3, B3)) // Expected output: 1

    // Test case 4
    val A4 = intArrayOf(2, 3, 4)
    val B4 = intArrayOf(1, 1, 1)
    println(solution.solution(A4, B4)) // Expected output: 3

    // Test case 5
    val A5 = intArrayOf(4, 3, 2)
    val B5 = intArrayOf(0, 0, 0)
    println(solution.solution(A5, B5)) // Expected output: 3

    // Test case 6
    val A6 = intArrayOf(1)
    val B6 = intArrayOf(0)
    println(solution.solution(A6, B6)) // Expected output: 1

    // Test case 7
    val A7 = intArrayOf(1)
    val B7 = intArrayOf(1)
    println(solution.solution(A7, B7)) // Expected output: 1
}

