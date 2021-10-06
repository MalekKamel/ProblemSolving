package challenges.cracking_coding_interview.arrays_strings

import java.util.*


/*
Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
A palindrome is a word or phrase that is the same forwards and backwards.
A permutation is a rearrangement of letters.
The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat", "atco eta", etc.)
 */

/*
Given a string, determine if a permutation of the string could form a palindrome.
For example,
"code" -> False, "aab" -> True, "carerac" -> True.
*/

/**
 * If a string with an even length is a palindrome, every character in the string must always occur an even number of times.
 * If the string with an odd length is a palindrome, every character except one of the characters must always occur an even number of times.
 * Thus, in case of a palindrome, the number of characters with odd number of occurrences can't exceed 1
 * (1 in case of odd length and 0 in case of even length).
 */

/**
 * Approach 1: HashMap
 * Algorithm:
 * From the discussion above, we know that to solve the given problem,
 * we need to count the number of characters with odd number of occurrences in the given string s.
 * To do so, we can also make use of a hashmap. This map takes the form (character_i, number of occurrences of character_i).
 *
 * We traverse over the given string s.
 * For every new character found in s, we create a new entry in the map for this character with the number of occurrences as 1.
 * Whenever we find the same character again, we update the number of occurrences appropriately.
 *
 * At the end, we traverse over the map created and find the number of characters with odd number of occurrences.
 * If this count happens to exceed 1 at any step, we conclude that a palindromic permutation isn't possible for the string s.
 * But, if we can reach the end of the string with count lesser than 2, we conclude that a palindromic permutation is possible for s.
 *
 * Complexity Analysis
 * Time complexity : O(n).
 * We traverse over the given string s with n characters once.
 * We also traverse over the map which can grow up to a size of n in case all characters in s are distinct.
 * Space complexity : O(n). The hashmap can grow up to a size of n, in case all the characters in s are distinct.
 */
private object Solution1 {

    fun solve(s: String): Boolean {
        val map = hashMapOf<Char, Int>()
        for (i in s.indices) {
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
        }
        var count = 0
        for (key in map.keys) {
            count += map[key]!! % 2
        }
        return count <= 1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve("eye"))       // true
        println(solve("carerac"))   // true
        println(solve("taco cat"))  // false
        println(solve("atco eta"))  // false
        println(solve("atco cta"))  // false
        println(solve("taco cate")) // false
        println(solve("code"))      // false
    }
}

/**
 * Approach 2: HashSet
 * Algorithm
 * A modification of the last approach could be by making use of a set for keeping track of
 * the number of elements with odd number of occurrences in s.
 * For doing this, we traverse over the characters of the string s.
 * Whenever the number of occurrences of a character becomes odd, we put its entry in the set.
 * Later on, if we find the same element again, lead to its number of occurrences as even,
 * we remove its entry from the set.
 * Thus, if the element occurs again(indicating an odd number of occurrences), its entry won't exist in the set.
 *
 * Based on this idea, when we find a character in the string s that isn't present in the set
 * (indicating an odd number of occurrences currently for this character), we put its corresponding entry in the set.
 * If we find a character that is already present in the set
 * (indicating an even number of occurrences currently for this character), we remove its corresponding entry from the set.
 *
 * At the end, the size of set indicates the number of elements with odd number of occurrences in s.
 * If it is lesser than 2, a palindromic permutation of the string s is possible, otherwise not.
 *
 * Complexity Analysis
 * Time complexity : O(n). We traverse over the string s of length n once only.
 * Space complexity : O(n). The set can grow upto a maximum size of n in case of all distinct elements.
 */
private object Solution2 {
    fun solve(s: String): Boolean {
        val set: MutableSet<Char> = HashSet()
        for (i in s.indices) {
            if (!set.add(s[i])) set.remove(s[i])
        }
        return set.size <= 1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve("eye"))       // true
        println(solve("carerac"))   // true
        println(solve("taco cat"))  // false
        println(solve("atco eta"))  // false
        println(solve("atco cta"))  // false
        println(solve("taco cate")) // false
        println(solve("code"))      // false
    }
}

/**
 * Approach 3: Using Array instead of Set
 * Algorithm
 * We traverse over s and update the number of occurrences of the character just encountered in the map (an array).
 * But, whenever we update any entry in map, we also check if its value becomes even or odd.
 * We start of with a count value of 0.
 * If the value of the entry just updated in map happens to be odd,
 * we increment the value of count to indicate that one more character with odd number of occurrences has been found.
 * But, if this entry happens to be even,
 * we decrement the value of count to indicate that the number of characters with odd number of occurrences has reduced by one.
 *
 * But, in this case, we need to traverse till the end of the string to determine the final result,
 * because, even if the number of elements with odd number of occurrences may seem very large at the current moment,
 * but their occurrences could turn out to be even when we traverse further in the string s.
 * At the end, we again check if the value of count is lesser than 2 to conclude that a palindromic permutation is possible for the string s.
 *
 * Complexity Analysis
 * Time complexity : O(n). We traverse over the string s of length n once only.
 * Space complexity : O(1). An array of constant size(128) is used.
 */
private object Solution3 {
    fun solve(s: String): Boolean {
        val map = IntArray(128)
        var count = 0
        for (i in s.indices) {
            map[s[i].toInt()]++
            if (map[s[i].toInt()] % 2 == 0) {
                count--
                continue
            }
            count++
        }
        return count <= 1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve("eye"))       // true
        println(solve("carerac"))   // true
        println(solve("taco cat"))  // false
        println(solve("atco eta"))  // false
        println(solve("atco cta"))  // false
        println(solve("taco cate")) // false
        println(solve("code"))      // false
    }
}