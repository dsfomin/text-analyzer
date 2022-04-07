package com.dsfomin.textanalyzer.service;

import com.dsfomin.textanalyzer.exception.ModeNotFoundException;
import com.dsfomin.textanalyzer.model.TextAnalyze;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The service that implement method for calculating how many times letter in given sentence appears.
 * It gives numbers either for vowels or for consonants based on program input.
 * <p>
 * The first parameter can be 'vowels' or 'consonants'
 * The second parameter is the sentence to be analyzed.
 */

@Service
public class TextAnalyzerService {
    private static final String NON_VOWELS_REGEX = "(?iu:[^aeiou])";
    private static final String NON_CONSONANTS_REGEX = "(?iu:[^qwrtplkjhgfdszxcvbnm])";

    public Map<Character, Long> findMapOfVowelsOrConsonants(TextAnalyze textAnalyze) {
        return Stream.of(textAnalyze.getText()).map(s -> {
                    if (textAnalyze.getMode().equals("vowels")) {
                        return s.replaceAll(NON_VOWELS_REGEX, "");
                    } else if (textAnalyze.getMode().equals("consonants")) {
                        return s.replaceAll(NON_CONSONANTS_REGEX, "");
                    } else {
                        throw new ModeNotFoundException(textAnalyze.getMode());
                    }
                })
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(TextAnalyzerService::toUpperCase, Collectors.counting()));
    }

    public static Character toUpperCase(Character c) {
        return Character.toUpperCase(c);
    }
}
