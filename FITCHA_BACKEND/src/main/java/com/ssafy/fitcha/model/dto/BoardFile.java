package com.ssafy.fitcha.model.dto;

public class BoardFile {

	
	// 생성자
	public BoardFile() {
	}
	
	// 필드
	private int proofFileId; // 인증글 파일 식별 id
	private int proofBoardId; // 인증글 id 
	private String fileUploadName; // 파일 업로드 네임
	private String fileOriginalName; // 원래 파일 네임
	private String fileUrl; // 파일 url
	private String writer; // 작성자 필요한가?
	
	// getter / setter
	public int getProofFileId() {
		return proofFileId;
	}
	public void setProofFileId(int proofFileId) {
		this.proofFileId = proofFileId;
	}
	public int getProofBoardId() {
		return proofBoardId;
	}
	public void setProofBoardId(int proofBoardId) {
		this.proofBoardId = proofBoardId;
	}
	public String getFileUploadName() {
		return fileUploadName;
	}
	public void setFileUploadName(String fileUploadName) {
		this.fileUploadName = fileUploadName;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "BoardFile [proofFileId=" + proofFileId + ", proofBoardId=" + proofBoardId + ", fileUploadName="
				+ fileUploadName + ", fileOriginalName=" + fileOriginalName + ", fileUrl=" + fileUrl + ", writer="
				+ writer + "]";
	}
	
	
	
	
	
	
	
	

}
