package com.ssafy.fitcha.model.service;

import com.ssafy.fitcha.model.dto.Like;

public interface LikeService {

	boolean getChallengeLike(int challengeBoardId, String nickName);

	boolean updateChallengeLike(boolean isLiked, int challengeBoardId, String nickName);

	// --------------------------------- 인증글 좋아요 ------------------------

	// 인증글 좋아요 조회
	boolean getProofLike(int proofBoardId, String nickName);

	// 인증글 좋아요
	public boolean addLike(int boardId, String nickName);

	// 인증글 좋아요 취소
	public boolean removeLike(int boardId, String nickName);

	// --------------------------------- 영상 좋아요 ------------------------

	// 영상 좋아요 수정
	boolean updateVideoLike(Like like);

	// 영상 좋아요 여부 및 좋아요 수 조회
	Like getVideoLike(String videoId, String writer);

}
