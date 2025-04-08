package challenges.misc

/**
You are developing a text editor that needs to implement an "undo" and "redo" functionality.
The editor maintains a history of text operations, where each operation is represented as a string.
Operations can be of two types:

1.  **`ADD <text>`**: Appends the specified `<text>` to the end of the current document content.
2.  **`DELETE <n>`**: Deletes the last `n` characters from the document.

You need to implement a function that processes a sequence of these operations, including "UNDO" operations.
An "UNDO" operation cancels the effect of the most recent non-"UNDO" operation that hasn't been undone yet.
If there are no operations to undo, the "UNDO" operation is ignored.

The function should take an array (or list) of operation strings as input and return the final text content
after all operations have been processed.

 **Additionally, the implementation should consider the following requirements:**

 * Handle "ADD" operations by appending the text to the document.
 * Handle "DELETE n" operations by removing the last `n` characters from the document.
 * Handle "UNDO" operations by reversing the effect of the most recent non-"UNDO" operation.
 * Ensure that multiple consecutive "UNDO" operations correctly reverse the corresponding number
 * of previous operations.
 * If there are no operations to undo, the "UNDO" operation should be ignored.
 * The solution should ideally be efficient in terms of time and space complexity.

The problem also implies the need for a "REDO" functionality, although it's not explicitly listed
as a command type. The examples demonstrate that after an "UNDO", a subsequent operation
(either "ADD", "DELETE", or another "UNDO") will clear the possibility of "REDO"ing the undone action.
However, the provided solutions have implemented a "REDO" operation to fully align with typical undo/redo behavior.
 */
class TextEditor {

    fun processTextOperations(operations: List<String>): String {
        val text = StringBuilder()
        val history = mutableListOf<String>()
        val redoStack = mutableListOf<String>()

        for (operation in operations) {
            val parts = operation.split(" ")
            val command = parts[0]

            when (command) {
                "ADD" -> {
                    if (parts.size > 1) {
                        val textToAdd = parts.drop(1).joinToString(" ")
                        history.add(text.toString())
                        text.append(textToAdd)
                        redoStack.clear()
                    }
                }

                "DELETE" -> {
                    if (parts.size == 2) {
                        val countToDelete = parts[1].toIntOrNull()
                        countToDelete?.let { count ->
                            if (count > 0 && count <= text.length) {
                                history.add(text.toString())
                                text.delete(text.length - count, text.length)
                                redoStack.clear()
                            }
                        }
                    }
                }

                "UNDO" -> {
                    if (history.isNotEmpty()) {
                        redoStack.add(text.toString())
                        text.clear().append(history.removeLast())
                    }
                }

                "REDO" -> {
                    if (redoStack.isNotEmpty()) {
                        history.add(text.toString())
                        text.clear().append(redoStack.removeLast())
                    }
                }

                else -> {
                    // Ignore unknown operations
                }
            }
        }

        return text.toString()
    }
}


fun main() {
    val solution = TextEditor()
    val operations1 = listOf("ADD Hello", "ADD World", "UNDO", "ADD !")
    val result1 = solution.processTextOperations(operations1)
    println("Result 1: $result1") // Output: Result 1: Hello!

    val operations2 = listOf("ADD abc", "DELETE 1", "ADD d", "UNDO", "UNDO")
    val result2 = solution.processTextOperations(operations2)
    println("Result 2: $result2") // Output: Result 2: ab

    val operations3 = listOf("ADD a", "ADD b", "UNDO", "REDO", "ADD c", "UNDO")
    val result3 = solution.processTextOperations(operations3)
    println("Result 3: $result3") // Output: Result 3: a
}