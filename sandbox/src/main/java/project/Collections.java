package project;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "php"};

        List<String> languages = Arrays.asList("Java", "C#", "Python", "1");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
