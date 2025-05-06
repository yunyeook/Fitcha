package com.ssafy.fitcha.model.service;

import java.util.List;

import com.ssafy.fitcha.model.dto.Youtube;

public interface YoutubeService {

	// 필터 적용(검색)시 유튜브 목록 조회
	List<Youtube> getSearchVideos(String query);

}
