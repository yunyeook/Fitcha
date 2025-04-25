package com.ssafy.fitcha.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.ChallengeDao;
import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Search;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	private ChallengeDao challengeDao;
	public ChallengeServiceImpl(ChallengeDao challengeDao) {
		this.challengeDao=challengeDao;
	}

	@Override
	public List<Challenge> getSearchChallenges(Search search) {
		List<Challenge> challengeBoardList = challengeDao.selectSearchChallengeBoardList(search);
		for(Challenge c : challengeBoardList) {
			int cbId = c.getChallengeBoardId();
			c.setFile(challengeDao.selectChallengeFileList(cbId));
		}
		return challengeBoardList;

	}

}
