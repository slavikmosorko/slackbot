package com.slack.bot.services;

import com.slack.bot.controllers.SlackBotController;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class BotAnswerService implements IBotAnswerService {
    private static final Logger logger = LoggerFactory.getLogger(SlackBotController.class);

    @Override
    public void sendAnswer(SlackService slackService, Bot bot, WebSocketSession session, Event event) {
        if (bot.isConversationOn(event)) {
            if (NumberUtils.isNumber(event.getText())) {
                switch (event.getText()) {
                    case "1":
                        bot.reply(session, event, new Message(event.getText()));
                        bot.stopConversation(event);
                        break;
                    default:
                        bot.reply(session, event, new Message("I don't understand you, if you want end this conversation type yes or repeat your choice correctly."));
                        bot.stopConversation(event);
                        bot.startConversation(event, "sendAnswer");   // start conversation
                }
            } else {
                bot.stopConversation(event);
                if (event.getText().toLowerCase().equals("yes")) {
                    bot.reply(session, event, new Message("Bye :cry:"));
                } else {
                    bot.reply(session, event, new Message("I don't understand you, if you want end this conversation type yes or repeat your choice correctly."));
                    bot.startConversation(event, "sendAnswer");   // start conversation
                }
            }
        }
    }

    @Override
    public void sendQuestionsList(SlackService slackService, Bot bot, WebSocketSession session, Event event) {
        bot.startConversation(event, "sendAnswer");   // start conversation
        bot.reply(session, event, new Message("Hi, I am " + slackService.getCurrentUser().getName() + ":simple_smile:"));
        bot.reply(session, event, new Message("You could choose your question type from this list:"));
        bot.reply(session, event, new Message(":heavy_check_mark: Type 1 if you..."));
        bot.reply(session, event, new Message(":heavy_check_mark: Type 2 if you..."));
        bot.reply(session, event, new Message(":heavy_check_mark: Type 3 if you..."));
    }
}
