package challenges.coderbyte;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Your goal is to count how many items exist that have an age equal to or greater than 50, and print this final value.

public class AgeCounting {

    public static void main(String[] args) {
        System.setProperty("http.agent", "Chrome");
        try {
            URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
            try {
                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();
                System.out.println(inputStream);

                String response = parseResponse(inputStream);
                System.out.println("CONTENT: " + response);

                List<Person> persons = parsePersons(response);

                long count = persons.stream().filter(item -> item.age >= 50).count();

                System.out.println("COUNT: " + count);

            } catch (Exception ioEx) {
                System.out.println(ioEx);
            }
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
        }
    }

    private static String parseResponse(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }

        return content.toString();
    }

    private static List<Person> parsePersons(String str) {
        Matcher beforeMatcher = Pattern.compile("\\w+(?==)").matcher(str);
        Matcher afterMatcher = Pattern.compile("(?<==)(.*?)(?=((, \\w*)=)|\")").matcher(str);

        List<String> names = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();

        while (beforeMatcher.find() && afterMatcher.find()) {
            String key = beforeMatcher.group();
            String value = afterMatcher.group();

            if (key.equals("key")) names.add(value);
            if (key.equals("age")) ages.add(new Integer(value));
        }

        List<Person> result = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            Person person = new Person(names.get(i), ages.get(i));
            result.add(person);
        }

        return result;
    }

   static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}


