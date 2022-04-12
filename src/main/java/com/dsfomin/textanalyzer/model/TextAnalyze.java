package com.dsfomin.textanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TextAnalyze {
    private String mode; //"vowels"
    private String text;
}
