# Binary Tree Explained
In computer science, a binary tree is a hierarchical data structure where each node has at most
two children, referred to as the left child and the right child.

# Key Terms:

* Node: Each element in a tree.
* Root: The topmost node in a tree.
* Parent: A node that has children.
* Child: A node that is a descendant of another node.
* Leaf: A node with no children.
* Edge: The connection between two nodes.
* Subtree: A portion of a tree that is itself a tree.

# Types of Binary Trees:

* Full Binary Tree: Every node has either 0 or 2 children.
* Complete Binary Tree: All levels are filled except possibly the last, and all nodes are as far left as possible.
* Perfect Binary Tree: All levels are completely filled.
* Balanced Binary Tree: The height difference between the left and right subtrees of any node is at most 1.

# Common Operations:

* Insertion: Adding a new node to the tree.
* Deletion: Removing a node from the tree.
* Traversal: Visiting every node in the tree.
  - Preorder
  - Inorder
  - Postorder

* Search: Finding a specific node in the tree.

# Applications of Binary Trees:

* Storing hierarchical data: File systems, organizational structures.
* Efficient searching and sorting: Binary search trees.
* Implementing other data structures: Heaps, hash tables.
* Expression trees: Representing arithmetic expressions.
* Decision trees: Used in machine learning.

---

## Time Complexity of Binary Trees

The time complexity of operations on a binary tree depends significantly on whether the tree is
balanced or unbalanced.

**General Binary Tree (Unbalanced)**

In a general binary tree, the structure can degenerate into a linked list in the worst case.

* **Search:**
    * **Worst Case:** O(n)
    * **Average Case:** O(n)
    * **Best Case:** O(1)
* **Insertion:**
    * **Worst Case:** O(n)
    * **Average Case:** O(n)
    * **Best Case:** O(1)
* **Deletion:**
    * **Worst Case:** O(n)
    * **Average Case:** O(n)
    * **Best Case:** O(1)
* **Traversal (In-order, Pre-order, Post-order):** O(n)

**Balanced Binary Search Tree (e.g., AVL Tree, Red-Black Tree)**

Balanced binary search trees maintain a structure that limits the height of the tree to be
logarithmic with respect to the number of nodes.

* **Search:**
    * **Worst Case:** O(\log n)
    * **Average Case:** O(\log n)
    * **Best Case:** O(1)
* **Insertion:**
    * **Worst Case:** O(\log n)
    * **Average Case:** O(\log n)
    * **Best Case:** O(1)
* **Deletion:**
    * **Worst Case:** O(\log n)
    * **Average Case:** O(\log n)
    * **Best Case:** O(1)
* **Traversal (In-order, Pre-order, Post-order):** O(n)

**Summary Table:**

| Operation | Unbalanced Binary Tree | Balanced Binary Search Tree |
|:----------|:-----------------------|:----------------------------|
| Search    | O(n)                   | O(log n)                    |
| Insertion | O(n)                   | O(log n)                    |
| Deletion  | O(n)                   | O(log n)                    |
| Traversal | O(n)                   | O(n)                        |

---

# Resources

* Binary Trees - Data Structures Explained https://www.youtube.com/watch?v=GzJoqJO1zdI
* Learn Tree traversal in 3 minutes https://www.youtube.com/watch?v=b_NjndniOqY
* Learn Binary search trees in 20 minutes https://www.youtube.com/watch?v=Gt2yBZAhsGM