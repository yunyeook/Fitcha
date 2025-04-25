package com.ssafy.fitcha.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.File;
import com.ssafy.fitcha.model.dto.Search;

@Mapper()
public interface ChallengeDao {
	//검색한 챌린지보드 목록 조회 
	public List<Challenge> selectSearchChallengeBoardList(Search search);
	
	//챌린지 번호와 맞는 사진 목록 조회
	public List<File> selectChallengeFileList(int challengeBoardId);

}
