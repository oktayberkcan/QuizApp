package com.oktay.quizapp.dto.requests;

import com.oktay.quizapp.model.entities.Question;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuizRequest {
    private String quizTitle;
    private Integer questionId;
}
