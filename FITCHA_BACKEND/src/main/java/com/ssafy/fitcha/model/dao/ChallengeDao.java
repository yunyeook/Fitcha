package com.ssafy.fitcha.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.SearchChallenge;


public interface ChallengeDao {
	//검색한 챌린지보드 목록 조회 
	public List<Challenge> selectSearchChallengeBoardList(SearchChallenge search);
	

	//챌린지보드 상세 조회
	public Challenge selectChallengeBoard(int challengeBoardId);
	
	//챌린지보드 수정
	public void updateChallengeBoard(Challenge challenge);

	//챌린지보드 삭제
	public int deleteChallengeBoard(int challengeBoardId);

	//챌린지보드 등록
	public void insertChallengeBoard(Challenge challenge);

	


}
