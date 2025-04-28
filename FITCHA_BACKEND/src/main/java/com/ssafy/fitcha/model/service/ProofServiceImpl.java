package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.ProofDao;
import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.Search;

@Service
public class ProofServiceImpl implements ProofService {

	// 생성자 의존성 주입
	private ProofDao proofDao;

	public ProofServiceImpl(ProofDao proofDao) {
		this.proofDao = proofDao;
	}

	// 인증글 검색 조회 ( 검색 없으실 전체 조회 )
	@Override
	public List<Proof> getSearchProofs(Search search) {
		// TODO Auto-generated method stub
		return null;
	}

	// 인증글 상세 조회 
	@Override
	public Proof getProofDetails(int proofBoardId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 인증글 등록
	@Override
	public boolean registProof(Proof proof) {
		// TODO Auto-generated method stub
		return false;
	}

	// 인증글 수정
	@Override
	public boolean updateProof(Proof proof) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 인증 글 삭제
	@Override
	public boolean deleteProofBoard(int challengeFileId) {
		return 1 == proofDao.deleteProofBoard(challengeFileId);
	}
}
