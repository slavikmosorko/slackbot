package com.slack.bot.controllers;

import com.slack.bot.services.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SlashCommandsController {

    private IRequestService requestService;

    @Autowired
    public SlashCommandsController(IRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> test() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", "@Mosorko, Hello!");
        return requestService.executePost("https://hooks.slack.com/services/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",
                headers,
                parameters);
    }
}
