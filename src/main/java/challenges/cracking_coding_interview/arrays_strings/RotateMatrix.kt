package challenges.cracking_coding_interview.arrays_strings

import challenges.util.AssortedMethods

/*
Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
 write a method to rotate the image by 90 degrees. Can you do this in place?
 */
object RotateMatrix {

    fun rotate(matrix: Array<IntArray>): Boolean {
        if (matrix.isEmpty() || matrix.size != matrix[0].size) return false // Not a square
        val n = matrix.size
        for (layer in 0 until n / 2) {
            val last = n - 1 - layer
            for (i in layer until last) {
                val offset = i - layer

                // Top    -> matrix[layer][i]
                // Bottom -> matrix[last][last - offset]
                // Left   -> matrix[last - offset][layer]
                // Right  -> matrix[i][last]

                val top = matrix[layer][i] // save top

                // left -> top
                matrix[layer][i] = matrix[last - offset][layer]

                // bottom -> left
                matrix[last - offset][layer] = matrix[last][last - offset]

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last]

                // top -> right
                matrix[i][last] = top // right <- saved top
            }
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix: Array<IntArray> = AssortedMethods.randomMatrix(3, 3, 0, 9)
        AssortedMethods.printMatrix(matrix)
        rotate(matrix)
        println()
        AssortedMethods.printMatrix(matrix)
    }
}