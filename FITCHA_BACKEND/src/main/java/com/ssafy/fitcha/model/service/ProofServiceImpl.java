package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.ProofDao;
import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.SearchProof;

@Service
public class ProofServiceImpl implements ProofService {

	// 생성자 의존성 주입
	private ProofDao proofDao;

	public ProofServiceImpl(ProofDao proofDao) {
		this.proofDao = proofDao;
	}

	// 인증글 검색 조회 ( 검색 없으실 전체 조회 )
	@Override
	public List<Proof> getSearchProofs(SearchProof search) {
		return proofDao.selectSearchProofList(search);
	}

	// 인증글 상세 조회 
	@Override
	public Proof getProofDetails(int proofBoardId) {
		return proofDao.selectProofBoard(proofBoardId);
	}

	// 인증글 등록
	@Override
	public boolean registProof(Proof proof) {
		return 1 == proofDao.insertProofBoard(proof);
	}

	// 인증글 수정
	@Override
	public boolean updateProof(Proof proof) {
		return 1 == proofDao.updateProofBoard(proof) ;
	}
	
	// 인증 글 삭제
	@Override
	public boolean deleteProofBoard(int challengeFileId) {
		return 1 == proofDao.deleteProofBoard(challengeFileId);
	}

	//챌린지 글 삭제시 내 인증글 삭제 
	@Override
	public boolean deleteMyProofBoard(Proof proof) {
		return 1== proofDao.deleteMyProofBoard(proof);
		
	}
}
