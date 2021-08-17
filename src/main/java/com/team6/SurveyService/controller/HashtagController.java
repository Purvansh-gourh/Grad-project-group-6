package com.team6.SurveyService.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;


@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class HashtagController {

	Logger logger = LoggerFactory.getLogger(HashtagController.class);
	private HashtagService hashtagService;

	@Autowired
	public HashtagController(HashtagService hashtagService){
		this.hashtagService=hashtagService;
	}

	
//	get all hashtags orderby count
	@GetMapping(produces = "application/json",path = "/dashboard/hashtags")
	public ResponseEntity<?> getAllHashtags(){
		try{
			List<String> surveyList = hashtagService.getTrendingHashtags();
			return ResponseEntity.status(HttpStatus.OK).body(surveyList);
		}
		catch(Exception e){
			logger.error("request to getTrendingHashtags failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}
	}


	
	
	
}
