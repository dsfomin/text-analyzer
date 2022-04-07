package com.dsfomin.textanalyzer.controller;

import com.dsfomin.textanalyzer.model.TextAnalyze;
import com.dsfomin.textanalyzer.service.TextAnalyzerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/text-analyze")
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    @PostMapping
    public ResponseEntity<Map<Character, Long>> analyzeText(@RequestBody TextAnalyze textAnalyze) {
        Map<Character, Long> mapOfVowelsOrConsonants = textAnalyzerService
                .findMapOfVowelsOrConsonants(textAnalyze.getMode(), textAnalyze.getText());
        return ResponseEntity.ok(mapOfVowelsOrConsonants);
    }
}
