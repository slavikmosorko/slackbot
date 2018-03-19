package com.slack.bot.controllers;

import com.slack.bot.services.IBotAnswerService;
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

    private IBotAnswerService botAnswerService;

    @Value("${slackBotToken}")
    private String slackToken;

    @Autowired
    public SlackBotController(IBotAnswerService botAnswerService) {
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

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE}, next = "sendAnswer")
    public void sendAnswersList(WebSocketSession session, Event event) {
        botAnswerService.sendQuestionsList(slackService, this, session, event);
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void sendAnswer(WebSocketSession session, Event event) {
        botAnswerService.sendAnswer(slackService, this, session, event);
    }

}