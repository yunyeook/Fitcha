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
import com.ssafy.fitcha.model.service.ChallengeService;
import com.ssafy.fitcha.model.service.CommentService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	private ChallengeService challengeService;
	private CommentService commentService;

	public ChallengeController(ChallengeService challengeService, CommentService commentService) {
		this.challengeService = challengeService;
		this.commentService = commentService;
	}

	// 검색 목록 조회(검색어 없을 시 전체 조회).
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

	// 상세 조회
	@GetMapping("/{challengeBoardId}")
	public ResponseEntity<Challenge> getDetailChallenge(@PathVariable("challengeBoardId") int challengeBoardId) {
		Challenge challenge = challengeService.getChallengeDetail(challengeBoardId);
		if (challenge == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(challenge);
	}

	// 수정 (이후 상세화면으로 이동)
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

	// 삭제
	@DeleteMapping("/{challengeBoardId}/{writer}")
	public ResponseEntity<Void> deleteChallenge(@PathVariable("challengeBoardId") int challengeBoardId,
			@PathVariable("writer") String writer  ) {
		challengeService.deleteChallenge(challengeBoardId,writer);

		URI redirectUri = URI.create("/challenge");
		return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
	}

	// 등록
	@PostMapping
	public ResponseEntity<Void> registChallenge(
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			@ModelAttribute Challenge challenge) throws Exception {
		challengeService.registChallenge(challenge, files);
		URI redirectUri = URI.create("/challenge/" + challenge.getChallengeBoardId());
		return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();

	}

	// ---댓글-------------------------------------------------------------------------------

	// 챌린지 댓글 등록
	@PostMapping("/comment/{challengeBoardId}")
	public ResponseEntity<Void> registChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@RequestBody Comment comment) {

		if (commentService.registChallengeComment(challengeBoardId, comment)) {
			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// 챌린지 댓글 삭제
	@DeleteMapping("/comment/{challengeBoardId}/{challengeCommentId}")
	public ResponseEntity<Void> deleteChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@PathVariable("challengeCommentId") int challengeCommentId) {
		if (commentService.deleteChallengeComment(challengeCommentId)) {

			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// 챌린지 댓글 수정
	@PutMapping("/comment/{challengeBoardId}")
	public ResponseEntity<Void> updateChallengeComment(@PathVariable("challengeBoardId") int challengeBoardId,
			@RequestBody Comment comment) {
		if (commentService.updateChallengeComment(comment)) {
			URI redirectUri = URI.create("/challenge/" + challengeBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
} 