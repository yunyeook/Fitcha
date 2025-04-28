package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Search;
import com.ssafy.fitcha.model.dto.User;

public interface ChallengeService {

	//검색 목록 조회 (검색어 없을 시 전체 조회)
	List<Challenge> getSearchChallenges(Search search)throws Exception ;

	//상세 정보 
	Challenge getChallengeDetail(int challengeBoardId);

	//수정 
	void updateChallenge(Challenge challenge, List<MultipartFile> files,List<Integer> deleteChallengeFileIds) throws Exception;
	
	//삭제 
	void deleteChallenge(int challengeBoardId,User user,String writer);

	//등록
	void registChallenge(Challenge challenge, List<MultipartFile> files)throws Exception ;

}
