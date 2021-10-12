package challenges.util



/* Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix.
 */
class Trie {
    // The root of this trie.
    var root: TrieNode
        private set

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
    constructor(list: ArrayList<String?>) {
        root = TrieNode()
        for (word in list) {
            root.addWord(word)
        }
    }

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
    constructor(list: Array<String?>) {
        root = TrieNode()
        for (word in list) {
            root.addWord(word)
        }
    }

    /* Checks whether this trie contains a string with the prefix passed
     * in as argument.
     */
    @JvmOverloads
    fun contains(prefix: String, exact: Boolean = false): Boolean {
        var lastNode: TrieNode? = root
        var i = 0
        i = 0
        while (i < prefix.length) {
            lastNode = lastNode?.getChild(prefix.toCharArray()[i]);
            if (lastNode == null) return false
            i++
        }
        return !exact || lastNode?.terminates() == true
    }
}