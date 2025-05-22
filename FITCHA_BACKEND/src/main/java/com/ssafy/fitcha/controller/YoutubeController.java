package com.ssafy.fitcha.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Youtube;
import com.ssafy.fitcha.model.service.YoutubeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/youtube")
@Tag(name="Youtube API", description="유튜브에 검색한 결과 정보 조회")
public class YoutubeController {
	private final YoutubeService youtubeService;

	public YoutubeController(YoutubeService youtubeService) {
		this.youtubeService = youtubeService;
	}

	@Operation(summary="유튜브에 검색한 결과 정보 조회",description="검색어가 없을 경우 '전신운동 홈트' 검색결과의 유튜브 정보를 가져옴.")
	@GetMapping("/search")
	public ResponseEntity<Map<String, Object>> getSearchVideos(
	        @RequestParam(value = "query", defaultValue = "전신 운동 홈트") String query,
	        @RequestParam(value = "pageToken", required = false) String pageToken) {
	    Map<String, Object> list = youtubeService.getSearchVideos(query, pageToken);
	    return ResponseEntity.ok(list);
	}
	
	@Operation(summary="특정 유튜브 영상 정보 조회")
	@GetMapping("/{videoId}")
	public ResponseEntity<Map<String, Object>> getVideo(
	        @PathVariable("videoId") String videoId) {
	    Map<String, Object> list = youtubeService.getVideo(videoId);
	    return ResponseEntity.ok(list);
	}
	
	

}
