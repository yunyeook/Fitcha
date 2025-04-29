package com.ssafy.fitcha.model.dao;

import java.util.List;

import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.Search;

public interface ProofDao {

	// 인증글 검색 조회 (검색 조건 없으실 전체 조회)
	List<Proof> selectSearchProofList(Search search);

	// 인증글 상세 조회
	Proof selectProofBoard(int proofBoardId);

	// 인증글 등록
	int insertProofBoard(Proof proof);

	// 인증글 수정
	int updateProofBoard(Proof proof);

	// 인증글 삭제
	int deleteProofBoard(int challengeFileId);

	//챌린지글 삭제시 내 인증글 삭제
	int deleteMyProofBoard(Proof proof);

}
