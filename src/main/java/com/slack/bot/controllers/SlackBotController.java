package com.slack.bot.controllers;

import com.slack.bot.services.BotAnswerService;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SlackBotController extends Bot {

    private BotAnswerService botAnswerService;

    @Value("${slackBotToken}")
    private String slackToken;

    @Autowired
    public SlackBotController(BotAnswerService botAnswerService) {
        this.botAnswerService = botAnswerService;
    }

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void sendAnswers(WebSocketSession session, Event event) {
        botAnswerService.sendAnswers(slackService, this, session, event);
    }

}