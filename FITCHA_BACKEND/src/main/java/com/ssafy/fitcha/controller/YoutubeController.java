package com.ssafy.fitcha.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Youtube;
import com.ssafy.fitcha.model.service.YoutubeService;

@RestController

@RequestMapping("/youtube")
public class YoutubeController {
	private final YoutubeService youtubeService;

	public YoutubeController(YoutubeService youtubeService) {
		this.youtubeService = youtubeService;
	}

	@GetMapping("/search")
	public ResponseEntity<List<Youtube>> search(@RequestParam("query") String query) {

		List<Youtube> list = youtubeService.getSearchVideos(query);
		return ResponseEntity.ok(list);
	}

}
