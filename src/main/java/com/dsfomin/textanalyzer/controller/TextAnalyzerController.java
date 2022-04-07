package com.dsfomin.textanalyzer.controller;

import com.dsfomin.textanalyzer.model.TextAnalyze;
import com.dsfomin.textanalyzer.service.TextAnalyzerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/text-analyze")
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    @PostMapping
    public ResponseEntity<Map<Character, Long>> analyzeText(@Valid @RequestBody TextAnalyze textAnalyze) {
        return ResponseEntity.ok(textAnalyzerService.findMapOfVowelsOrConsonants(textAnalyze));
    }
}
