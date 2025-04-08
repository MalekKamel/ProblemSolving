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

The number of nodes in the tree is in the range [1, 10^5].
0 <= Node.val <= 10^6
At most 105 calls will be made to hasNext, and next.

Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory,
where h is the height of the tree?

https://leetcode.com/problems/binary-search-tree-iterator/description/
 */

/**
 * 1. Problem Explanation
 * The code implements an iterator for a Binary Search Tree (BST). This iterator allows you to
 * traverse the nodes of the BST in an in-order sequence (smallest to largest value) without
 * explicitly performing a full in-order traversal and storing all the values beforehand. Instead,
 * it provides next() and hasNext() methods to retrieve the next smallest element and check if
 * there are more elements, respectively, on demand.
 *
 * 2. Pattern Identification and Rationale
 * The core pattern employed here is a variation of iterative in-order traversal using a stack.
 *
 * Rationale:
 *
 * In-order Traversal for Sorted Order: By definition, an in-order traversal of a BST visits nodes
 * in ascending order of their values. This is precisely what an iterator for a BST should provide.
 * Iterative Approach for Efficiency and On-Demand Access: An iterative approach using a stack avoids
 * the overhead of recursive function calls and allows for pausing and resuming the traversal.
 * This is crucial for an iterator, as you only want to compute the next element when it's requested
 * via the next() method. Storing all elements in advance would be inefficient, especially for large BSTs.
 * Stack for Tracking the Path: The stack is used to keep track of the nodes that still need to be visited.
 * Specifically, it maintains a path of left children from the current node. The top of the stack
 * always points to the next smallest unvisited node.
 * 3. Solution Breakdown
 * Let's break down the functionality of the P6BSTIterator class:
 *
 * private val stack = mutableListOf<TreeNode>():
 *
 * Logic: This line declares a mutable list that will be used as a stack to store TreeNode objects.
 * Contribution: This stack is the central data structure for maintaining the state of the iterator
 * and enabling the in-order traversal.
 * init { pushLeftBranch(root) }:
 *
 * Logic: The init block is executed when an instance of P6BSTIterator is created. It calls
 * the pushLeftBranch() method starting from the root of the BST.
 * Contribution: This initializes the stack with the leftmost path from the root. The top of
 * the stack will initially hold the smallest element in the BST (or the root if it has no left children).
 * fun next(): Int { ... }:
 *
 * Logic: This method retrieves the next smallest element from the BST.
 * val current = stack.removeLast(): It pops the top element from the stack. This element is the next
 * smallest unvisited node.
 * pushLeftBranch(current.right): After processing the current node, we need to explore its right subtree.
 * The pushLeftBranch() method is called on the right child to add all the leftmost descendants of the right
 * subtree onto the stack. This ensures that the next call to next() will retrieve the smallest element
 * in the right subtree (if it exists).
 * return current.value``: The value of the popped node (the next smallest element) is returned.
 * Contribution: This method implements the core logic of the iterator, providing access to the elements
 * in ascending order.
 * fun hasNext(): Boolean { ... }:
 *
 * Logic: This method checks if there are any more elements to visit in the BST.
 * Contribution: It returns true if the stack is not empty (meaning there are still nodes to process),
 * and false otherwise. This allows the user to control the iteration.
 * private fun pushLeftBranch(node: TreeNode?) { ... }:
 *
 * Logic: This helper method takes a TreeNode as input and pushes it and all of its left descendants
 * onto the stack.
 * var current = node: It starts with the given node.
 * while (current != null): It continues as long as the current node is not null.
 * stack.add(current): The current node is added to the stack.
 * current = current.left: The current pointer is moved to its left child.
 * Contribution: This method efficiently prepares the stack so that the top element is always
 * the next smallest unvisited node. By pushing all left children, we ensure that the nodes are
 * visited in the correct in-order sequence.
 * 4. Time Complexity
 * Let n be the number of nodes in the Binary Search Tree.
 *
 * hasNext(): This operation simply checks if the stack is empty, which takes O(1) time.
 *
 * next():
 *
 * Popping from the stack takes O(1) time.
 * The pushLeftBranch() operation, in the worst case, might traverse a long chain of left nodes.
 * However, each node in the BST is pushed onto the stack and popped off the stack exactly once
 * during the entire iteration process. Therefore, the total time spent in pushLeftBranch() across
 * all calls to next() is proportional to the number of nodes in the BST, n.
 * Thus, while a single call to next() might take O(h) time in the worst case (where h is the height
 * of the tree, if the right subtree is skewed to the left), the amortized time complexity of next()
 * over all n calls is O(1). This is because each node is visited and processed exactly once.
 * Initialization (init): The pushLeftBranch() called in the init block, in the worst case, might
 * traverse the entire left spine of the BST, which can be O(h) in the worst case (skewed tree) or O(logn)
 * in the best case (balanced tree). However, similar to the next() operation, each node is pushed onto
 * the stack at most once during the initialization. So, the initialization has a time complexity of O(h)
 * in the worst case, where h is the height of the BST.
 *
 * Overall Amortized Time Complexity:
 *
 * Over the entire process of iterating through all n nodes of the BST using next() and hasNext(), each
 * node is visited and processed a constant number of times (pushed onto and popped from the stack).
 * Therefore, the overall amortized time complexity for iterating through all elements is O(n). Each
 * individual next() call has an amortized time complexity of O(1).
 *
 * Space Complexity:
 *
 * The space complexity is determined by the maximum number of nodes stored in the stack. In the worst
 * case (a skewed tree where all nodes are in the left subtree), the stack might contain all n nodes.
 * In the best case (a balanced tree), the height is O(logn), and the stack will hold at most O(logn) nodes.
 * Therefore, the space complexity is O(h), where h is the height of the BST. In the worst case, this becomes O(n),
 * and in the best case, it is O(log n).
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
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 7
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 9
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 15
    println(bstIterator.hasNext()) // Output: true
    println(bstIterator.next()) // Output: 20
    println(bstIterator.hasNext()) // Output: false
}