package com.ssafy.fitcha.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Proof;
import com.ssafy.fitcha.model.dto.Search;
import com.ssafy.fitcha.model.service.ProofService;

@RestController
@RequestMapping("/proof")
public class ProofController {

	// 생성자 의존성 주입
	private ProofService proofService;

	public ProofController(ProofService proofService) {
		this.proofService = proofService;
	}

	// 검색 목록 조회 ( 없을시 전체 조회 )
	@GetMapping
	public ResponseEntity<List<Proof>> getSearchProofs(@ModelAttribute Search search) {
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

	// 상세 조회
	@GetMapping("/{proofBoardId}")
	public ResponseEntity<Proof> getDetailProof(@PathVariable("proofBoardId") int proofBoardId) {
		Proof proof = proofService.getProofDetails(proofBoardId);
		if (proof == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(proof);
	}

	// 등록
	@PostMapping
	public ResponseEntity<Void> registProof(@RequestBody Proof proof) {
		if (proofService.registProof(proof)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	// 수정
	@PutMapping("/{proofBoardId}")
	public ResponseEntity<Void> updateProof(@PathVariable("proofBoardId") int proofBoardId, @RequestBody Proof proof) {
		proof.setProofBoardId(proofBoardId);
		if (proofService.updateProof(proof)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	// 삭제
	@DeleteMapping("/{proofBoardId}")
	public ResponseEntity<Void> deleteProof(@PathVariable("proofBoardId") int proofBoardId) {
		if (proofService.deleteProofBoard(proofBoardId)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();

	}

}
