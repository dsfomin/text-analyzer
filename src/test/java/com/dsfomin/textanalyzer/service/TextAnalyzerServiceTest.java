package com.dsfomin.textanalyzer.service;

import com.dsfomin.textanalyzer.exception.ModeNotFoundException;
import com.dsfomin.textanalyzer.model.TextAnalyze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TextAnalyzerServiceTest {

    private final TextAnalyzerService underTest = new TextAnalyzerService();

    @Test
    void canGetMapOfVowels() {
        // Given
        String input = "aA ecE OiwW";
        String mode = "vowels";

        TextAnalyze textAnalyze = new TextAnalyze(mode, input);

        // When
        Map<Character, Long> actualMapOfVowels = underTest.findMapOfVowelsOrConsonants(textAnalyze);

        // Then
        Map<Character, Long> expectedMapOfVowels = Map.of(
                'A', 2L,
                'E', 2L,
                'O', 1L,
                'I', 1L
        );
        assertThat(actualMapOfVowels).isEqualTo(expectedMapOfVowels);
    }

    @Test
    void canGetMapOfConsonants() {
        // Given

        String input = "aA ecE OiwW";
        String mode = "consonants";

        TextAnalyze textAnalyze = new TextAnalyze(mode, input);

        // When
        Map<Character, Long> actualMapOfVowels = underTest.findMapOfVowelsOrConsonants(textAnalyze);

        // Then
        Map<Character, Long> expectedMapOfVowels = Map.of(
                'C', 1L,
                'W', 2L
        );
        assertThat(actualMapOfVowels).isEqualTo(expectedMapOfVowels);
    }

    @Test
    void cannotGetMapOfVowelsOrConsonants_whenModeIsWrong() {
        // Given
        String input = "aA ecE OiwW";
        String mode = "something";

        TextAnalyze textAnalyze = new TextAnalyze(mode, input);

        // When
        ModeNotFoundException thrown = Assertions.assertThrows(
                ModeNotFoundException.class,
                () -> underTest.findMapOfVowelsOrConsonants(textAnalyze),
                "Mode: {" + mode + "} wasn't found"
        );
        // Then
        Assertions.assertEquals("Mode: {" + mode + "} wasn't found", thrown.getMessage());
    }

    @Test
    void toUpperCaseTest() {
        // Given
        Character input = 'a';

        // When
        Character actual = TextAnalyzerService.toUpperCase(input);

        // Then
        Assertions.assertEquals('A', actual);
    }
}