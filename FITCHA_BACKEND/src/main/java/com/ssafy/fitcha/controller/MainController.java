package com.ssafy.fitcha.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.service.ChallengeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {
	private final ChallengeService challengeService;
	
	public MainController(ChallengeService challengeService) {
		this.challengeService=challengeService;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> goToMain() {
		return new ResponseEntity<String>("main", HttpStatus.OK);
	}
	
	@GetMapping("/main")
	public ResponseEntity<Map<String, List<Object>>> main() {
		Map mainMap = new HashMap<>();
		List<Challenge>recentChallenges =  challengeService.getTop10Challenges("recent");
		List<Challenge>manyParticipantsChallenges =  challengeService.getTop10Challenges("participants");
		List<Challenge>manyLikesChallenges =  challengeService.getTop10Challenges("likes");
		List<Challenge>manyViewsiewsChallenges =  challengeService.getTop10Challenges("views");
		mainMap.put("recent", recentChallenges);
		mainMap.put("participants", manyParticipantsChallenges);
		mainMap.put("likes", manyLikesChallenges);
		mainMap.put("views", manyViewsiewsChallenges);
		
		return ResponseEntity.ok(mainMap);
	}
	
	

}
