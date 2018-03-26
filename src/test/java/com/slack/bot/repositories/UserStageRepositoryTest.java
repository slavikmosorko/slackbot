package com.slack.bot.repositories;

import com.slack.bot.models.UserStage;
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
public class UserStageRepositoryTest {

    @Autowired
    private UserStageRepository userStageRepository;

    @Test
    public void getUserStageTest() {
        UserStage actualUserStage = new UserStage();
        actualUserStage.setStageNumber(555);
        actualUserStage.setUserId("TEST");
        userStageRepository.save(actualUserStage);
        UserStage expectedUserStage = userStageRepository.getUserStage("TEST").get(0);
        Assert.assertEquals(expectedUserStage, actualUserStage);
    }
}
