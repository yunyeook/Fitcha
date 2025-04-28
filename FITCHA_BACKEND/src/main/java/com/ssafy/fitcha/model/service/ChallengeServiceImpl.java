package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dao.ChallengeDao;
import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.ChallengeFile;
import com.ssafy.fitcha.model.dto.Comment;
import com.ssafy.fitcha.model.dto.Search;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	private ChallengeDao challengeDao;
	private FileService fileService;
	private ProofService proofService;
	private CommentService commentService;

	public ChallengeServiceImpl(ChallengeDao challengeDao, FileService fileService, ProofService proofService,
			CommentService commentService) {
		this.challengeDao = challengeDao;
		this.fileService = fileService;
		this.proofService = proofService;
		this.commentService = commentService;
	}

	@Override
	// 검색 목록 조회 (검색어 없을 시 전체 조회)
	public List<Challenge> getSearchChallenges(Search search) {
		List<Challenge> challengeBoardList = challengeDao.selectSearchChallengeBoardList(search);

		for (Challenge challenge : challengeBoardList) {
			int challengeBoardId = challenge.getChallengeBoardId();
			challenge.setChallengeFiles(fileService.getChallengeFileList(challengeBoardId));
		}
		return challengeBoardList;
	}

	// 상세 조회
	@Override
	public Challenge getChallengeDetail(int challengeBoardId) {
		Challenge challenge = challengeDao.selectChallengeBoard(challengeBoardId);
		List<ChallengeFile> files = fileService.getChallengeFileList(challengeBoardId);
		challenge.setChallengeFiles(files);
		List<Comment> comments = commentService.getChallengeCommentList(challengeBoardId);
		challenge.setComments(comments);
		return challenge;
	}

	// 수정 (챌린지 보드 : 수정, 챌린지 파일 : 등록 및 삭제)
	@Override
	public void updateChallenge(Challenge challenge, List<MultipartFile> files, List<Integer> deleteChallengeFileIds)
			throws Exception {
		challengeDao.updateChallengeBoard(challenge);
		// 챌린지 파일 삭제
		fileService.deleteChallengeFile(deleteChallengeFileIds);
		// 챌린지 파일 등록 (새로 추가된것만 등록..?)
		fileService.insertChallengeFile(files, challenge.getChallengeBoardId(), challenge.getWriter());

	}

	// 삭제
	@Override
	public void deleteChallenge(int challengeBoardId) {
		// 내가쓴 챌린지글과 파일 삭제
		challengeDao.deleteChallengeBoard(challengeBoardId);
		// 내가쓴 인증글과 파일 삭제
		proofService.deleteProofBoard(challengeBoardId);

	}

	// 등록
	@Override
	public void registChallenge(Challenge challenge, List<MultipartFile> files) throws Exception {
		challengeDao.insertChallengeBoard(challenge);
		fileService.insertChallengeFile(files, challenge.getChallengeBoardId(), challenge.getWriter());
	}

}
