package com.ssafy.fitcha.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Comment;
import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.SearchProof;
import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.CommentService;
import com.ssafy.fitcha.model.service.LikeService;
import com.ssafy.fitcha.model.service.ProofService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/proof")
@Tag(name = "Proof RESTful API", description = "챌린지 게시글의 인증 게시판 CRUD")
public class ProofController {

	// 생성자 의존성 주입
	private ProofService proofService;
	private CommentService commentService;
	private LikeService likeService;

	public ProofController(ProofService proofService, CommentService commentService, LikeService likeService) {
		this.proofService = proofService;
		this.commentService = commentService;
		this.likeService = likeService;
	}

	@Operation(summary = "인증글 게시글 검색 및 전체 목록 조회")
	@GetMapping
	public ResponseEntity<List<Proof>> getSearchProofs(@ModelAttribute SearchProof search) {
		List<Proof> proofList = null; // 인증글 전체 리스트
		try {
			proofList = proofService.getSearchProofs(search);
			if (proofList == null || proofList.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(proofList);

	}

	@Operation(summary = "인증글 게시글 상세 조회")
	@GetMapping("/{proofBoardId}")
	public ResponseEntity<Proof> getDetailProof(@PathVariable("proofBoardId") int proofBoardId) {
		Proof proof = proofService.getProofDetails(proofBoardId);
		if (proof == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(proof);
	}

	@Operation(summary = "인증글 게시글 등록")
	@PostMapping
	public ResponseEntity<Integer> registProof(
			@RequestParam(value = "files", required = false) List<MultipartFile> files, @ModelAttribute Proof proof)
			throws Exception {

		if (proofService.registProof(proof, files))
			return ResponseEntity.ok(proof.getProofBoardId());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@Operation(summary = "인증글 게시글 수정 ")
	@PutMapping("/{proofBoardId}")
	public ResponseEntity<Void> updateProof(@PathVariable("proofBoardId") int proofBoardId, @RequestBody Proof proof,
			@RequestParam(value = "files", required = false) List<MultipartFile> files, // 추가된 파일
			@RequestParam(value = "deleteProofFileIds", required = false) List<Integer> deleteProofFileIds // 삭제할 파일
	) throws Exception {
		proof.setProofBoardId(proofBoardId);
		boolean isUpdated = proofService.updateProof(proof, files, deleteProofFileIds);

		if (isUpdated) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	@Operation(summary = "인증글 게시글 삭제")
	@DeleteMapping("/{proofBoardId}")
	public ResponseEntity<Void> deleteProof(@PathVariable("proofBoardId") int proofBoardId) {
		if (proofService.deleteProofBoard(proofBoardId)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();

	}

	
	
	

	// ---댓글-------------------------------------------------------------------------------

	@Operation(summary = "인증글 게시글의 댓글 등록")
	@PostMapping("/{proofBoardId}/comment")
	public ResponseEntity<Void> registProofComment(@PathVariable("proofBoardId") int proofBoardId,
			@RequestBody Comment comment) {

		if (commentService.registProofComment(proofBoardId, comment)) {
			URI redirectUri = URI.create("/proof/" + proofBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@Operation(summary = "인증글 게시글의 댓글 삭제")
	@DeleteMapping("/{proofBoardId}/comment/{proofCommentId}")
	public ResponseEntity<Void> deleteProofComment(@PathVariable("proofBoardId") int proofBoardId,
			@PathVariable("proofCommentId") int proofCommentId) {
		if (commentService.deleteProofComment(proofBoardId, proofCommentId)) {
			URI redirectUri = URI.create("/proof/" + proofBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@Operation(summary = "인증글 게시글의 댓글 수정")
	@PutMapping("/{proofBoardId}/comment/{proofCommentId}")
	public ResponseEntity<Void> updateProofComment(@PathVariable("proofBoardId") int proofBoardId,
			@PathVariable("proofCommentId") int proofCommentId, @RequestBody Comment comment) {

		if (commentService.updateProofComment(proofBoardId, proofCommentId, comment)) {
			URI redirectUri = URI.create("/proof/" + proofBoardId);
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(redirectUri).build();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	// ----- 좋아요-----
	@Operation(summary = "인증 게시글 좋아요 갱신")
	@PostMapping("/{proofBoardId}/like")
	public ResponseEntity<Void> updateProofLike(@PathVariable("proofBoardId") int proofBoardId,
			@RequestParam("like") boolean isLiked, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if (likeService.updateProofLike(isLiked, proofBoardId, user.getNickName()))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
