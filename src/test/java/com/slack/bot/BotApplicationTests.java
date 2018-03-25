package com.slack.bot;

import com.slack.bot.models.UserStage;
import com.slack.bot.repositories.UserStageRepository;
import com.slack.bot.services.UserStageServiceImpl;
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
public class BotApplicationTests {

    @Autowired
    private UserStageServiceImpl userStageServiceImpl;

    @Autowired
    private UserStageRepository userStageRepository;

    @Test
    public void contextLoads() {
        UserStage actualUserStage = new UserStage();
        actualUserStage.setStageNumber(555);
        actualUserStage.setUserId("TEST");
        userStageRepository.save(actualUserStage);
        UserStage expectedUserStage = userStageRepository.getUserStage("TEST").get(0);
        Assert.assertEquals(expectedUserStage, actualUserStage);
    }
}
