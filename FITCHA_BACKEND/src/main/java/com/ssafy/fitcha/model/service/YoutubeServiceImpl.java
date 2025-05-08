package com.ssafy.fitcha.model.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.fitcha.model.dto.Youtube;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	@Value("${youtube.api.key}")
	private String apiKey;

	private final String YOUTUBE_SEARCH_URL = "https://www.googleapis.com/youtube/v3/search";

	@Override
	public List<Youtube> getSearchVideos(String query) {
		// RestTemplate: 외부 HTTP 요청을 보내기 위한 Spring 제공 클래스
		RestTemplate restTemplate = new RestTemplate();

		// YouTube 검색 API 요청 URL 생성
		String searchUrl = UriComponentsBuilder.fromHttpUrl(YOUTUBE_SEARCH_URL)
				.queryParam("part", "snippet")  // snippet: 제목, 채널명, 설명 등 메타정보 포함
				.queryParam("q", query) // 검색어 입력
				.queryParam("key", apiKey) // API 키 설정
				.queryParam("type", "video") // 동영상만 검색 (채널/재생목록 제외)
				.queryParam("videoDuration", "long") // 20분 이상 영상만 포함 (쇼츠 자연스럽게 제외됨)
				.queryParam("maxResults", 10) // 최대 결과 수 설정 
				.build().toUriString();

		// API 호출 및 결과 수신 (Map 형태로 JSON 파싱)
		Map<String, Object> searchResponse = restTemplate.getForObject(searchUrl, Map.class);
		List<Map<String, Object>> searchItems = (List<Map<String, Object>>) searchResponse.get("items");

		// 최종 반환할 Youtube 객체 리스트
		List<Youtube> results = new ArrayList<>();

		// 각 영상 정보 추출 및 Youtube 객체 생성
		for (Map<String, Object> item : searchItems) {
			Map<String, Object> id = (Map<String, Object>) item.get("id");
			Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");

			// 영상 ID 추출
			String videoId = (String) id.get("videoId");

			// snippet 정보를 통해 각 속성 추출
			String title = (String) snippet.get("title"); // 제목
			String description = (String) snippet.get("description"); // 설명
			String thumbnailUrl = ((Map<String, Object>) ((Map<String, Object>) snippet.get("thumbnails")).get("high"))
					.get("url").toString(); // 썸네일 (고화질)
			String channelTitle = (String) snippet.get("channelTitle"); // 채널 이름
			String publishedAt = convertToKoreanTime((String) snippet.get("publishedAt")); // 업로드 날짜 (한국 시간 변환)

			// 영상 URL 및 iframe 코드 생성
			String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
			String iframe = "<iframe width='560' height='315' src='https://www.youtube.com/embed/" + videoId
					+ "' frameborder='0' allowfullscreen></iframe>";

			// Youtube 객체 생성 후 리스트에 추가
			results.add(new Youtube(videoId, title, videoUrl, iframe, description, thumbnailUrl, channelTitle,
					publishedAt));
		}

		// 최종 결과 반환
		return results;
	}

	// UTC 시간을 한국 시간 형식(yyyy년 M월 d일 a h시 m분)으로 변환
	private String convertToKoreanTime(String utcString) {
		ZonedDateTime utcZoned = ZonedDateTime.parse(utcString); // UTC 시간 파싱
		ZonedDateTime koreaZoned = utcZoned.withZoneSameInstant(ZoneId.of("Asia/Seoul")); // 한국 시간대로 변경
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분")
				.withLocale(java.util.Locale.KOREA); // 한국어 포맷 지정
		return koreaZoned.format(formatter); // 포맷팅 결과 반환
	}
}