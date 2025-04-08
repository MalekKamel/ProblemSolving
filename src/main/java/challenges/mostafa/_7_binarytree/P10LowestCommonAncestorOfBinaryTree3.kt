package challenges.mostafa._7_binarytree

import challenges.mostafa._7_binarytree.P10LowestCommonAncestorOfBinaryTree3.Node

/**
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:

class Node {
public int val;
public Node left;
public Node right;
public Node parent;
}

According to the definition of LCA on Wikipedia: “The lowest common ancestor of two
nodes p and q in a tree T is the lowest node that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q exist in the tree.

https://leetcode.ca/2020-06-06-1650-Lowest-Common-Ancestor-of-a-Binary-Tree-III/
 */

internal class P10LowestCommonAncestorOfBinaryTree3 {

    class Node(
        var `val`: Int,
        var left: Node? = null,
        var right: Node? = null,
        var parent: Node? = null
    ) {
        override fun toString(): String {
            return `val`.toString()
        }
    }

    /**
     * Here's a breakdown of the provided solution for finding the Lowest Common Ancestor (LCA)
     * of two nodes in a binary tree where each node has a reference to its parent:
     *
     * ## 1. Problem Explanation
     *
     * The problem asks us to find the lowest common ancestor of two given nodes, `p` and `q`, in
     * a binary tree. The key characteristic of this problem is that each node in the tree has
     * a pointer to its parent. The lowest common ancestor is defined as the deepest node in the tree
     * that has both `p` and `q` as descendants (including the possibility that a node can be a descendant
     * of itself).
     *
     * ## 2. Pattern Identification and Rationale
     *
     * The relevant pattern here is **iterative traversal using parent pointers and set membership checking**.
     *
     * **Rationale:**
     *
     * * **Availability of Parent Pointers:** The crucial piece of information is that each node
     * has a `parent` pointer. This allows us to move upwards from any given node towards the root of the tree.
     * * **Finding Common Ancestors:** If we traverse upwards from both `p` and `q`, any node we
     * encounter that has been visited in both upward paths is a common ancestor.
     * * **Lowest Common Ancestor:** To find the *lowest* common ancestor, we can collect all
     * ancestors of one node (say, `p`) and then traverse upwards from the other node (`q`). The first
     * ancestor of `q` that we find in the set of ancestors of `p` will be the lowest common ancestor.
     * This is because we are moving upwards from `q`, so the first common ancestor we encounter will
     * be the deepest one.
     * * **Efficiency:** Using a set to store the ancestors of one node allows for efficient
     * checking (in near constant time on average) of whether an ancestor of the other node has been seen before.
     *
     * ## 3. Solution Breakdown
     *
     * The provided Kotlin solution implements this approach in the `lowestCommonAncestor` function:
     *
     * 1.  **Initialize a Set for Ancestors of `p`:**
     *     ```kotlin
     *     val ancestorsP = mutableSetOf<Node>()
     *     ```
     *     A mutable set `ancestorsP` is created to store all the ancestors of node `p`. Using a set
     *     allows for efficient checking of whether a node has been encountered before.
     *
     * 2.  **Traverse Upwards from `p` and Store Ancestors:**
     *     ```kotlin
     *     var currentP = p
     *     while (currentP != null) {
     *         ancestorsP.add(currentP)
     *         currentP = currentP.parent
     *     }
     *     ```
     *     Starting from node `p`, the code iterates upwards using the `parent` pointer. In each step,
     *     the current ancestor is added to the `ancestorsP` set. This continues until the root of
     *     the tree (where `parent` becomes `null`) is reached.
     *
     * 3.  **Traverse Upwards from `q` and Check for Common Ancestor:**
     *     ```kotlin
     *     var currentQ = q
     *     while (currentQ != null) {
     *         if (ancestorsP.contains(currentQ)) {
     *             return currentQ
     *         }
     *         currentQ = currentQ.parent
     *     }
     *     ```
     *     Next, the code starts from node `q` and iterates upwards using its `parent` pointer.
     *     For each ancestor of `q`, it checks if that ancestor is present in the `ancestorsP` set.
     *
     * 4.  **Return the First Common Ancestor Found:**
     *     ```kotlin
     *     if (ancestorsP.contains(currentQ)) {
     *         return currentQ
     *     }
     *     ```
     *     The first node encountered while traversing upwards from `q` that is also present in
     *     the `ancestorsP` set is the lowest common ancestor. This node is immediately returned.
     *
     * 5.  **Handle the Case Where No Common Ancestor is Found (Though Should Not Occur):**
     *     ```kotlin
     *     return null // Should not happen based on the problem constraints (p and q exist)
     *     ```
     *     The problem statement guarantees that both `p` and `q` exist in the tree. Therefore,
     *     they will always have a common ancestor (at least the root). This `return null` is included
     *     for completeness but should ideally not be reached under the given constraints.
     *
     * ## 4. Time Complexity
     *
     * The time complexity of this solution is **O(H)**, where H is the height of the deeper path
     * from either `p` or `q` to the root of the tree.
     *
     * * The first `while` loop iterates up from `p` to the root, taking at most O(H) steps in the worst
     * case (e.g., a skewed tree).
     * * The second `while` loop iterates up from `q` to the root, also taking at most O(H) steps in
     * the worst case.
     * * The `contains` operation on the `HashSet` takes, on average, O(1) time.
     *
     * Therefore, the overall time complexity is dominated by the traversals up to the root, resulting
     * in O(H). In the worst case (a skewed tree), H can be equal to N (the number of nodes), so the time
     * complexity can be O(N). In a balanced binary tree, H would be O(log N), resulting in a time
     * complexity of O(log N).
     *
     * The space complexity is **O(H)** in the worst case, as the `ancestorsP` set can store up
     * to H nodes (all ancestors of `p` up to the root). In the worst-case skewed tree, this could be O(N),
     * and in a balanced tree, it would be O(log N).
     */
    fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
        val ancestorsP = mutableSetOf<Node>()
        var currentP = p
        while (currentP != null) {
            ancestorsP.add(currentP)
            currentP = currentP.parent
        }

        var currentQ = q
        while (currentQ != null) {
            if (ancestorsP.contains(currentQ)) {
                return currentQ
            }
            currentQ = currentQ.parent
        }

        return null // Should not happen based on the problem constraints (p and q exist)
    }

}

fun main(args: Array<String>) {
    // Construct the tree for Example 1
    val root1 = Node(3)
    val node5 = Node(5)
    val node1 = Node(1)
    val node6 = Node(6)
    val node2 = Node(2)
    val node0 = Node(0)
    val node8 = Node(8)
    val node7 = Node(7)
    val node4 = Node(4)

    root1.left = node5
    root1.right = node1
    node5.parent = root1
    node1.parent = root1

    node5.left = node6
    node5.right = node2
    node6.parent = node5
    node2.parent = node5

    node1.left = node0
    node1.right = node8
    node0.parent = node1
    node8.parent = node1

    node2.left = node7
    node2.right = node4
    node7.parent = node2
    node4.parent = node2

    val solution1 = P10LowestCommonAncestorOfBinaryTree3()
    val lca1 = solution1.lowestCommonAncestor(node5, node1)
    println("LCA of ${node5.`val`} and ${node1.`val`}: ${lca1?.`val`}") // Output: 3

    // Construct the tree for Example 2 (reusing nodes)
    val lca2 = solution1.lowestCommonAncestor(node5, node4)
    println("LCA of ${node5.`val`} and ${node4.`val`}: ${lca2?.`val`}") // Output: 5

    // Construct the tree for Example 3
    val root3 = Node(1)
    val node2_3 = Node(2)
    root3.left = node2_3
    node2_3.parent = root3
    val solution3 = P10LowestCommonAncestorOfBinaryTree3()
    val lca3 = solution3.lowestCommonAncestor(root3, node2_3)
    println("LCA of ${root3.`val`} and ${node2_3.`val`}: ${lca3?.`val`}") // Output: 1
}