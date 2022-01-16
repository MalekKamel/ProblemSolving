package challenges.coderbyte

import kotlin.jvm.JvmStatic
import java.lang.Exception
import java.net.MalformedURLException
import kotlin.Throws
import java.io.IOException
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.regex.Pattern

// Your goal is to count how many items exist that have an age equal to or greater than 50, and print this final value.
object AgeCounting {
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("http.agent", "Chrome")
        try {
            val url = URL("https://coderbyte.com/api/challenges/json/age-counting")
            try {
                val connection = url.openConnection()
                val inputStream = connection.getInputStream()
                println(inputStream)
                val response = parseResponse(inputStream)
                println("CONTENT: $response")
                val persons = parsePersons(response)
                val count = persons.stream().filter { item: Person -> item.age >= 50 }.count()
                println("COUNT: $count")
            } catch (ioEx: Exception) {
                println(ioEx)
            }
        } catch (malEx: MalformedURLException) {
            println(malEx)
        }
    }

    @Throws(IOException::class)
    private fun parseResponse(inputStream: InputStream): String {
        val content = StringBuilder()
        val br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
        var line: String?
        while (br.readLine().also { line = it } != null) {
            content.append(line).append("\n")
        }
        return content.toString()
    }

    private fun parsePersons(str: String): List<Person> {
        val beforeMatcher = Pattern.compile("\\w+(?==)").matcher(str)
        val afterMatcher = Pattern.compile("(?<==)(.*?)(?=((, \\w*)=)|\")").matcher(str)
        val names: MutableList<String> = ArrayList()
        val ages: MutableList<Int> = ArrayList()
        while (beforeMatcher.find() && afterMatcher.find()) {
            val key = beforeMatcher.group()
            val value = afterMatcher.group()
            if (key == "key") names.add(value)
            if (key == "age") ages.add(value.toInt())
        }
        val result: MutableList<Person> = ArrayList()
        for (i in names.indices) {
            val person = Person(names[i], ages[i])
            result.add(person)
        }
        return result
    }

    internal class Person(var name: String, var age: Int)
}