package com.dsfomin.textanalyzer.controller;

import com.dsfomin.textanalyzer.model.TextAnalyze;
import com.dsfomin.textanalyzer.service.TextAnalyzerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/text-analyze")
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextAnalyzerController.class);

    private final TextAnalyzerService textAnalyzerService;

    @PostMapping
    public ResponseEntity<Map<Character, Long>> analyzeText(@Valid @RequestBody TextAnalyze textAnalyze) {
        LOGGER.info(MarkerFactory.getMarker("IMPORTANT"), "Post method analyzeText({})", textAnalyze.toString());
        return ResponseEntity.ok(textAnalyzerService.findMapOfVowelsOrConsonants(textAnalyze));
    }
}
