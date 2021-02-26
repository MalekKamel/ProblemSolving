package challenges.coderbyte

/*
Have the function BoggleSolver(strArr) read the array of strings stored in strArr,
which will contain 2 elements: the first element will represent a 4x4 matrix of
letters, and the second element will be a long string of comma-separated words
each at least 3 letters long, in alphabetical order, that represents a dictionary
of some arbitrary length. For example: strArr can be: ["rbfg, ukop, fgub, mnry",
"bog,bop,gup,fur,ruk"]. Your goal is to determine if all the comma separated words
as the second parameter exist in the 4x4 matrix of letters. For this example,
the matrix looks like the following:

r b f g
u k o p
f g u b
m n r y

The rules to make a word are as follows:

1. A word can be constructed from sequentially adjacent spots in the matrix,
where adjacent means moving horizontally, vertically, or diagonally in any direction.
2. A word cannot use the same location twice to construct itself.

The rules are similar to the game of Boggle. So for the example above,
all the words exist in that matrix so your program should return the string true.
If all the words cannot be found, then return a comma separated string of the words
that cannot be found, in the order they appear in the dictionary.

Use the Parameter Testing feature in the box below to test your code with different arguments.

Solution: https://www.geeksforgeeks.org/boggle-set-2-using-trie/
 */

/*
Solution Steps:
1- Put all words in a Trie.
2- Iterate over each char in boggle.
3- Get the char node in Trie if found.
4- Depth-First Search each trie node to see if we can get a word
 */
object BoggleSolver {
    private var dirX = intArrayOf(-1, -1, -1, 0, 1, 0, 1, 1)
    private var dirY = intArrayOf(-1, 1, 0, -1, -1, 1, 0, 1)

    private fun findWords(strArr: Array<String>): String {
        val boggle = strArr[0]
                .split(", ")
                .map { it.toCharArray() }
                .toTypedArray()
        val words = strArr[1]
                .split(", ")
                .toTypedArray()

        val trie = Trie()
        trie.insert(words)

        val result = findWords(boggle, trie)
        if (result.size == words.size) {
            return "true"
        }
        return words.filter { !result.contains(it) }
                .joinToString(",")
    }

    // Prints all words present in dictionary.
    private fun findWords(boggle: Array<CharArray>, trie: Trie): MutableSet<String> {
        // Mark all characters as not visited
        val visited = Array(boggle.size) { BooleanArray(boggle[0].size) }

        val result: MutableSet<String> = HashSet()
        // traverse all matrix elements
        for (i in boggle.indices) {
            for (j in boggle[0].indices) {
                // we start searching for word in dictionary
                // if we found a character which is child
                // of Trie root
                val ch = boggle[i][j]
                val index = indexOf(ch)
                val node = trie.root.child[index] ?: continue
                searchWord(node, boggle, i, j, visited, ch.toString(), result)
            }
        }
        return result
    }

    // A recursive function to print
    // all words present on boggle
    private fun searchWord(root: TrieNode,
                           boggle: Array<CharArray>,
                           i: Int,
                           j: Int,
                           visited: Array<BooleanArray>,
                           word: String,
                           result: MutableSet<String>) {
        if (root.isWord) result.add(word)

        // make it visited
        visited[i][j] = true

        for (k in 0..25) {
            val child = root.child[k] ?: continue
            val ch = toChar(k)
            // skip if a cell is invalid, or it is already visited
            for (n in 0..7) {
                val x = i + dirX[n]
                val y = j + dirY[n]
                if (!(isSafe(x, y, boggle, visited))) continue
                if (boggle[x][y] != ch) continue
                searchWord(child, boggle, x, y, visited, word + ch, result)
            }
        }
        // make current element unvisited
        visited[i][j] = false
        return
    }

    // function to check that current location
    // (i and j) is in matrix range
    private fun isSafe(i: Int,
                       j: Int,
                       board: Array<CharArray>,
                       visited: Array<BooleanArray>): Boolean {
        return i in board.indices && j in board[0].indices && !visited[i][j]
    }

    class Trie {
        var root: TrieNode = TrieNode()

        // If not present, inserts a key into the trie
        // If the key is a prefix of trie node, just
        // marks leaf node
        fun insert(words: Array<String>) {
            for (element in words) insert(element)
        }

        fun insert(key: String) {
            val n = key.length
            var pChild = root
            for (i in 0 until n) {
                val index = indexOf(key[i])
                if (pChild.child[index] == null) pChild.child[index] = TrieNode()
                pChild = pChild.child[index]!!
            }
            // make last node as leaf node
            pChild.isWord = true
        }
    }

    // trie Node
    class TrieNode {
        var child = arrayOfNulls<TrieNode>(26)

        // isWord is true if the node represents
        // end of a word
        var isWord = false
    }

    fun toChar(c: Int): Char {
        return (c + 'a'.toInt()).toChar()
    }

    fun indexOf(c: Char): Int {
        // subtract from a to convert unicode number to a number from 0 to 26.
        return c - 'a'
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
                "aaey, rrum, tgmn, ball",
                "all, ball, mur, raeymnl, tall, true, trum"
        )
        val input2 = arrayOf(
                "aaey, rrum, tgmn, ball",
                "all, ball, mur, raeymnl, rumk, tall, true, trum, yes"
        )
        println(findWords(input))
        println(findWords(input2))
    }
}
