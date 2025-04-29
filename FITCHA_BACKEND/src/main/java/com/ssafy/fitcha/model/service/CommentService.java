package com.ssafy.fitcha.model.service;

import java.util.List;

import com.ssafy.fitcha.model.dto.Comment;
import com.ssafy.fitcha.model.dto.User;

public interface CommentService {
	

	
	//챌린지 댓글 조회 
	List<Comment> getChallengeCommentList(int challengeBoardId);

	
	//챌린지 댓글 등록
	boolean registChallengeComment(int challengeBoardId, Comment comment);
	
	//챌린지 댓글 삭제
	boolean deleteChallengeComment(int challengeBoardId,int challengeCommentId);


	//챌린지 댓글 수정
	public boolean updateChallengeComment(int challengeBoardId,int challengeCommentId,Comment comment);
}
