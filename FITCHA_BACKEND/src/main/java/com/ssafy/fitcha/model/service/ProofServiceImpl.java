package com.ssafy.fitcha.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.ProofDao;

@Service
public class ProofServiceImpl implements ProofService {
	private ProofDao proofDao;
	public ProofServiceImpl(ProofDao proofDao) {
		this.proofDao=proofDao;
	}
	//해당 챌린지글의 인증 글 삭제
	@Override
	public void deleteProofBoard(int challengeFileId) {
		proofDao.deleteProofBoard(challengeFileId);
	}

}
