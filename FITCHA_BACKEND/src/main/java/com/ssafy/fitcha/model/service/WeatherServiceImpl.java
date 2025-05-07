package com.ssafy.fitcha.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fitcha.model.dto.Weather;
import com.ssafy.fitcha.util.GridUtil;

import jakarta.annotation.PostConstruct;

@Service
public class WeatherServiceImpl implements WeatherService {
	@PostConstruct
	public void init() {
	    System.out.println("🔑 [serviceKey 확인] " + serviceKey);
	}

	// 생성자 주입
	private final WebClient weatherWebClient;
	private final GridUtil gridUtil;

	public WeatherServiceImpl(WebClient weatherWebClient, GridUtil gridUtil) {
		this.weatherWebClient = weatherWebClient;
		this.gridUtil = gridUtil;
	}

	@Value("${weather.api.key}")
	private String serviceKey;

	@Override
	public Weather getCurrentWeather(double lat, double lon) {
		
		int[] grid = gridUtil.convertToGridUtil(lat, lon); // 위도 경도를 좌표로 변환
		int nx = grid[0];
		int ny = grid[1];

		// 현재 날짜를 yyyyMMdd 형식으로 포맷팅
//		String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		String baseDate = "20250507";

		// 기상청 API 요구사항 : 예보 기준 시간은 40분 전으로 설정
//		String baseTime = LocalTime.now().minusMinutes(40) // 현재 시간보다 40분 이전
//				.format(DateTimeFormatter.ofPattern("HHmm"));
		String baseTime = "1000";

		String url = UriComponentsBuilder
				.fromHttpUrl("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
				.queryParam("ServiceKey", serviceKey) // 인증키
				.queryParam("dataType", "JSON") // 응답 포맷
				.queryParam("base_date", baseDate) // 기준 날짜
				.queryParam("base_time", baseTime) // 기준 시간
				.queryParam("nx", nx) // 격자 X 좌표
				.queryParam("ny", ny) // 격자 Y 좌표
				.build(false) // 쿼리 파라미터에 인코딩하지 않음 (serviceKey 때문에)
				.toUriString(); // 최종 문자열로 반환

		String json = weatherWebClient.get().uri(url)
		        .retrieve()  // 응답 수신
		        .bodyToMono(String.class)  // Mono로 비동기 응답처리 (String 타입으로 받음)
		        .doOnTerminate(() -> System.out.println("API 호출 종료"))  // API 호출 종료 로그
		        .block();  // 비동기 -> 동기로 전환
		
	    System.out.println("API 응답 데이터: " + json);  // 응답 확인

		
		System.out.println("📡 API 호출 baseDate: " + baseDate + ", baseTime: " + baseTime);
		System.out.println("🗺️ 위치 nx: " + nx + ", ny: " + ny);
		System.out.println("🔑 인증키: " + serviceKey); // 보안상 실서비스엔 절대 금지

		
		return parseWeather(json);
	}

	// 기상청에서 응답 받은 json을 자바 Weather Dto로 변환 해주는 함수
	private Weather parseWeather(String json) {
		Weather weather = new Weather();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(json);
			JsonNode items = root.path("response").path("body").path("items").path("item");

			for (JsonNode item : items) {
				String category = item.get("category").asText();
				String value = item.get("obsrValue").asText();

				switch (category) {
				case "T1H": // 기온
					weather.setTemperature(value);
					break;
				case "REH": // 습도
					weather.setHumidity(value);
					break;
				case "PTY": // 강수형태만으로 날씨 설정
					weather.setWeather(ptyCodeToDesc(value));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return weather;
	}

	private String ptyCodeToDesc(String code) {
		return switch (code) {
		case "1" -> "비";
		case "2" -> "비/눈";
		case "3" -> "눈";
		case "4" -> "소나기";
		default -> "없음";
		};
	}

}
