package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dao.ProofDao;
import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.SearchProof;

@Service
public class ProofServiceImpl implements ProofService {

	// 생성자 의존성 주입
	private ProofDao proofDao;
	private FileService fileService;

	public ProofServiceImpl(ProofDao proofDao, FileService fileService) {
		this.proofDao = proofDao;
		this.fileService = fileService;
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
	public boolean registProof(Proof proof, List<MultipartFile> files) throws Exception {
		fileService.insertProofFile(files, proof.getProofBoardId(), proof.getWriter());
		return 1 == proofDao.insertProofBoard(proof);
	}

	// 인증글 수정
	@Override
	public boolean updateProof(Proof proof, List<MultipartFile> files, List<Integer> deleteProofFileIds) throws Exception {
		boolean isUpdated = (1 == proofDao.updateProofBoard(proof));
		// 인증글 파일 삭제
		if (deleteProofFileIds != null && deleteProofFileIds.size() > 0) {
			fileService.deleteProofFile(deleteProofFileIds);

		}
		// 인증글 파일 등록 (새로 추가된것 등록)
		if (files != null && files.size() > 0) {
			fileService.insertProofFile(files, proof.getChallengeBoardId(), proof.getWriter());

		}
		return isUpdated;
	}

	// 인증 글 삭제
	@Override
	public boolean deleteProofBoard(int challengeFileId) {
		return 1 == proofDao.deleteProofBoard(challengeFileId);
	}

	// 챌린지 글 삭제시 내 인증글 삭제
	@Override
	public boolean deleteMyProofBoard(Proof proof) {
		return 1 == proofDao.deleteMyProofBoard(proof);
	}

}
