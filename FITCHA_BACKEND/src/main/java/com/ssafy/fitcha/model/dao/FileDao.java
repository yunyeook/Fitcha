package com.ssafy.fitcha.model.dao;

import java.util.List;

import com.ssafy.fitcha.model.dto.ChallengeFile;

public interface FileDao {

	//챌린지파일 목록 조회
	public List<ChallengeFile> selectChallengeFileList(int challengeBoardId);
	
	// 챌린파일 수정
	public void updateChallengeFiles(List<ChallengeFile> files);
	
	// 챌린지파일 삭제 : 파일 고유번호로 삭제
	public void deleteChallengeFile(int challengeFileId);
	
	// 챌린지파일 등록 
	public void insertChallengeFile(ChallengeFile challengeFile);
}
