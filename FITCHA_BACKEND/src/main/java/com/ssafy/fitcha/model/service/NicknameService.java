package com.ssafy.fitcha.model.service;


	import java.util.Random;
	import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.UserDao;

import lombok.RequiredArgsConstructor;

	@Service
	@RequiredArgsConstructor
	public class NicknameService {

	    private final UserDao userDao;
	    private final Random random = new Random();

	    private static final String[] ADJECTIVES = {
	    		   "행복한", "슬기로운", "용감한", "빠른", "귀여운", "똑똑한",
	    		    "재치있는", "친절한", "멋진", "따뜻한", "순수한", "엉뚱한",
	    		    "우아한", "반짝이는", "창의적인", "유쾌한", "상냥한", "든든한",
	    		    "명랑한", "기발한", "배려하는", "활발한", "조용한", "빛나는",
	    		    "달콤한", "섬세한", "놀라운", "진지한", "신비로운", "열정적인"
	    };

	    private static final String[] NOUNS = {
	    		 "호랑이", "토끼", "독수리", "곰", "여우", "고양이",
	    		    "강아지", "펭귄", "사슴", "늑대", "부엉이", "기린",
	    		    "코끼리", "원숭이", "수달", "거북이", "햄스터", "판다",
	    		    "사자", "돌고래", "하마", "양", "말", "치타",
	    		    "앵무새", "돼지", "두더지", "다람쥐", "오리", "고래"
	    };

	    public String generateUniqueNickname() {
	        String nickname;
	        do {
	            nickname = generateRandomNickname();
	        } while (userDao.existsByNickname(nickname)==1);
	        return nickname;
	    }

	    private String generateRandomNickname() {
	        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
	        String noun = NOUNS[random.nextInt(NOUNS.length)];
	        int number = random.nextInt(1000); // 0 ~ 999
	        return adjective + noun + number;
	    }
	}

