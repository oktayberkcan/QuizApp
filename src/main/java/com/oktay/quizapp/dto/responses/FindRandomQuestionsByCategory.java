package com.oktay.quizapp.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindRandomQuestionsByCategory {
    private String category;
    private int numQ;
    private String title;
}
