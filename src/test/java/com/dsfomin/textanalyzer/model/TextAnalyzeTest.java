package com.dsfomin.textanalyzer.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzeTest {

    @Test
    void getMode() {
        TextAnalyze textAnalyze = new TextAnalyze("vowels", "aaa");
        textAnalyze.setText("bbb");
        textAnalyze.setMode("consonants");

        assertThat(textAnalyze.getText()).isNotEqualTo("aaa");
        assertThat(textAnalyze.getText()).isEqualTo("bbb");

        assertThat(textAnalyze.getMode()).isNotEqualTo("vowels");
        assertThat(textAnalyze.getMode()).isEqualTo("consonants");
    }
}