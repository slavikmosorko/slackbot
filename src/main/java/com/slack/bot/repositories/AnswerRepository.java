package com.slack.bot.repositories;

import com.slack.bot.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT * FROM answer as answ WHERE answ.answer_stage_number = :stageNr AND answ.answer_number = :answerNr",
            nativeQuery = true)
    List<Answer> getAnswerForUser(@Param("answerNr") int answerNr, @Param("stageNr") int stageNr);
}
