package com.community.netflickers.config;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.community.netflickers.service.CategoryService;
import com.community.netflickers.service.dto.JsonDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StepConfig {
	
	@Autowired CategoryService categoryService;
	
	@Scheduled(cron="0 0 14 * * ?")
	public void getCategories() throws IOException, InterruptedException {
    		HttpRequest request = HttpRequest.newBuilder()
    				.uri(URI.create("https://ott-details.p.rapidapi.com/advancedsearch?start_year=2010&end_year=2023&min_imdb=7&max_imdb=10&genre=action&language=english&type=movie&sort=latest&page=1"))
    				.header("X-RapidAPI-Key", "cf4c7bf2damsh880fdbe391e0a71p15503cjsndd4a9e539a74")
    				.header("X-RapidAPI-Host", "ott-details.p.rapidapi.com")
    				.method("GET", HttpRequest.BodyPublishers.noBody())
    				.build();
    		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    		
//    		Category[] array = gson.fromJson(response.body(),Category[].class);
    		Gson gson = new Gson();

    		JsonDto json = gson.fromJson(response.body(), new TypeToken<JsonDto>(){}.getType());
    		categoryService.save(json.getResults());


    }

}
