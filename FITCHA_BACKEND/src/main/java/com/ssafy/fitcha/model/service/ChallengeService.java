package com.ssafy.fitcha.model.service;

import java.util.List;

import com.ssafy.fitcha.model.dto.Challenge;
import com.ssafy.fitcha.model.dto.Search;

public interface ChallengeService {

	List<Challenge> getSearchChallenges(Search search);

}
