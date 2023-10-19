package com.oktay.quizapp.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "quiz")
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String quizTitle;
   @ManyToMany
   private List<Question> questions;

}
