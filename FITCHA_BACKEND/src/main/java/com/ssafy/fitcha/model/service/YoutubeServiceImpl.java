package com.ssafy.fitcha.model.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
	private final String YOUTUBE_VIDEOS_URL = "https://www.googleapis.com/youtube/v3/videos";

	@Override
	public List<Youtube> getSearchVideos(String query) {
		RestTemplate restTemplate = new RestTemplate();

		// 1. 검색 요청 (영상 ID 수집)
		String searchUrl = UriComponentsBuilder.fromHttpUrl(YOUTUBE_SEARCH_URL).queryParam("part", "snippet")
				.queryParam("q", query).queryParam("key", apiKey).queryParam("type", "video")
				.queryParam("maxResults", 10).build().toUriString();

		Map<String, Object> searchResponse = restTemplate.getForObject(searchUrl, Map.class);
		List<Map<String, Object>> searchItems = (List<Map<String, Object>>) searchResponse.get("items");

		List<String> videoIds = new ArrayList<>();
		Map<String, Map<String, Object>> snippetMap = new HashMap<>();

		for (Map<String, Object> item : searchItems) {
			Map<String, Object> id = (Map<String, Object>) item.get("id");
			Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");

			String videoId = (String) id.get("videoId");
			videoIds.add(videoId);
			snippetMap.put(videoId, snippet);
		}

		// 2. 각 영상의 duration 정보 확인 : 15 이상 영상만 필터
		String detailsUrl = UriComponentsBuilder.fromHttpUrl(YOUTUBE_VIDEOS_URL).queryParam("part", "contentDetails")
				.queryParam("id", String.join(",", videoIds)).queryParam("key", apiKey).build().toUriString();

		Map<String, Object> detailResponse = restTemplate.getForObject(detailsUrl, Map.class);
		List<Map<String, Object>> detailItems = (List<Map<String, Object>>) detailResponse.get("items");

		List<Youtube> results = new ArrayList<>();

		for (Map<String, Object> detailItem : detailItems) {
			String videoId = (String) detailItem.get("id");
			Map<String, Object> contentDetails = (Map<String, Object>) detailItem.get("contentDetails");
			String duration = (String) contentDetails.get("duration");

			if (isLongerThan15Minutes(duration)) {
				Map<String, Object> snippet = snippetMap.get(videoId);

				String title = (String) snippet.get("title");
				String description = (String) snippet.get("description");
				String thumbnailUrl = ((Map<String, Object>) ((Map<String, Object>) snippet.get("thumbnails"))
						.get("high")).get("url").toString();
				String channelTitle = (String) snippet.get("channelTitle");
				String publishedAt = convertToKoreanTime((String) snippet.get("publishedAt"));

				String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
				String iframe = "<iframe width='560' height='315' src='https://www.youtube.com/embed/" + videoId
						+ "' frameborder='0' allowfullscreen></iframe>";

				results.add(new Youtube(videoId, title, videoUrl, iframe, description, thumbnailUrl, channelTitle,
						publishedAt));
			}
		}

		return results;
	}

	// 날짜포맷팅 (ex) "2024-05-01T12:34:56Z" → 한국 시간 기준: "2024년 5월 1일 오후 9시 34분")
	private String convertToKoreanTime(String utcString) {
		// 1. UTC 문자열을 ZonedDateTime 객체로 변환
		ZonedDateTime utcZoned = ZonedDateTime.parse(utcString);

		// 2. 한국 시간대로 변환
		ZonedDateTime koreaZoned = utcZoned.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

		// 3. 원하는 포맷 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분")
				.withLocale(java.util.Locale.KOREA);

		// 4. 포맷 적용 후 반환
		return koreaZoned.format(formatter);
	}

	// 15분 이상인지 확인
	private boolean isLongerThan15Minutes(String isoDuration) {
		int totalSeconds = parseDurationToSeconds(isoDuration);
		return totalSeconds >= 900;
	}

	// ISO 8601 포맷 파싱
	private int parseDurationToSeconds(String duration) {
		int hours = 0, minutes = 0, seconds = 0;

		duration = duration.replace("PT", "");
		if (duration.contains("H")) {
			String[] split = duration.split("H");
			hours = Integer.parseInt(split[0]);
			duration = split[1];
		}
		if (duration.contains("M")) {
			String[] split = duration.split("M");
			minutes = Integer.parseInt(split[0].replaceAll("[^0-9]", ""));
			duration = split.length > 1 ? split[1] : "";
		}
		if (duration.contains("S")) {
			seconds = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
		}

		return hours * 3600 + minutes * 60 + seconds;
	}
}
