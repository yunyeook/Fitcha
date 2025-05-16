<template>
  <div class="profile-wrapper">
    <!-- 프로필 정보 -->
    <div class="profile-info-wrapper">
      <div class="profile-update-wrapper">
        <!-- 상단 헤더 -->
        <div class="edit-header">
          <button class="back-btn">
            <i class="fa fa-arrow-left"></i>
          </button>
          <span class="header-title">프로필 수정</span>
          <button class="save-btn">저장</button>
        </div>

        <!-- 프로필 이미지 + 카메라 아이콘 -->
        <div class="edit-profile-img">
          <img src="../assets/images/run.jpg" class="profile-img" />
          <label class="camera-icon">
            <i class="fa fa-camera"></i>
            <input type="file" hidden />
          </label>
        </div>

        <!-- 사용자 이름 입력 -->
        <div class="form-group">
          <label for="username">닉네임</label>
          <input
            type="text"
            id="username"
            class="input"
            placeholder="2~10자 이내"
          />

          <!-- 관심 분야 선택 -->
          <label>취향</label>
          <p class="description">최대 4개까지 선택할 수 있습니다.</p>
          <div class="tags-select">
            <span class="tag">헬스</span>
            <span class="tag">요가</span>
            <span class="tag">홈트</span>
            <span class="tag">크로스핏</span>
            <span class="tag">필라테스</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 탭 메뉴 -->
    <div class="tab-menu">
      <button class="tab active">참여한 챌린지</button>
      <button class="tab">내 인증 게시글</button>
    </div>

    <!-- 콘텐츠 예시 -->
    <div class="tab-content">
      <!-- 더미 데이터 -->
      <div class="proof-card">
        <a style="text-decoration: none" href="../views/fitLogDetail.html">
          <!-- 헤더 작성자 정보 -->
          <div class="header">
            <img
              class="user-profile-image"
              src="../assets/images/user1.jpg"
              alt="작성자 프로필"
            />
            <div class="user-info">
              <span class="title">아침 5K 챌린지</span>
              <span class="user-name">러너러너러너</span>
            </div>
          </div>

          <!-- 운동 인증 이미지 -->
          <div class="main-image">
            <img src="../assets/images/run.jpg" alt="운동 인증 사진" />
          </div>

          <!-- 운동 정보 뱃지 -->
          <div class="badges">
            <span class="badge distance">러닝</span>
            <span class="badge time">서울</span>
            <span class="badge kcal">🔥 초보</span>
          </div>

          <!-- 내용 -->
          <div class="content">
            오늘도 미션 완료! 새벽 공기가 상쾌해서 오히려 좋았어요. 점점 몸이
            가벼워지는 느낌 💪
          </div>
          <div class="hashtag">#5일차성공 #아침러닝</div>

          <!-- 하단 푸터 -->
          <div class="footer">
            <div class="date">2025.05.13</div>
            <div class="heart">
              <i class="fas fa-heart"></i> <span>12</span>
            </div>
          </div>
        </a>
      </div>
      <div class="proof-card">
        <a style="text-decoration: none" href="../views/fitLogDetail.html">
          <!-- 헤더 작성자 정보 -->
          <div class="header">
            <img
              class="user-profile-image"
              src="../assets/images/user1.jpg"
              alt="작성자 프로필"
            />
            <div class="user-info">
              <span class="title">아침 5K 챌린지</span>
              <span class="user-name">러너러너러너</span>
            </div>
          </div>

          <!-- 운동 인증 이미지 -->
          <div class="main-image">
            <img src="../assets/images/run.jpg" alt="운동 인증 사진" />
          </div>

          <!-- 운동 정보 뱃지 -->
          <div class="badges">
            <span class="badge distance">러닝</span>
            <span class="badge time">서울</span>
            <span class="badge kcal">🔥 초보</span>
          </div>

          <!-- 내용 -->
          <div class="content">
            오늘도 미션 완료! 새벽 공기가 상쾌해서 오히려 좋았어요. 점점 몸이
            가벼워지는 느낌 💪
          </div>
          <div class="hashtag">#5일차성공 #아침러닝</div>

          <!-- 하단 푸터 -->
          <div class="footer">
            <div class="date">2025.05.13</div>
            <div class="heart">
              <i class="fas fa-heart"></i> <span>12</span>
            </div>
          </div>
        </a>
      </div>
      <div class="proof-card">
        <a style="text-decoration: none" href="../views/fitLogDetail.html">
          <!-- 헤더 작성자 정보 -->
          <div class="header">
            <img
              class="user-profile-image"
              src="../assets/images/user1.jpg"
              alt="작성자 프로필"
            />
            <div class="user-info">
              <span class="title">아침 5K 챌린지</span>
              <span class="user-name">러너러너러너</span>
            </div>
          </div>

          <!-- 운동 인증 이미지 -->
          <div class="main-image">
            <img src="../assets/images/run.jpg" alt="운동 인증 사진" />
          </div>

          <!-- 운동 정보 뱃지 -->
          <div class="badges">
            <span class="badge distance">러닝</span>
            <span class="badge time">서울</span>
            <span class="badge kcal">🔥 초보</span>
          </div>

          <!-- 내용 -->
          <div class="content">
            오늘도 미션 완료! 새벽 공기가 상쾌해서 오히려 좋았어요. 점점 몸이
            가벼워지는 느낌 💪
          </div>
          <div class="hashtag">#5일차성공 #아침러닝</div>

          <!-- 하단 푸터 -->
          <div class="footer">
            <div class="date">2025.05.13</div>
            <div class="heart">
              <i class="fas fa-heart"></i> <span>12</span>
            </div>
          </div>
        </a>
      </div>
      <div class="proof-card">
        <a style="text-decoration: none" href="../views/fitLogDetail.html">
          <!-- 헤더 작성자 정보 -->
          <div class="header">
            <img
              class="user-profile-image"
              src="../assets/images/user1.jpg"
              alt="작성자 프로필"
            />
            <div class="user-info">
              <span class="title">아침 5K 챌린지</span>
              <span class="user-name">러너러너러너</span>
            </div>
          </div>

          <!-- 운동 인증 이미지 -->
          <div class="main-image">
            <img src="../assets/images/run.jpg" alt="운동 인증 사진" />
          </div>

          <!-- 운동 정보 뱃지 -->
          <div class="badges">
            <span class="badge distance">러닝</span>
            <span class="badge time">서울</span>
            <span class="badge kcal">🔥 초보</span>
          </div>

          <!-- 내용 -->
          <div class="content">
            오늘도 미션 완료! 새벽 공기가 상쾌해서 오히려 좋았어요. 점점 몸이
            가벼워지는 느낌 💪
          </div>
          <div class="hashtag">#5일차성공 #아침러닝</div>

          <!-- 하단 푸터 -->
          <div class="footer">
            <div class="date">2025.05.13</div>
            <div class="heart">
              <i class="fas fa-heart"></i> <span>12</span>
            </div>
          </div>
        </a>
      </div>
      <div class="proof-card">
        <a style="text-decoration: none" href="../views/fitLogDetail.html">
          <!-- 헤더 작성자 정보 -->
          <div class="header">
            <img
              class="user-profile-image"
              src="../assets/images/user1.jpg"
              alt="작성자 프로필"
            />
            <div class="user-info">
              <span class="title">아침 5K 챌린지</span>
              <span class="user-name">러너러너러너</span>
            </div>
          </div>

          <!-- 운동 인증 이미지 -->
          <div class="main-image">
            <img src="../assets/images/run.jpg" alt="운동 인증 사진" />
          </div>

          <!-- 운동 정보 뱃지 -->
          <div class="badges">
            <span class="badge distance">러닝</span>
            <span class="badge time">서울</span>
            <span class="badge kcal">🔥 초보</span>
          </div>

          <!-- 내용 -->
          <div class="content">
            오늘도 미션 완료! 새벽 공기가 상쾌해서 오히려 좋았어요. 점점 몸이
            가벼워지는 느낌 💪
          </div>
          <div class="hashtag">#5일차성공 #아침러닝</div>

          <!-- 하단 푸터 -->
          <div class="footer">
            <div class="date">2025.05.13</div>
            <div class="heart">
              <i class="fas fa-heart"></i> <span>12</span>
            </div>
          </div>
        </a>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.profile-update-wrapper .edit-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  border-bottom: 1px solid #e0e0e0;
}

.profile-update-wrapper .back-btn {
  background: none;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  color: #3cb371;
}

.profile-update-wrapper .header-title {
  font-size: 1rem;
  font-weight: bold;
  color: #333;
}

.profile-update-wrapper .save-btn {
  background-color: #3cb371;
  border: none;
  border-radius: 12px;
  padding: 6px 16px;
  font-size: 0.9rem;
  color: white;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.profile-update-wrapper .save-btn:hover {
  background-color: #2ea15d;
}

.profile-update-wrapper .edit-profile-img {
  position: relative;
  text-align: center;
  margin-top: 24px;
}

.profile-update-wrapper .profile-img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.profile-update-wrapper .camera-icon {
  position: absolute;
  right: calc(50% - 45px);
  bottom: -4px;
  background-color: #3cb371;
  border-radius: 50%;
  padding: 6px;
  color: white;
  cursor: pointer;
  font-size: 0.8rem;
}

.profile-update-wrapper .form-group {
  padding: 16px 24px;
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.profile-update-wrapper .form-group label {
  margin-top: 10px;
  font-size: 0.9rem;
  color: #333;
  font-weight: 500;
}

.profile-update-wrapper .form-group .input {
  padding: 10px;
  font-size: 0.9rem;
  border: none;
  border-bottom: 1px solid #ccc;
  outline: none;
  background-color: transparent;
}

.profile-update-wrapper .form-group .description {
  font-size: 0.75rem;
  color: #888;
  margin-top: -4px;
  margin-bottom: 8px;
}

.profile-update-wrapper .tags-select {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.profile-update-wrapper .tags-select .tag {
  background-color: #eafaf1;
  border-radius: 16px;
  padding: 6px 12px;
  font-size: 0.8rem;
  color: #3cb371;
  cursor: pointer;
  border: 1px solid #cceedd;
  transition: background-color 0.2s ease;
}

.profile-update-wrapper .tags-select .tag:hover {
  background-color: #d4f3e3;
}
</style>
