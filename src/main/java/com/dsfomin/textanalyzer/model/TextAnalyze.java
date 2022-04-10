package com.dsfomin.textanalyzer.model;

import lombok.*;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TextAnalyze {
    private String mode;
    private String text;
}
