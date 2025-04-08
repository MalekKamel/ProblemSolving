package challenges.mostafa._8_hashtable

/**
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and
returns -1 if there is no such integer.
void add(int value) insert value to the queue.

Example 1:

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output:
[null,2,null,2,null,3,null,-1]
Explanation:
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1

Example 2:

Input:
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output:
[null,-1,null,null,null,null,null,17]
Explanation:
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17

Example 3:

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output:
[null,809,null,-1]
Explanation:
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
1 <= value <= 10^8
At most 50000 calls will be made to showFirstUnique and add.

https://leetcode.ca/all/1429.html
 */

/**
 * Problem Explanation: Your code implicitly understands the problem of finding the first unique element
 * in a queue-like structure with add operations.
 *
 * Pattern Identification and Rationale: You've correctly identified the need to maintain the order of
 * elements (hence the queue) and track the frequency of each element (hence the uniqueMap). This aligns
 * with the core requirements.
 *
 * Solution Breakdown:
 *
 * Initialization (init): You iterate through the initial array and call add for each element, which is
 * a correct way to populate your data structures.
 * showFirstUnique(): This is where the main efficiency concern lies. In the worst case, to find the first
 * unique element, you might have to iterate through the entire queue using queue.first() and queue.removeFirst()
 * until you find a unique element or the queue becomes empty. Removing from the beginning of a MutableList
 * (which is backed by an ArrayList) takes O(N) time on average because all subsequent elements need to be shifted.
 * add(value): Adding to the end of a MutableList is typically O(1) on average. Updating the uniqueMap is
 * also O(1) on average.
 * Time Complexity:
 *
 * FirstUnique(nums): O(N) due to the initial iteration and calls to add.
 * showFirstUnique(): O(N) in the worst case, where N is the number of elements in the queue. This is because
 * you might have to remove and check every element from the front until you find a unique one or the queue is empty.
 * add(value): O(1) on average.
 * Efficient Implementation (Considerations):
 *
 * Efficiency of showFirstUnique(): The repeated removal from the front of the MutableList in showFirstUnique()
 * is the bottleneck. As mentioned, removeFirst() on an ArrayList-backed MutableList is O(N).
 * Alternative for Queue: Using java.util.LinkedList for the queue would make removeFirst() an O(1) operation.
 * Maintaining Order of Unique Elements: You're relying on the order within the queue to determine the "first"
 * unique element. However, as you remove non-unique elements from the front, the indices of the remaining elements
 * shift, which is handled correctly but contributes to the O(N) complexity of showFirstUnique().
 */
class FirstUnique(nums: IntArray) {
    private val queue = nums.toMutableList()
    private val map = mutableMapOf<Int, Int>()

    init {
        for (num in nums) {
            add(num)
        }
    }

    fun showFirstUnique(): Int {
        while (queue.isNotEmpty()) {
            val first = queue.first()
            if (map[first] == 1) return first
            queue.removeFirst()
        }
        return -1
    }

    fun add(value: Int) {
        if (map.containsKey(value)) {
            map[value] = map[value]!! + 1
            return
        }
        map[value] = 1
        queue.add(value)
    }
}

fun main() {
    val firstUnique = FirstUnique(intArrayOf(2, 3, 5))
    println(firstUnique.showFirstUnique()) // Output: 2
    firstUnique.add(5)
    println(firstUnique.showFirstUnique()) // Output: 2
    firstUnique.add(2)
    println(firstUnique.showFirstUnique()) // Output: 3
    firstUnique.add(3)
    println(firstUnique.showFirstUnique()) // Output: -1
}