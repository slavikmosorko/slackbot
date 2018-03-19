package com.slack.bot.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IRequestService {

    ResponseEntity<String> executePost(String url,
                                  Map<String, String> headers,
                                  Map<String, String> parameters);

    ResponseEntity<?> executeGet(String url,
                                 Map<String, String> parameters);
}
