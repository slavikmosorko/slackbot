package com.slack.bot.services;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.web.socket.WebSocketSession;

public interface BotAnswerService {
    void sendAnswers(SlackService slackService, Bot bot, WebSocketSession session, Event event);
}
