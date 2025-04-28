package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dto.ChallengeFile;

public interface FileService {

	//챌린지 파일 목록 조회
	List<ChallengeFile> getChallengeFileList(int challengeBoardId);

	//챌린지 파일 리스트 삭제 
	void deleteChallengeFile(List<Integer> deleteChallengeFileIds);
	
	//챌린지 파일 등록
	void insertChallengeFile(List<MultipartFile> files,int challengeBoardId,String writer)throws Exception;
	

}
