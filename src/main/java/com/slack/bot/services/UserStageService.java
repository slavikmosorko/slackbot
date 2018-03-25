package com.slack.bot.services;

public interface UserStageService {
    int getUserStage(String userId);

    void saveUserStage(int userStage, String userId);
}
