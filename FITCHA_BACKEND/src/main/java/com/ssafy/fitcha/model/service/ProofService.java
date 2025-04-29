package com.ssafy.fitcha.model.service;

import java.util.List;

import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.SearchProof;


public interface ProofService {
	
	// 인증글 검색 조회 (검색 없으면 전체 조회)
	List<Proof> getSearchProofs(SearchProof search);
	
	// 인증글 상세 조회 
	Proof getProofDetails(int proofBoardId);

	// 인증글 등록
	boolean registProof(Proof proof);
	
	// 인증글 수정
	boolean updateProof(Proof proof);
	
	// 인증글 삭제
	boolean deleteProofBoard(int challengeFileId);

	//챌린지글 삭제시 내 인증글 삭제
	boolean deleteMyProofBoard(Proof proof);
	

}
