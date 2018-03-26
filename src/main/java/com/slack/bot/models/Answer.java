package com.slack.bot.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return answerStageNumber == answer.answerStageNumber &&
                answerNumber == answer.answerNumber &&
                Objects.equals(id, answer.id) &&
                Objects.equals(answerCoontent, answer.answerCoontent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, answerStageNumber, answerNumber, answerCoontent);
    }
}
