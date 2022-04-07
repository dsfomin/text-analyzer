package com.dsfomin.textanalyzer.controller;

import com.dsfomin.textanalyzer.model.TextAnalyze;
import com.dsfomin.textanalyzer.service.TextAnalyzerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TextAnalyzerController.class)
class TextAnalyzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TextAnalyzerService textAnalyzerService;

    @Test
    void analyzeText_whenValidInput_thenReturns200() throws Exception {
        TextAnalyze input = new TextAnalyze("vowels", "aA ecE OiwW");

        mockMvc.perform(post("/text-analyze")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void analyzeText_whenValidInput_thenMapsToBusinessModel() throws Exception {
        TextAnalyze input = new TextAnalyze("vowels", "aA ecE OiwW");
        mockMvc.perform(post("/text-analyze")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());

        ArgumentCaptor<TextAnalyze> inputCaptor = ArgumentCaptor.forClass(TextAnalyze.class);
        verify(textAnalyzerService, times(1))
                .findMapOfVowelsOrConsonants(inputCaptor.capture());
        assertThat(inputCaptor.getValue().getMode()).isEqualTo("vowels");
        assertThat(inputCaptor.getValue().getText()).isEqualTo("aA ecE OiwW");
    }
}