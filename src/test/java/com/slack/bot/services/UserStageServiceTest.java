package com.slack.bot.services;

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
public class UserStageServiceTest {

    @Autowired
    UserStageService userStageService;

    @Test
    public void getUserStageTest() {
        int actualStageNr = 0;
        int expectedStageNr = userStageService.getUserStage("AAAAABBBBBB");
        Assert.assertEquals(actualStageNr, expectedStageNr);
    }

    @Test
    public void saveUserStageTest() {
        int actualStageNr = 0;
        userStageService.saveUserStage(actualStageNr,"aaabbb");
        int expectedStageNr = userStageService.getUserStage("aaabbb");
        Assert.assertEquals(actualStageNr, expectedStageNr);
    }
}
