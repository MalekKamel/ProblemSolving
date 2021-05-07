package challenges.coderbyte

/*
Have the function CommandLine(str) take the str parameter being passed
which represents the parameters given to a command in an old PDP system.
The parameters are alphanumeric tokens (without spaces) followed
by an equal sign and by their corresponding value.
Multiple parameters/value pairs can be placed on the command line
with a single space between each pair.
Parameter tokens and values cannot contain equal
signs but values can contain spaces.
The purpose of the function is to isolate the parameters and
values to return a list of parameter and value lengths.
It must provide its result in the same format and in the same order
by replacing each entry (tokens and values) by its corresponding length.

For example, if str is: "SampleNumber=3234 provider=Dr. M.
Welby patient=John Smith priority=High" then your function should return
the string "12=4 8=12 7=10 8=4" because "SampleNumber" is a 12 character
token with a 4 character value ("3234") followed by "provider" which is
an 8 character token followed by a 12 character value ("Dr. M. Welby"), etc.

 */
object CommandLine {

    private fun solve(str: String): String {
        /*
        match the strings before and after = and concatenate them
        */
        val beforeMatcher = "\\w+(?==)".toPattern().matcher(str)

        // (?<==)  must be preceded with =
        // (.*?) one or more character, must be few(?)
        // (?=(( \w*)=)|$) followed by another expression ( characters=) or the end of string (|$)
        val afterMatcher = "(?<==)(.*?)(?=(( \\w*)=)|$)".toPattern().matcher(str)

        var answer = ""
        while (beforeMatcher.find() && afterMatcher.find()) {
            answer += beforeMatcher.group().length.toString() +
                    "=" +
                    afterMatcher.group().length.toString() +
                    " "
        }
        return answer.trim()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print(solve("letters=A B Z T numbers=1 2 26 20 combine=true")) // 7=7 7=9 7=4
    }

}

