package com.team6.SurveyService.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class SurveyController {


	Logger logger = LoggerFactory.getLogger(HashtagController.class);

	@Autowired
	private SurveyService surveyService;
		
	
	@PostMapping(produces = "application/json",path = "/survey")
	public ResponseEntity<?> saveSurvey(@RequestBody Survey survey){
		surveyService.addSurvey(survey);
		return ResponseEntity.status(HttpStatus.CREATED).body(survey);
	}

	//	get all response
	@GetMapping(produces = "application/json",path = "/dashboard/survey")
	public ResponseEntity<?> getAllSurvey(){
		try{
			List<Survey> surveyList = surveyService.getAllSurvey();
			return ResponseEntity.status(HttpStatus.OK).body(surveyList);
		}
		catch(Exception e){
			logger.error("request to getAllSurvey failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}
	}

	@GetMapping(produces = "application/json",path = "/dashboard/survey/latestAvg/{days}")
	public ResponseEntity<?> getLatestAvg(@PathVariable("days") int days){
		try{
			List<List<String>> latestAvgRating = surveyService.getLatestAvg(days);
			ObjectNode objectNode = new ObjectMapper().createObjectNode();
			for(List<String> list: latestAvgRating)
				objectNode.put(list.get(0),list.get(1) );
			return ResponseEntity.status(HttpStatus.OK).body(objectNode);
		}
		catch(Exception e){
			logger.error("request to getLatestAvg failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}

	}
	//	search survey response by email
	@GetMapping(produces = "application/json",path = "/dashboard/survey/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
		try{
			List<Survey> listByEmail = surveyService.getByEmail(email);
			return ResponseEntity.status(HttpStatus.OK).body(listByEmail);
		}
		catch(Exception e){
			logger.error("request to getByEmail failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}
	}

	//	get complete response of last X days
	@GetMapping(produces = "application/json",path = "/dashboard/survey/latestData/{days}")
	public ResponseEntity<?> getLatestData(@PathVariable("days") int days){
		try{
			List<Survey> latestData = surveyService.getLatestData(days);
			return ResponseEntity.status(HttpStatus.OK).body(latestData);
		}
		catch(Exception e){
			logger.error("request to getLatestData failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}

	}

	@GetMapping(produces = "application/json",path = "/dashboard/survey/avgCategoriesRating/{days}")
	public ResponseEntity<?> getAvgCategoriesRating(@PathVariable("days") int days){
		try{
			List<List<String>> avgCategoriesRating = surveyService.getAvgCategoriesRating(days);
			ObjectNode objectNode = new ObjectMapper().createObjectNode();
			for(List<String> list: avgCategoriesRating)
				objectNode.put(list.get(0),list.get(1) );
			return ResponseEntity.status(HttpStatus.OK).body(objectNode);
		}
		catch(Exception e){
			logger.error("request to getAvgCategoriesRating failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}

	}


	@GetMapping(produces = "application/json",path = "/dashboard/survey/badMoodSince/{days}")
	public ResponseEntity<?> getBadMoodEmployee(@PathVariable("days") int days){
		try{
			List<List<String>> badMoodEmployee = surveyService.getBadMoodEmployee(days);
			ObjectNode objectNode = new ObjectMapper().createObjectNode();
			for(List<String> list: badMoodEmployee)
				objectNode.put(list.get(0),list.get(1) );
			return ResponseEntity.status(HttpStatus.OK).body(objectNode);
		}
		catch(Exception e){
			logger.error("request to getBadMoodEmployee failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request Not Found!!!");
		}

	}
	
}
