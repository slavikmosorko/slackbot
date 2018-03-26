package com.slack.bot.services;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BotAnswerServiceTest {

    @Test
    public void sendAnswersTest() {
        BotAnswerService botAnswerService = mock(BotAnswerService.class);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals(null, arg0);
            assertEquals(null, arg1);
            return null;
        }).when(botAnswerService)
                .sendAnswers(any(SlackService.class), any(Bot.class), any(WebSocketSession.class), any(Event.class));
        botAnswerService.sendAnswers(null, null, null, null);
    }

}
