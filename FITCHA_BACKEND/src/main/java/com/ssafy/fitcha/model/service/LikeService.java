package com.ssafy.fitcha.model.service;

public interface LikeService {

	boolean getChallengeLike(int challengeBoardId, String nickName);

	boolean updateChallengeLike(boolean isLiked, int challengeBoardId, String nickName);

}
