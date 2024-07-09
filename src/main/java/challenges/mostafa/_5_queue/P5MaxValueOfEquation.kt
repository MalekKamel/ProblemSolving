package challenges.mostafa._5_queue

/**
You are given an array points containing the coordinates of points on a 2D plane, sorted by
the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length.
You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k
and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy
the constraint |xi - xj| <= k.

Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate
the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition
and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values,
and give the value of 0 + 0 + |0 - 3| = 3.

Constraints:

2 <= points.length <= 10^5
points[i].length == 2
-108 <= xi, yi <= 10^8
0 <= k <= 2 * 10^8
xi < xj for all 1 <= i < j <= points.length
xi form a strictly increasing sequence.

https://leetcode.com/problems/max-value-of-equation/description/
 */

internal object P5MaxValueOfEquation {

    /**
    The equation `y - deque.first().first + x + deque.first().second` is used to calculate
    the value of the expression `y - x + k` for the current point `(x, y)` and the first point
    in the `deque`.

    Here's a breakdown of the equation:

    1. `deque.first().first` represents the x-value of the first point in the `deque`.
    2. `deque.first().second` represents the y-value of the first point in the `deque`.
    3. `y - deque.first().first` calculates the difference between the y-value of the current point
    and the x-value of the first point in the `deque`. This part of the equation corresponds to
    the `y - x` term in the original equation `y - x + k`.
    4. `x + deque.first().second` calculates the sum of the x-value of the current point and the
    y-value of the first point in the `deque`. This part of the equation corresponds to the `k` term
    in the original equation `y - x + k`.

    By combining these two parts, the equation `y - deque.first().first + x + deque.first().second`
    calculates the value of the expression `y - x + k` for the current point and the first point
    in the `deque`.

    The reason this equation is used is that the function is trying to find the maximum value
    of the expression `y - x + k` over all pairs of points in the `points` array, where the absolute
    difference between the x-values of the two points is less than or equal to `k`. By maintaining
    the `deque` and updating the `maxValue` variable based on this equation, the function can
    efficiently find the maximum value that satisfies the given constraint.

    The equation `y - x + k` is used to find the optimal solution when you have two conflicting
    objectives that need to be balanced.

    The key elements of the equation are:

    1. `y` - This represents the "positive" objective, such as maximizing something desirable
    (e.g., square footage, product quality, shipping speed).

    2. `x` - This represents the "negative" objective, such as minimizing something undesirable
    (e.g., cost, distance, delivery time).

    3. `k` - This represents an additional constraint or requirement that must be satisfied.

    The logic behind the equation is as follows:

    1. Subtracting `x` from `y` creates a trade-off between the two objectives. This allows you
    to find the best balance between maximizing the positive objective and minimizing the negative
    objective.

    2. Adding `k` to the result incorporates the additional constraint or requirement into
    the equation. This ensures that the optimal solution not only balances the two main objectives,
    but also satisfies the supplementary constraint.

    By using this equation, you can find the solution that maximizes the positive objective (`y`)
    while minimizing the negative objective (`x`), and still meeting the additional constraint (`k`).

    The specific use cases can vary, but the underlying principle is the same - finding the optimal
    balance between competing objectives and constraints. This equation is particularly useful in
    scenarios where you need to make a decision that involves multiple, sometimes conflicting, factors.

    In these examples, the equation is used to:
    1. Find the house that maximizes square footage while minimizing cost and staying within
    a distance constraint.
    2. Find the delivery option that minimizes shipping cost while staying within a delivery
    time constraint.

    The `y - x + k` equation provides a structured way to analyze and optimize these types of
    multi-faceted decision-making problems.

    Here's another example of the usage of the equation `y - x + k`:

    Imagine you are a real estate agent helping a client find the perfect new home. Your client
    has a specific budget, and they want to find the house that will give them the most square
    footage for their money, but they are only willing to look at houses that are within
    a certain distance from their current location.

    In this scenario, the `y` value represents the square footage of the house, the `x` value
    represents the price of the house, and `k` represents the maximum allowed distance (in miles)
    from the client's current location.

    For example, let's say you have the following properties available:

    ```
    House 1: (x=$300,000, y=2,000 sq ft, distance=5 miles)
    House 2: (x=$350,000, y=2,500 sq ft, distance=2 miles)
    House 3: (x=$400,000, y=3,000 sq ft, distance=7 miles)
    House 4: (x=$450,000, y=3,500 sq ft, distance=3 miles)
    House 5: (x=$500,000, y=4,000 sq ft, distance=6 miles)
    ```

    Your client has a budget of $400,000 and is only willing to look at houses within a 4-mile
    radius of their current location (i.e., `k=4`).

    In this case, you would calculate the expression `y - x + k` for each house that satisfies
    the `|xi - xj| <= k` constraint, and find the maximum value:

    - House 1 (x=$300,000, y=2,000 sq ft, distance=5 miles): `2,000 - 300,000 + 4 = -298,000`
        (excluded because distance > 4 miles)
    - House 2 (x=$350,000, y=2,500 sq ft, distance=2 miles): `2,500 - 350,000 + 4 = -347,500`
    - House 3 (x=$400,000, y=3,000 sq ft, distance=7 miles): `3,000 - 400,000 + 4 = -397,000`
        (excluded because distance > 4 miles)
    - House 4 (x=$450,000, y=3,500 sq ft, distance=3 miles): `3,500 - 450,000 + 4 = -446,500`
    - House 5 (x=$500,000, y=4,000 sq ft, distance=6 miles): `4,000 - 500,000 + 4 = -496,000`
        (excluded because distance > 4 miles)

    The maximum value of the expression `y - x + k` is -347,500, which corresponds to
    House 2 (x=$350,000, y=2,500 sq ft, distance=2 miles). This means that the house that will
    give your client the most square footage for their $400,000 budget, while being within a 4-mile
    radius, is House 2.

    This example shows how the `y - x + k` equation can be used to find the optimal solution in
    a real estate scenario, where the goal is to maximize the square footage of a property while
    considering the client's budget and location constraints.
     */
    private fun findMaxValueOfEquation(points: Array<IntArray>, k: Int): Int {
        val deque = ArrayDeque<Pair<Int, Int>>()
        var maxValue = Int.MIN_VALUE

        for ((x, y) in points) {
            // Remove the points from the front of the deque whose x-value difference with
            // the current point is greater than k. This ensures that we only consider
            // points within the constraint |xi - xj| <= k.
            while (deque.isNotEmpty() && x - deque.first().first > k) {
                deque.removeFirst()
            }

            // Update the maximum value if the deque is not empty
            if (deque.isNotEmpty())
                maxValue = maxOf(maxValue, y - deque.first().first + x + deque.first().second)

            // Remove points from the deque whose y-value minus x-value is less than the current point's y-value minus x-value
            while (deque.isNotEmpty() && deque.last().second - deque.last().first < y - x) {
                deque.removeLast()
            }

            deque.addLast(x to y)
        }

        return maxValue
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val points = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 0),
            intArrayOf(5, 10),
            intArrayOf(6, -10)
        )
        val k = 1
        val maxValue = findMaxValueOfEquation(points, k)
        println("Maximum value of the equation: $maxValue")

        val points2 = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(3, 0),
            intArrayOf(9, 2)
        )
        val k2 = 3
        val maxValue2 = findMaxValueOfEquation(points2, k2)
        println("Maximum value of the equation: $maxValue2")
    }
}