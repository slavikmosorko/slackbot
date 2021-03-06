package com.slack.bot.services;

import com.slack.bot.controllers.SlackBotController;
import com.slack.bot.models.Answer;
import com.slack.bot.repositories.AnswerRepository;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Service
public class BotAnswerServiceImpl implements BotAnswerService {
    private static final Logger logger = LoggerFactory.getLogger(SlackBotController.class);

    @Value("${endConversationWord}")
    private String END_CONVERSATION_WORD;
    @Value("${previousConversationWord}")
    private String PREVIOUS_CONVERSATION_WORD;
    @Value("${byeMessage}")
    private String BYE_MESSAGE;
    @Value("${errorMessage}")
    private String ERROR_MESSAGE;

    private UserStageService userStageService;
    private AnswerRepository answerRepository;

    @Autowired
    public BotAnswerServiceImpl(UserStageService userStageService, AnswerRepository answerRepository) {
        this.userStageService = userStageService;
        this.answerRepository = answerRepository;
    }

    @Override
    public void sendAnswers(SlackService slackService, Bot bot, WebSocketSession session, Event event) {
        int userStage = userStageService.getUserStage(event.getUserId());

        if (bot.isConversationOn(event)) {
            if (END_CONVERSATION_WORD.equals(event.getText().toUpperCase())) {
                userStageService.saveUserStage(0, event.getUserId());
                bot.reply(session, event, new Message(BYE_MESSAGE));
                bot.stopConversation(event);
                return;
            }
            if (PREVIOUS_CONVERSATION_WORD.equals(event.getText().toUpperCase()) && userStage > 0) {
                userStage--;
                userStageService.saveUserStage(userStage, event.getUserId());
                return;
            }
        } else {
            bot.startConversation(event, "sendAnswers");
        }

        int answerNumber = 0;

        try {
            if (userStage != 0) {
                answerNumber = new Integer(event.getText());
            }
        } catch (Exception e) {
            bot.reply(session, event, new Message(ERROR_MESSAGE));
            logger.warn("Cant parse " + event.getText());
            return;
        }

        List<Answer> answers = answerRepository.getAnswerForUser(answerNumber, userStage);
        answers.stream().forEach(
                answer -> bot.reply(session, event, new Message(answer.getAnswerCoontent()))
        );

        if (answers.size() == 0) {
            userStage = 0;
        } else {
            userStage++;
        }

        userStageService.saveUserStage(userStage, event.getUserId());
    }
}
