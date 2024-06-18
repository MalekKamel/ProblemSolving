package challenges.mostafa._7_binarytree

/**
Implement the BSTIterator class that represents an iterator over the in-order traversal of
a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST
is given as part of the constructor. The pointer should be initialized to a non-existent
number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of
the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call
to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least
a next number in the in-order traversal when next() is called.

Example 1:

Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False

Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, and next.

Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory,
where h is the height of the tree?

https://leetcode.com/problems/binary-search-tree-iterator/description/
 */

internal class P6BSTIterator(root: TreeNode?) {
    private val stack = mutableListOf<TreeNode>()

    init {
        pushLeftBranch(root)
    }

    fun next(): Int {
        val current = stack.removeLast()
        pushLeftBranch(current.right)
        return current.`value`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

    private fun pushLeftBranch(node: TreeNode?) {
        var current = node
        while (current != null) {
            stack.add(current)
            current = current.left
        }
    }
}

class TreeNode(
    var `value`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun main(args: Array<String>) {
    // Create a binary search tree
    val root = TreeNode(7)
    root.left = TreeNode(3)
    root.right = TreeNode(15)
    root.right?.left = TreeNode(9)
    root.right?.right = TreeNode(20)

    // Create a BSTIterator and perform the operations
    val bstIterator = P6BSTIterator(root)

    println(bstIterator.next()) // Output: 3
    println(bstIterator.next()) // Output: 7
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 9
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 15
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 20
    println(bstIterator.hasNext()) // Output: false
}