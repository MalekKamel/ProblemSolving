package challenges.mostafa._4_stack

import java.util.Stack

/**
On a single-threaded CPU, we execute a program containing n functions. Each function has
a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto
the stack, and when a function call ends, its ID is popped off the stack. The function whose ID
is at the top of the stack is the current function being executed. Each time a function starts or
ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string
"{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call
with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call
with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple
times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program.
For example, if a function is called twice, one call executing for 2 time units and another call
executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index
represents the exclusive time for the function with ID i.

Example 1:
Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the
end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of
time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total
time executing.
Example 2:

Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
Output: [8]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls
itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit
of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.

Example 3:

Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
Output: [7,1]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls
itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit
of total time executing.


Constraints:

1 <= n <= 100
1 <= logs.length <= 500
0 <= function_id < n
0 <= timestamp <= 109
No two start events will happen at the same timestamp.
No two end events will happen at the same timestamp.
Each function has an "end" log for each "start" log.

https://leetcode.com/problems/exclusive-time-of-functions/description/
 */

internal object P10ExclusiveTimeOfFunctions {

    /**
     * 1. Problem Explanation
     * The problem asks us to calculate the exclusive execution time for each of n functions running
     * on a single-threaded CPU.
     * We are given a log of function calls, where each log entry indicates the function ID, whether
     * it started or ended, and the timestamp.
     * The exclusive time of a function is the total time the CPU spends executing that function,
     * excluding any time spent in functions it calls.
     * We need to return an array of size n, where the i-th element is the exclusive time of
     * the function with ID i.
     */

    /**
     * 2. Pattern Identification and Rationale
     * The problem involves tracking the execution of functions based on start and end events and
     * maintaining a call stack.
     * This suggests using a stack data structure to keep track of the currently executing function(s).
     * As we process the logs chronologically, when a function starts, we push its ID onto the stack.
     * When a function ends, we pop its ID from the stack.
     * To calculate the exclusive time, we need to know the duration of each function's execution.
     * This duration is the difference between the end timestamp and the start timestamp (inclusive
     * of the start time but exclusive of the end time in the log format, so we'll need to adjust).
     * While a function is executing, if another function is called (a new 'start' log appears),
     * the execution of the current function is paused, and the new function starts executing.
     * The time spent in the newly called function should not be counted towards the exclusive
     * time of the calling function.
     * Therefore, we need to keep track of the start time of the currently running function and,
     * when a new function starts or an existing function ends, calculate the execution time of
     * the function that was running.
     * The stack will help us identify the currently running function. When a function starts, we record
     * its start time. When a function ends, we calculate the duration it ran by comparing its end time
     * with its start time.
     * We also need to handle the case where a function call is interrupted by another function call.
     * In this scenario, when the inner function ends, the outer function resumes. We need to account
     * for the time the outer function was paused.
     * A suitable approach is to iterate through the logs, maintain a stack of currently executing function IDs,
     * and keep track of the start time of the function at the top of the stack. When a function ends,
     * we calculate its execution time and add it to the total exclusive time for that function.
     * We also need to account for the time the calling function was paused.
     */

    /**
     * 3. Solution Breakdown
     * Step 1: Initialize an array `result` of size `n` to store the exclusive time for each function,
     * initialized to 0.
     * Step 2: Initialize a stack `callStack` to store the IDs of the currently executing functions.
     * Step 3: Initialize a variable `previousTimestamp` to keep track of the timestamp of the previous
     * log entry. This will help in calculating the execution time. Initialize it to 0 (or -1 if the first log is a 'start').
     * Step 4: Iterate through the `logs` list. For each log entry:
     * a. Parse the log entry to extract the `functionId`, `type` ("start" or "end"), and `timestamp`.
     * Convert the timestamp to an integer.
     * b. If the log type is "start":
     * i. If the `callStack` is not empty, it means a function was running before this one started.
     * The time elapsed between the previous log's timestamp (plus one, as the current start happens
     * at the beginning of the current timestamp) and the current timestamp is the execution time
     * of the function at the top of the stack. Add this duration to the `result` for that function ID.
     * ii. Push the current `functionId` onto the `callStack`.
     * iii. Update `previousTimestamp` to the current `timestamp`.
     * c. If the log type is "end":
     * i. Pop the top `functionId` from the `callStack`. This is the function that just ended.
     * ii. The execution time of this function is the difference between the current `timestamp` and
     * the `previousTimestamp` (inclusive, so `timestamp - previousTimestamp + 1`). Add this duration
     * to the `result` for this `functionId`.
     * iii. Update `previousTimestamp` to the current `timestamp`.
     * Step 5: After processing all the logs, the `result` array will contain the exclusive time for
     * each function. Return this array.
     */

    /**
    The key aspects of this solution are:

    1. Using a stack to keep track of the currently executing functions.
    2. Updating the exclusive time of each function based on the difference between the current
        and previous timestamps.
    3. Adding 1 to the exclusive time when a function ends, to account for the full execution time.

    Adding 1 to the exclusive time when a function ends is necessary to account for the full
    execution time of the function.

    Here's a more detailed explanation:
    In the problem, the exclusive time of a function is defined as the total time a function
    is the main active function. This means that the exclusive time of a function should include
    the time from the start of the function to the end of the function, inclusive.
    However, the way the timestamps are given in the log entries, the "end" timestamp represents
    the last moment when the function was active. This means that if we simply took the difference
    between the "end" timestamp and the previous "start" timestamp, we would be missing the last
    unit of time when the function was active.
    For example, consider the following log entries:

    ```
    0:start:0
    0:end:1
    1:start:2
    1:end:3
    ```

    If we calculate the exclusive time for function 0 by taking the difference between the
    "end" timestamp (1) and the previous "start" timestamp (0), we would get a value of 1, which
    is not the full execution time of the function.

    To account for this, we add 1 to the exclusive time when a function ends. This ensures that
    the full execution time of the function is included in the exclusive time calculation.

    In the example above, the exclusive time for function 0 would be calculated as:

    ```
    exclusive time for function 0 = (1 - 0) + 1 = 2
    ```

    This correctly captures the full execution time of the function, from the start timestamp
    to the end timestamp, inclusive.
     */
    private fun exclusiveTime(n: Int, logs: List<String>): IntArray {
        val result = IntArray(n) // Initialize the result array with n elements
        val stack = Stack<Int>() // Use Java Stack
        var prevTimestamp = 0 // Timestamp of the previous log entry

        for (log in logs) {
            val parts = log.split(":") // Split the log entry into its parts
            val functionId = parts[0].toInt()
            val state = parts[1]
            val currentTimestamp = parts[2].toInt()

            when (state) {
                "start" -> {
                    if (stack.isNotEmpty())
                        result[stack.peek()] += currentTimestamp - prevTimestamp
                    stack.push(functionId)
                    prevTimestamp = currentTimestamp
                }
                "end" -> {
                    result[stack.pop()] += currentTimestamp - prevTimestamp + 1
                    prevTimestamp = currentTimestamp + 1
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n = 2
        val logsList = listOf(
            listOf(
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"
            ),
            listOf(
                "0:start:0",
                "0:start:2",
                "0:end:5",
                "0:start:6",
                "0:end:6",
                "0:end:7"
            ),
            listOf(
                "0:start:0",
                "0:start:2",
                "0:end:5",
                "1:start:6",
                "1:end:6",
                "0:end:7"
            )
        )

        for (logs in logsList) {
            val result = exclusiveTime(n, logs)
            println(result.contentToString()) // Output: [3, 4]
        }
    }
}