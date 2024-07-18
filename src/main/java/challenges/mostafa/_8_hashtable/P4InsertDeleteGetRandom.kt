package challenges.mostafa._8_hashtable

import java.util.*
import kotlin.random.Random

/**
Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item
was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item
was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed
that at least one element exists when this method is called). Each element must have
the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1)
time complexity.

Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

Constraints:

-231 <= val <= 231 - 1
At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.

https://leetcode.com/problems/insert-delete-getrandom-o1/description/
 */

class RandomizedSet {

    /**
    The use of a set in this problem, specifically a `HashSet`, is crucial for the efficient
    implementation of the `RandomizedSet` class. Here's why:

    1. **Constant time lookup and membership check**: The main purpose of using a `HashSet`
    is to provide constant-time (`O(1)`) lookup and membership check operations. This is crucial
    for the `insert` and `remove` operations, where we need to quickly determine if a value is already present in the set.

    2. **Uniqueness guarantee**: The `HashSet` ensures that each element in the set is unique.
    This is important for the `insert` operation, where we need to prevent duplicate values from
    being added to the data structure.

    3. **Complementary data structure**: The `ArrayList` is used to maintain the order of
    the elements, which is required for the `getRandom` operation to return a random element from
    the set. By keeping track of the elements in both the `HashSet` and the `ArrayList`, we can
    efficiently perform all the required operations.

    Here's how the combination of the `HashSet` and `ArrayList` works:

    1. **insert(value)**: The `HashSet` is used to quickly check if the `value` is already
    present in the set. If not, the `value` is added to both the `HashSet` and the `ArrayList`.
    2. **remove(value)**: The `HashSet` is used to quickly check if the `value` is present in
    the set. If it is, the index of the `value` in the `ArrayList` is found, the last element in
    the `ArrayList` is swapped with the element at that index, and the last element is then
    removed from the `ArrayList`. Finally, the `value` is removed from the `HashSet`.
    3. **getRandom()**: A random index is generated within the range of the `ArrayList`'s size,
    and the element at that index is returned. This operation is efficient because the `ArrayList`
    maintains the order of the elements.

    By using the `HashSet` and `ArrayList` in combination, the `RandomizedSet` class can achieve
    constant-time complexity for the `insert`, `remove`, and `getRandom` operations, which is
    the desired performance for this problem.
     */
    private val set = HashSet<Int>()
    private val list = ArrayList<Int>()

    /**
    The time complexity of the provided `RandomizedSet` implementation is as follows:

    1. **insert(value: Int)**: The time complexity of this operation is **O(1)**.
    - Looking up the value in the `set` (HashSet) takes constant time, O(1).
    - Adding the value to the `set` and the `list` (ArrayList) also take constant time, O(1).

    2. **remove(value: Int)**: The time complexity of this operation is **O(1)**.
    - Looking up the value in the `set` (HashSet) takes constant time, O(1).
    - Finding the index of the value in the `list` (ArrayList) takes linear time, O(n),
    but this is followed by a constant time operation to swap the last element with the element
    at the index, and then removing the last element, which are both O(1).
    - Removing the value from the `set` (HashSet) takes constant time, O(1).

    3. **getRandom()**: The time complexity of this operation is **O(1)**.
    - Generating a random index and accessing the corresponding element in the `list` (ArrayList)
    both take constant time, O(1).

    In summary, the time complexities of the `RandomizedSet` operations are:

    - `insert(value: Int)`: O(1)
    - `remove(value: Int)`: O(1)
    - `getRandom()`: O(1)

    This implementation using a HashSet and an ArrayList provides constant time complexity for
    the key operations, making it an efficient implementation of a randomized set.
     */
    fun insert(value: Int): Boolean {
        // If the value is already in the set, return false
        if (set.contains(value)) return false

        // Add the value to the set and the list
        set.add(value)
        list.add(value)
        return true
    }

    fun remove(value: Int): Boolean {
        // If the value is not in the set, return false
        if (!set.contains(value)) return false

        // Find the index of the value in the list
        val index = list.indexOf(value)

        // Swap the last element in the list with the element at the index
        val lastElement = list.last()
        list[index] = lastElement
        list.removeLast()

        // Remove the value from the set
        set.remove(value)
        return true
    }

    fun getRandom(): Int {
        // Get a random index from the list and return the corresponding value
        val randomIndex = Random.nextInt(list.size)
        return list[randomIndex]
    }
}

fun main() {
    val randomizedSet = RandomizedSet()
    println(randomizedSet.insert(1)) // true
    println(randomizedSet.remove(2)) // false
    println(randomizedSet.insert(2)) // true
    println(randomizedSet.getRandom()) // 1 or 2
    println(randomizedSet.remove(1)) // true
    println(randomizedSet.insert(2)) // false
    println(randomizedSet.getRandom()) // 2
}