package com.ssafy.fitcha.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitcha.model.dao.FileDao;
import com.ssafy.fitcha.model.dto.ChallengeFile;

@Service
public class FileServiceImpl implements FileService {
	@Value("${challenge-file.upload.dirctory}")
	private String challengeFileUploadDir;
	private FileDao fileDao;
	private ResourceLoader resourceLoader;
	public FileServiceImpl(FileDao fileDao,ResourceLoader resourceLoader) {
		this.fileDao=fileDao;
		this.resourceLoader=resourceLoader;
	}

	//챌린지파일 목록 조회 
	@Override
	public List<ChallengeFile> getChallengeFileList(int challengeBoardId) {
		return fileDao.selectChallengeFileList(challengeBoardId);
	}

	//챌린지 파일 리스트 삭제 
	@Override
	public void deleteChallengeFile(List<Integer> deleteChallengeFileIds) {
		for(int challengeFileId : deleteChallengeFileIds) {
			fileDao.deleteChallengeFile(challengeFileId);
		}
		
	}
	
	//챌린지 파일 등록
	@Override
	public void insertChallengeFile(List<MultipartFile> files, int challengeBoardId, String writer) throws Exception {
		Resource resource = resourceLoader.getResource(challengeFileUploadDir);
		for(MultipartFile mfile : files) {
			if(!mfile.isEmpty()) {
				String originalFileName = mfile.getOriginalFilename();
				String uploadFileName = generateUniqueName(originalFileName);
				mfile.transferTo(new File(challengeFileUploadDir,uploadFileName));

				ChallengeFile challengeFile = new ChallengeFile();
				challengeFile.setChallengeBoardId(challengeBoardId);
				challengeFile.setFileOriginalName(originalFileName);
				challengeFile.setFileUploadName(uploadFileName);
				challengeFile.setFileUrl(challengeFileUploadDir+uploadFileName);
				challengeFile.setWriter(writer);
				fileDao.insertChallengeFile(challengeFile);
				
				
			}
		}
	}
	//파일의 중복 이름 저장방지 
	private String generateUniqueName(String originalName) {
		String timeStr = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String uniqueStr = UUID.randomUUID().toString().substring(0, 8);
		int index = originalName.lastIndexOf(".");
		String extName = "";
		if (index != -1) {
			extName = originalName.substring(index);
		}
		return timeStr + "_" + uniqueStr + extName;
	}

}
