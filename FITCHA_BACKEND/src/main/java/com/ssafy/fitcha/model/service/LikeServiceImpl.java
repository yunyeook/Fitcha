package com.ssafy.fitcha.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.LikeDao;
import com.ssafy.fitcha.model.dto.Like;

@Service
public class LikeServiceImpl implements LikeService {

	private LikeDao likeDao;

	public LikeServiceImpl(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	// 로그인 유저의 챌린지 글 좋아요 여부 조회
	@Override
	public boolean getChallengeLike(int challengeBoardId, String nickName) {
		Like like = new Like();
		like.setBoardId(challengeBoardId);
		like.setWriter(nickName);

		return null != likeDao.selectChallengeLike(like);
	}

	// 로그인 유저의 챌린지 글 좋아요 상태 수정
	@Override
	public boolean updateChallengeLike(boolean isLiked, int challengeBoardId, String nickName) {
		Like like = new Like();
		like.setBoardId(challengeBoardId);
		like.setWriter(nickName);

		// 좋아요버튼눌렀다면
		if (isLiked) {
			return 1 == likeDao.insertChallengeLike(like);
		} else
			return 1 == likeDao.deleteChallengeLike(like);

	}

	// 챌린지글의 좋아요수 조회
	@Override
	public int getChallengeLikeCount(int challengeBoardId) {
		return likeDao.selectChallengeLikeCount(challengeBoardId);
	}

	// -------------------- 인증글 좋아요-------------------------------------------------

	// 로그인 유저의 인증글 좋아요 여부 조회
	@Override
	public boolean getProofLike(int proofBoardId, String nickName) {
		Like like = new Like();
		like.setBoardId(proofBoardId);
		like.setWriter(nickName);

		return null != likeDao.selectProofLike(like);
	}

	@Override
	public boolean updateProofLike(boolean isLiked, int proofBoardId, String nickName) {
		Like like = new Like();
		like.setBoardId(proofBoardId);
		like.setWriter(nickName);

		// 좋아요버튼눌렀다면
		if (isLiked)
			return 1 == likeDao.insertProofLike(like);
		else
			return 1 == likeDao.deleteProofLike(like);

	}

}
