package com.slack.bot.repositories;

import com.slack.bot.models.Answer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void getAnswerForUserTest() {
        Answer actualAnswer = new Answer();
        actualAnswer.setAnswerStageNumber(0);
        actualAnswer.setAnswerNumber(1);
        actualAnswer.setAnswerCoontent("content");
        answerRepository.save(actualAnswer);
        Answer expectedAnswer = answerRepository
                .getAnswerForUser(actualAnswer.getAnswerNumber(), actualAnswer.getAnswerStageNumber()).get(0);
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }
}
