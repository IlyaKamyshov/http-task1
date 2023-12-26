package org.example.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.jsons.JsonCats;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestCats {

    public static void makeHttpRequest(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(request);

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        TypeReference<List<JsonCats>> typeReference = new TypeReference<>() {};

        ObjectMapper objectMapper = new ObjectMapper();

        List<JsonCats> posts = objectMapper.readValue(body, typeReference);

        posts
                .stream()
                .filter(value -> value.getUpvotes() > 0)
                .forEach(value -> System.out.println(value.toString()));

    }

}
