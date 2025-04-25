package com.ssafy.fitcha.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Search;
import com.ssafy.fitcha.model.service.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	private ChallengeService challengeService;
	public ChallengeController(ChallengeService challengeService) {
		this.challengeService=challengeService;
	}
	
	//검색 기반 운동 챌린지 목록 조회.
	@GetMapping
	public ResponseEntity<List<Challenge>> getSearchChallenges(@ModelAttribute Search search) {
	    List<Challenge> challenges = challengeService.getSearchChallenges(search);
	    if (challenges == null || challenges.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(challenges);
	}

}
