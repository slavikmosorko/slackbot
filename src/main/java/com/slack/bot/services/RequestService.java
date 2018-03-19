package com.slack.bot.services;

import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class RequestService implements IRequestService {

    @Override
    public ResponseEntity<String> executePost(String url,
                                              Map<String, String> headers,
                                              Map<String, String> parameters) {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        headers.forEach(
                post::setHeader
        );

        JsonObject body = new JsonObject();
        parameters.forEach(
                body::addProperty
        );

        try {
            post.setEntity(new StringEntity(body.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response.getStatusLine().getReasonPhrase(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> executeGet(String url,
                                             Map<String, String> parameters) {
        return null;
    }
}
