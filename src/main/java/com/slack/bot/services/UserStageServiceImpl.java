package com.slack.bot.services;

import com.slack.bot.models.UserStage;
import com.slack.bot.repositories.UserStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserStageServiceImpl implements UserStageService {

    private UserStageRepository userStageRepository;

    @Autowired
    public UserStageServiceImpl(UserStageRepository userStageDao) {
        this.userStageRepository = userStageDao;
    }

    @Override
    public int getUserStage(String userId) {
        List<UserStage> userStageList = userStageRepository.getUserStage(userId);
        if (userStageList.size() > 0) {
            return userStageList.get(0).getStageNumber();
        }
        return 0;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void saveUserStage(int userStage, String userId) {
        List<UserStage> userStageList = userStageRepository.getUserStage(userId);
        UserStage userStageObj = new UserStage();
        if (userStageList.size() > 0) {
            userStageObj = userStageList.get(0);
        }
        userStageObj.setStageNumber(userStage);
        userStageObj.setUserId(userId);
        userStageRepository.save(userStageObj);
    }
}
