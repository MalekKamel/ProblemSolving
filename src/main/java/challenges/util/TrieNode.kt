package challenges.util


/* One node in the trie. Most of the logic of the trie is implemented
 * in this class.
 */
class TrieNode() {
    /* The children of this node in the trie.*/
    private val children: HashMap<Char, TrieNode> = HashMap()
    private var terminates = false

    /* Returns the character data stored in this node. */
    // The character stored in this node as data.
    var char = 0.toChar()
        private set

    /* Constructs a trie node and stores in the node the char passed in
     * as the argument. Initializes the list of child nodes of this
     * node to an empty hash map.
     */
    constructor(character: Char) : this() {
        char = character
    }

    /* Add this word to the trie, and recursively create the child
     * nodes. */
    fun addWord(word: String?) {
        if (word == null || word.isEmpty()) {
            return
        }
        val firstChar = word[0]
        var child = getChild(firstChar)
        if (child == null) {
            child = TrieNode(firstChar)
            children[firstChar] = child
        }
        if (word.length > 1) {
            child.addWord(word.substring(1))
        } else {
            child.setTerminates(true)
        }
    }

    /* Find a child node of this node that has the char argument as its
     * data. Return null if no such child node is present in the trie.
     */
    fun getChild(c: Char): TrieNode? {
        return children[c]
    }

    /* Returns whether this node represents the end of a complete word. */
    fun terminates(): Boolean {
        return terminates
    }

    /* Set whether this node is the end of a complete word.*/
    fun setTerminates(t: Boolean) {
        terminates = t
    }

}