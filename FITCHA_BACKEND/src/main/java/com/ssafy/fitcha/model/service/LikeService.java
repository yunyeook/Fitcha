package com.ssafy.fitcha.model.service;

public interface LikeService {

	boolean getChallengeLike(int challengeBoardId, String nickName);

	boolean updateChallengeLike(boolean isLiked, int challengeBoardId, String nickName);
	
	
	// --------------------------------- 인증글 좋아요 ------------------------
	
	// 인증글 좋아요 조회 
	boolean getProofLike(int proofBoardId, String nickName);
	
	// 인증글 좋아요 수정
	boolean updateProofLike(boolean isLiked, int proofBoardId, String nickName);

}
