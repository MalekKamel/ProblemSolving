## Depth-First Search (DFS) and Breadth-First Search (BFS) in Binary Trees

Here, we'll explore how DFS and BFS work specifically with binary trees. The core concepts are the
same as with general graphs, but the implementation is tailored to the binary tree structure.

### Binary Tree Node in Kotlin

First, let's define a `TreeNode` class in Kotlin:

```kotlin
class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
```

This class represents a node in a binary tree, holding an integer value and references to its left
and right children.

### Depth-First Search (DFS) in Binary Tree

DFS can be implemented in a binary tree using recursion or a stack. The most common DFS traversals
for binary trees are:

* **Pre-order:** Visit the node, then the left subtree, then the right subtree.

* **In-order:** Visit the left subtree, then the node, then the right subtree.

* **Post-order:** Visit the left subtree, then the right subtree, then the node.

Here's a Kotlin example of pre-order DFS:

```kotlin
fun dfsPreOrder(node: TreeNode?) {
    if (node == null) {
        return
    }
    print("${node.value} ") // Visit the node
    dfsPreOrder(node.left) // Traverse the left subtree
    dfsPreOrder(node.right) // Traverse the right subtree
}
```

**Example Usage:**

```kotlin
fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    root.right?.left = TreeNode(6)
    root.right?.right = TreeNode(7)

    println("DFS Pre-Order Traversal:")
    dfsPreOrder(root) // Output: 1 2 4 5 3 6 7
}
```

### Breadth-First Search (BFS) in Binary Tree

BFS visits all nodes at the same level before moving to the next level. It uses a queue to keep
track of the nodes to visit. This is also known as level-order traversal.

Here's a Kotlin example of BFS:

```kotlin
import java.util.LinkedList
import java.util.Queue

fun bfs(root: TreeNode?) {
    if (root == null) {
        return
    }

    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root) // Enqueue the root

    println("BFS Traversal:")
    while (queue.isNotEmpty()) {
        val node = queue.poll() // Dequeue a node
        print("${node.value} ") // Visit the node

        if (node.left != null) {
            queue.offer(node.left) // Enqueue left child
        }
        if (node.right != null) {
            queue.offer(node.right) // Enqueue right child
        }
    }
}
```

**Example Usage:**

```kotlin
fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    root.right?.left = TreeNode(6)
    root.right?.right = TreeNode(7)

    bfs(root) // Output: 1 2 3 4 5 6 7
}
```

### Summary

* **DFS** explores a branch as far as possible before backtracking. For binary trees, this can be
  done pre-order, in-order, or post-order.

* **BFS** explores the tree level by level.

Both DFS and BFS are fundamental algorithms for traversing binary trees and are used in various
applications.
