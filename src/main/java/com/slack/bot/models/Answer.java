package com.slack.bot.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "answer_stage_number")
    private int answerStageNumber;

    @Column(name = "answer_number")
    private int answerNumber;

    @Column(name = "answer_content", length = 10485760)
    private String answerCoontent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnswerStageNumber() {
        return answerStageNumber;
    }

    public void setAnswerStageNumber(int answerStageNumber) {
        this.answerStageNumber = answerStageNumber;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getAnswerCoontent() {
        return answerCoontent;
    }

    public void setAnswerCoontent(String answerCoontent) {
        this.answerCoontent = answerCoontent;
    }
}
