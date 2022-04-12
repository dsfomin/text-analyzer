package com.dsfomin.textanalyzer.service;

import com.dsfomin.textanalyzer.exception.ModeNotFoundException;
import com.dsfomin.textanalyzer.model.TextAnalyze;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The service that implement method for calculating how many times letter in given sentence appears.
 * It gives numbers either for vowels or for consonants based on parameter input.
 * <p>
 * The parameter is TextAnalyze object that contains two field
 * The first field (mode) can be 'vowels' or 'consonants'
 * The second field (text) is the sentence to be analyzed.
 */

@Service
public class TextAnalyzerService {
    private static final String NON_VOWELS_REGEX = "(?iu:[^aeiou])";
    private static final String NON_CONSONANTS_REGEX = "(?iu:[^qwrtplkjhgfdszxcvbnm])";

    public Map<Character, Long> findMapOfVowelsOrConsonants(TextAnalyze textAnalyze) {
        return Stream.of(transformTextAnalyze(textAnalyze).getText())
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(TextAnalyzerService::toUpperCase, Collectors.counting()));
    }

    public static TextAnalyze transformTextAnalyze(TextAnalyze textAnalyze) {
        textAnalyze.setText(textAnalyze.getText().transform(text -> {
            if (textAnalyze.getMode().equals("vowels")) {
                return text.replaceAll(NON_VOWELS_REGEX, "");
            } else if (textAnalyze.getMode().equals("consonants")) {
                return text.replaceAll(NON_CONSONANTS_REGEX, "");
            } else {
                throw new ModeNotFoundException(textAnalyze.getMode());
            }
        }));
        return textAnalyze;
    }

    public static Character toUpperCase(Character c) {
        return Character.toUpperCase(c);
    }
}
