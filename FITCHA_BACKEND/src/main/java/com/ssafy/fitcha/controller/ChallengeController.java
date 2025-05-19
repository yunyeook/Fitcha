package com.ssafy.fitcha.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Comment;
import com.ssafy.fitcha.model.dto.SearchChallenge;
import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.ChallengeService;
import com.ssafy.fitcha.model.service.CommentService;
import com.ssafy.fitcha.model.service.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/challenge")
@Tag(name="Challenge RESTfull API", description="운동 챌린지 게시판 CRUD ")
public class ChallengeController {
	private ChallengeService challengeService;
	private CommentService commentService;
	private LikeService likeService;

	public ChallengeController(ChallengeService challengeService, CommentService commentService,
			LikeService likeService) {
		this.challengeService = challengeService;
		this.commentService = commentService;
		this.likeService = likeService;
	}

	@Operation(summary = "챌린지 게시글 검색 및 전체 목록 조회")
	@GetMapping
	public ResponseEntity<List<Challenge>> getSearchChallenges(@ModelAttribute SearchChallenge search) {
		List<Challenge> challenges = null;
		try {
			challenges = challengeService.getSearchChallenges(search);
		
			if (challenges == null || challenges.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(challenges);
	}

//	@Operation(summary ="챌린지 게시글 상세 조회")
//	@GetMapping("/{challengeBoardId}")
//	public ResponseEntity<Challenge> getDetailChallenge(@PathVariable("challengeBoardId") int challengeBoardId,
//			@RequestParam("isViewCounted") boolean isViewCounted, HttpSession session) {
//		User user = (User) session.getAttribute("loginUser");
//		Challenge challenge = challengeService.getChallengeDetail(challengeBoardId, user.getNickName(), isViewCounted);
//
//		if (challenge == null)
//			return ResponseEntity.noContent().build();
//		return ResponseEntity.ok(challenge);
//	}
	
	// 로그인 기능 구현 전 vue test
	@Operation(summary ="챌린지 게시글 상세 조회")
	@GetMapping("/{challengeBoardId}")
	public ResponseEntity<Challenge> getDetailChallenge(@PathVariable("challengeBoardId") int challengeBoardId,
			@RequestParam("isViewCounted") boolean isViewCounted) {
		Challenge challenge = challengeService.getChallengeDetail(challengeBoardId,"길동이", isViewCounted);
		if (challenge == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(challenge);
	}


	@Operation(summary ="챌린지 게시글 수정 " , description="게시글 수정 후 상세화면으로 이동")
	@PutMapping("/{challengeBoardId}")
	public ResponseEntity<Void> updateChallenge(@PathVariable("challengeBoardId") int challengeBoardId,
			@ModelAttribute Challenge challenge,
			@RequestParam(value = "files", required = false) List<MultipartFile> files, // 추가된 파일
			@RequestParam(value = "deleteChallengeFileIds", required = false) List<Integer> deleteChallengeFileIds // 삭제할
																													// 파일
	) throws Exception {

		challenge.setChallengeBoardId(challengeBoardId);
		challengeService.updateChallenge(challenge, files, deleteChallengeFileIds);

		URI redirectUri = URI.create("/challenge/" + challengeBoardId);
		return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
	}


	@Operation(summary ="챌린지 게시글 삭제")
	@DeleteMapping("/{challengeBoardId}/{writer}")
	public ResponseEntity<Void> deleteChallenge(@PathVariable("challengeBoardId") int challengeBoardId,
			@PathVariable("writer") String writer) {
		challengeService.deleteChallenge(challengeBoardId, writer);

		URI redirectUri = URI.create("/challenge");
		return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
	}


	@Operation(summary ="챌린지 게시글 등록")
	@PostMapping
	public ResponseEntity<Void> registChallenge(
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			@ModelAttribute Challenge challenge) throws Exception {
		challengeService.registChallenge(challenge, files);
		URI redirectUri = URI.create("/challenge/" + challenge.getChallengeBoardId());
		return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();

	}

	// -------- 댓 글 ------------


	@Operation(summary ="챌린지 게시글에 댓글 등록")
	@PostMapping("/{challengeBoardId}/comment")
	public ResponseEntity<Void> registChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@RequestBody Comment comment) {

		if (commentService.registChallengeComment(challengeBoardId, comment)) {
			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@Operation(summary ="챌린지 게시글의 댓글 삭제")
	@DeleteMapping("{challengeBoardId}/comment/{challengeCommentId}")
	public ResponseEntity<Void> deleteChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@PathVariable("challengeCommentId") int challengeCommentId) {
		System.out.println(challengeCommentId);

		if (commentService.deleteChallengeComment(challengeBoardId, challengeCommentId)) {

			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}


	@Operation(summary ="챌린지 게시글의 댓글 수정")
	@PutMapping("/{challengeBoardId}/comment/{challengeCommentId}")
	public ResponseEntity<Void> updateChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@PathVariable("challengeCommentId") int challengeCommentId, @RequestBody Comment comment) {

		if (commentService.updateChallengeComment(challengeBoardId, challengeCommentId, comment)) {
			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// ----- 좋아요-----

	@Operation(summary ="챌린지 게시글 좋아요 갱신")
	@PostMapping("/{challengeBoardId}/like")
	public ResponseEntity<Void> updateChallengeLike(@PathVariable("challengeBoardId") int challengeBoardId,
			@RequestParam("isLiked") boolean isLiked, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if (likeService.updateChallengeLike(isLiked, challengeBoardId, user.getNickName()))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}