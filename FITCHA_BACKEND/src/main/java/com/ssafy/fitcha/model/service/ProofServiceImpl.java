package com.ssafy.fitcha.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	    List<Proof> proofList = proofDao.selectSearchProofList(search);

		// 1. 인증글 ID만 추출
	    List<Integer> proofBoardIds = proofList.stream()
	            .map(Proof::getProofBoardId)
	            .collect(Collectors.toList());

	    if (proofBoardIds.isEmpty()) {
	        return proofList; // 게시글 없음
	    }

	    // 2. 해시태그 목록 가져오기
	    List<Map<String, Object>> rawHashtags = proofDao.selectHashTagsByProofBoardIds(proofBoardIds);

	    // 3. Map<proofBoardId, List<hashtag>> 형태로 정리
	    Map<Integer, List<String>> hashtagMap = new HashMap<>();
	    for (Map<String, Object> row : rawHashtags) {
	        Integer boardId = (Integer) row.get("proof_board_id");
	        String tag = (String) row.get("hashtag");
	        hashtagMap.computeIfAbsent(boardId, k -> new ArrayList<>()).add(tag);
	    }

	    // 4. 인증글에 해시태그 세팅
	    for (Proof proof : proofList) {
	        List<String> tags = hashtagMap.getOrDefault(proof.getProofBoardId(), new ArrayList<>());
	        proof.setHashTags(tags);
	    }

	    return proofList;
	}

	// 인증글 상세 조회
	@Override
	public Proof getProofDetails(int proofBoardId) {
		return proofDao.selectProofBoard(proofBoardId);
	}

	// 인증글 등록
	@Override
	public boolean registProof(Proof proof, List<MultipartFile> files) throws Exception {
		// 인증글 저장
		boolean isRegisted = (1 == proofDao.insertProofBoard(proof));
		int proofBoardId = proof.getProofBoardId();

		fileService.insertProofFile(files, proof.getProofBoardId(), proof.getWriter());

		// 해쉬태그 저장
		Map<String, Object> params = new HashMap<>();
		params.put("proofBoardId", proofBoardId);
		params.put("hashTags", proof.getHashTags());
		proofDao.insertProofBoardHashtags(params);

		return isRegisted;
	}

	// 인증글 수정
	@Override
	public boolean updateProof(Proof proof, List<MultipartFile> files, List<Integer> deleteProofFileIds)
			throws Exception {
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
