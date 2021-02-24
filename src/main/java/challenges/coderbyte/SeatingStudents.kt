package challenges.coderbyte

/*
Have the function SeatingStudents(arr) read the array of integers stored in arr which will be in the
following format: [K, r1, r2, r3, ...] where K represents the number of desks in a classroom,
and the rest of the integers in the array will be in sorted order and will represent the desks
 that are already occupied. All of the desks will be arranged in 2 columns,
 where desk #1 is at the top left, desk #2 is at the top right, desk #3 is below #1, desk #4 is below #2, etc.
 Your program should return the number of ways 2 students can be seated next to each other.
 This means 1 student is on the left and 1 student on the right, or 1 student is directly above or below the other student.
For example: if arr is [12, 2, 6, 7, 11] then this classrooms looks like the following diagram:
 1   (2)
 3    4
 5   (6)
(7)   8
 9    10
(11)  12

Based on above arrangement of occupied desks, there are a total of 6 ways ([1,3][3,4][3,5][8,10][9,10][10,12])
to seat 2 new students next to each other. The combinations are: [1, 3], [3, 4], [3, 5], [8, 10], [9, 10], [10, 12].
So for this input your program should return 6. K will range from 2 to 24 and will always be an even number.
After K, the number of occupied desks in the array can range from 0 to K.

Sample Test Cases
Input:6, 4
Output:4
Input:8, 1, 8
Output:6
*/
object SeatingStudents {

    /*
    Solution Steps:
    1- Fill all occupied sets with 1, empty with 0
    2- Increase count if behind/beside seats found
     */
    fun seatingStudents(arr: Array<Int>): Int {
        val k = arr[0]
        val seats = createSeats(arr)
        var count = 0
        count += pairsBeside(seats, k)
        count += pairsBehind(seats, k)
        return count
    }

    private fun createSeats(arr: Array<Int>): Array<IntArray> {
        val k = arr[0]
        val seats = Array(k / 2) { IntArray(2) }
        var index = 1
        for (i in 0 until k / 2) {
            for (j in 0..1) {
                if (index < arr.size && arr[index] == i * 2 + j + 1) {
                    seats[i][j] = 1
                    index++
                }
            }
        }
        return seats
    }

    private fun pairsBeside(seats: Array<IntArray>, k: Int): Int {
        var count = 0
        for (i in 0 until k / 2) {
            if (seats[i][0] == 0 && seats[i][1] == 0) {
                count++
            }
        }
        return count
    }

    private fun pairsBehind(seats: Array<IntArray>, k: Int): Int {
        var count = 0
        for (j in 0..1) {
            for (i in 0 until k / 2 - 1) {
                if (seats[i][j] == 0 && seats[i + 1][j] == 0) {
                    count++
                }
            }
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(6, 4)
        val input2 = arrayOf(8, 1, 8)
        println(seatingStudents(input))
        println(seatingStudents(input2))
    }
}