package com.ssafy.fitcha.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Participate;
import com.ssafy.fitcha.model.dto.SearchChallenge;

public interface ChallengeDao {
	// 검색한 챌린지보드 목록 조회
	public List<Challenge> selectSearchChallengeBoardList(SearchChallenge search);

	// 챌린지보드 상세 조회
	public Challenge selectChallengeBoard(int challengeBoardId);

	// 챌린지보드 수정
	public int updateChallengeBoard(Challenge challenge);

	// 챌린지보드 삭제
	public int deleteChallengeBoard(int challengeBoardId);

	// 챌린지보드 등록
	public int insertChallengeBoard(Challenge challenge);
	//동시에 참여중 등록
	public int insertParticipantChallenge(Challenge challenge);


	// 챌린지보드 조회수 증가
	public int updateChallengeViewCount(int challengeBoardId);

	//최근등록한| 참여자수 많은| 좋아요많은 | 조회수 많은 챌린지글 조회
	public List<Challenge> selectTop10Challenges(String orderBy);

	//현재 유저의 챌린지 참여 여부.
	public int selectChallengeParticipated(Participate participate);


}
