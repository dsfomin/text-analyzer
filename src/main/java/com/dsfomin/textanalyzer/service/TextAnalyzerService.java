package com.dsfomin.textanalyzer.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The program for calculating how many times letter in given sentence appears.
 * It gives numbers either for vowels or for consonants based on program input.
 * <p>
 * The first parameter can be 'vowels' or 'consonants'
 * The second parameter is the sentence to be analyzed.
 * <p>
 * Task: Refactor this code to be production ready and create appropriate unit tests.
 */

@Service
public class TextAnalyzerService {
    private static final String NON_VOWELS_REGEX = "(?iu:[^aeiou])";
    private static final String NON_CONSONANTS_REGEX = "(?iu:[^qwrtplkjhgfdszxcvbnm])";

    public Map<Character, Long> findMapOfVowelsOrConsonants(String mode, String input) {
        return Stream.of(input).map(s -> {
                    if (mode.equals("vowels")) {
                        return s.replaceAll(NON_VOWELS_REGEX, "");
                    } else if (mode.equals("consonants")) {
                        return s.replaceAll(NON_CONSONANTS_REGEX, "");
                    }
                    return "";
                })
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(TextAnalyzerService::toUpperCase, Collectors.counting()));
    }

    static Character toUpperCase(Character c) {
        return Character.toUpperCase(c);
    }
}
