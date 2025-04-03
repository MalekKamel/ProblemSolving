package challenges.codility._7_stacks_and_queues

import java.util.*

/**
 * The Problem:
 *
 * You are going to build a stone wall. The wall should be straight and N meters long, and its
 * thickness should be constant; however, it should have different heights in different places. The
 * height of the wall is specified by an array H of N positive integers. H[I] is the height of the wall
 * from I to I+1 meters to the right of its left end. In particular, H[0] is the height of the wall's
 * left end and H[N−1] is the height of the wall's right end.
 *
 * The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular).
 * Your task is to compute the minimum number of blocks needed to build the wall.
 *
 * Write a function:
 *
 * fun solution(H: IntArray): Int
 *
 * that, given an array H of N positive integers specifying the height of the wall, returns the minimum
 * number of blocks needed to build it.
 *
 * For example, given array H containing N = 9 integers:
 *
 *     H[0] = 8    H[1] = 8    H[2] = 5
 *     H[3] = 7    H[4] = 9    H[5] = 8
 *     H[6] = 7    H[7] = 4    H[8] = 8
 * the function should return 7. The figure shows one possible arrangement of seven blocks.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array H is an integer within the range [1..1,000,000,000].
 *
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 */
class StoneWall {
    fun solution(H: IntArray): Int {

        /**
         * 1. Problem Explanation
         *         The problem asks us to find the minimum number of cuboid blocks needed to build a wall
         * of varying heights specified by an array H. Each H[i] represents the height of the wall
         * from meter i to i+1.
         *
         * 2. Pattern Identification and Rationale
         *         The problem can be solved efficiently using a stack-based approach. We iterate through
         *         the height array, maintaining a stack of the heights of the blocks currently used. The
         * stack helps us keep track of the layers of blocks. When the height increases, we might
         * need to add new blocks. When the height decreases, we can potentially reuse existing
         * blocks from lower levels.
         *
         * 3. Solution Breakdown
         *         - Initialize a counter `blocksCount` to keep track of the minimum number of blocks.
         * - Initialize an empty stack `stack` to store the heights of the blocks currently forming
         *         the wall.
         * - Iterate through the input height array `H`.
         * - For each height `h` in `H`:
         * - While the stack is not empty and the top element of the stack is greater than the
         * current height `h`, it means the current height is lower, so the blocks at the higher
         * level are no longer needed at this point. We pop these higher block heights from the stack.
         * - If the stack is empty or the top element of the stack is less than the current
         * height `h`, it means we need to add a new block (or blocks) to reach the current height.
         * We push the current height `h` onto the stack and increment the `blocksCount`. We only
         *         increment the count when we are adding a new block level that wasn't present before at
         * the current horizontal position.
         *
         *  Let's try to visualize the wall and the blocks. Imagine the wall is being built from the ground up.
         *
         * Consider the example H = [8, 8, 5, 7, 9, 8, 7, 4, 8].
         *
         * Step 1: Height 8
         * We need a base of height 8. We use one block of height 8.
         * Blocks: 1, Stack: [8]
         *
         * Step 2: Height 8
         * The height is still 8. We don't need a new block on top of the existing 8. The current block continues.
         * Blocks: 1, Stack: [8]
         *
         * Step 3: Height 5
         * The height drops to 5. The block of height 8 is now too high. We conceptually remove the top part of height 3. So, the block of height 8 ends here. Now we need a wall of height 5. Since we have no blocks of height 5 in our current structure, we need to add a new block of height 5.
         * Blocks: 2, Stack: [5] (The 8 is gone because the height decreased)
         *
         * Step 4: Height 7
         * The height increases to 7. We currently have a block of height 5. To reach 7, we need to add a block of height 2 on top. So, we add a new "level" reaching height 7.
         * Blocks: 3, Stack: [5, 7]
         *
         * Step 5: Height 9
         * The height increases to 9. We currently have blocks reaching height 7. To reach 9, we need to add a block of height 2 on top.
         * Blocks: 4, Stack: [5, 7, 9]
         *
         * Step 6: Height 8
         * The height drops to 8. The block of height 9 is now too high. It ends. We are currently at height 7. To reach 8, we need to add a block of height 1 on top.
         * Blocks: 5, Stack: [5, 7, 8]
         *
         * Step 7: Height 7
         * The height drops to 7. The block of height 8 is too high. It ends. We are currently at height 7, which is what we need.
         * Blocks: 5, Stack: [5, 7]
         *
         * Step 8: Height 4
         * The height drops to 4. The block of height 7 is too high (ends). We are at height 5, which is also too high (ends). Now we need to build to height 4. We add a new block of height 4.
         * Blocks: 6, Stack: [4]
         *
         * Step 9: Height 8
         * The height increases to 8. We are currently at height 4. We need to add a block of height 4 on top to reach 8.
         * Blocks: 7, Stack: [4, 8]
         *
         * The final count is 7.
         *
         * Now let's map this back to the stack operations:
         *
         * When the height increases (compared to the top of the stack), we need a new block (or layer), so we push onto the stack and increment the count.
         * When the height decreases, it means the blocks that were at the higher level are no longer needed. We pop them from the stack. We don't increment the count when we pop because those blocks were counted when they were initially placed.
         * We only push onto the stack and increment the count if the current height is strictly greater than the top of the stack after we have popped any taller heights. If the current height is equal to the top of the stack, we don't need a new block.
         * The stack maintains the heights of the blocks that are currently "active" at the current horizontal position. Each time we add a new height to the stack (when it's greater than the previous top), it signifies a new block being used. When we decrease in height, we are essentially finishing the use of some blocks.
         *
         * Think of the stack as keeping track of the "levels" of blocks that are currently contributing to the wall's height. When the height goes up, we add a new level. When it goes down, we remove levels that are no longer needed. Each time we add a new unique level, it corresponds to a new block (or a set of blocks forming that level across the width).
         *
         */

        // 4. Efficient Implementation
        var blocksCount = 0
        val stack = Stack<Int>()

        for (h in H) {
            while (!stack.isEmpty() && stack.peek() > h) {
                stack.pop()
            }
            if (stack.isEmpty() || stack.peek() < h) {
                stack.push(h)
                blocksCount++
            }
        }

        return blocksCount
    }
}

fun main() {
    val solution = StoneWall()

    // Test case 1
    val H1 = intArrayOf(8, 8, 5, 7, 9, 8, 7, 4, 8)
    println(solution.solution(H1)) // Expected output: 7

    // Test case 2: Single height
    val H2 = intArrayOf(5)
    println(solution.solution(H2)) // Expected output: 1

    // Test case 3: Increasing heights
    val H3 = intArrayOf(1, 2, 3, 4, 5)
    println(solution.solution(H3)) // Expected output: 5

    // Test case 4: Decreasing heights
    val H4 = intArrayOf(5, 4, 3, 2, 1)
    println(solution.solution(H4)) // Expected output: 5

    // Test case 5: Constant height
    val H5 = intArrayOf(1, 1, 1, 1, 1)
    println(solution.solution(H5)) // Expected output: 1

    // Test case 6: More complex case
    val H6 = intArrayOf(2, 1, 2, 3, 2, 1, 2)
    println(solution.solution(H6)) // Expected output: 5
}