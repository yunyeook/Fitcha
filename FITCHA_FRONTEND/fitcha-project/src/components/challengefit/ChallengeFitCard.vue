<template>
  <div>
    <h2>ChallengeSearch.vue</h2>
    <!-- main-content 안에 들어감-->

    챌린지 게시글 카드 구조
    <div class="challenge-card">
      <!-- 챌린지 카드 이미지 -->
      <img src="../assets/images/run.jpg" alt="" />
      <!-- 챌린지 카드 내용 -->
      <div class="card-content">
        <h3>{{ challenge.title }}!</h3>
        <div class="card-badges">
          <span class="badge activity">
            <i class="fas fa-running"></i>
            {{ challenge.exerciseType }}
          </span>
          <span class="badge location">
            <i class="fas fa-map-marker-alt"></i>
            {{ challenge.bodyPart }}
          </span>
          <span class="badge level">
            <i class="fas fa-star"></i>
            {{ challenge.level }}
          </span>
        </div>
        <!-- 챌린지 게시글 내용 -->
        <p class="card-desc">{{ challenge.content }}</p>
        <!-- 참여 인원 프로그래스바 -->
        <div class="progress-container">
          <div class="progress-bar">
            <div class="fill"></div>
          </div>
        </div>

        <p class="participants">참여:{{ challenge.participantCount }} / {{ challenge.totalParticipantCount }}명</p>
        <p class="date">
          {{ challenge.regDate }}
          <span class="comment-count">2</span>
          개의 댓글
        </p>
        <div class="card-footer">
          <div class="writer-info">
            <img src="../assets/images/run.jpg" alt="" />
            <span>{{ challenge.writer }}</span>
          </div>
          <div class="like">
            <i class="fas fa-heart"></i>
            <span>{{ challenge.likeCount }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({ challenge: { type: Object } });
</script>

<style lang="scss" scoped>
.challenge-card {
  font-family: 'Arial', sans-serif;
  background-color: #ffffff;
  width: 240px;
  height: 400px;
  border-radius: 15px;
  overflow: hidden;
  transition: all 0.3s ease;
  cursor: pointer;
}

.challenge-card:hover {
  transform: translateY(-4px);
  box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.15);
}

.challenge-card img {
  width: 100%;
  height: 160px;
  object-fit: cover;
}

.challenge-card .card-content {
  padding: 12px 16px;
}

.card-content h3 {
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #222;
}

.card-content .card-badges {
  margin-bottom: 12px;
}

.card-badges .badge {
  display: inline-block;
  color: #ffffff;
  padding: 3px 8px;
  border-radius: 5px;
  font-size: 0.65rem;
}

.badge.activity {
  background-color: #3cb371; /* 활동 */
}

.badge.location {
  background-color: #4dabf7; /* 지역 */
}

.badge.level {
  background-color: #ffa94d; /* 난이도 */
}

.badge i {
  margin-right: 4px;
}

.card-desc {
  font-size: 0.8rem;
  color: #555;
  margin-bottom: 10px;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.progress-container {
  margin-bottom: 8px;
}

.progress-container .progress-bar {
  height: 8px;
  background-color: #eee;
  border-radius: 5px;
  overflow: hidden;
}

.progress-bar .fill {
  height: 100%;
  background-color: #3cb371;
  width: 50%;
  transition: width 0.3s;
}

.participants {
  font-size: 0.75rem;
  color: #444;
  margin-bottom: 8px;
}

.date {
  font-size: 0.7rem;
  color: #888;
  margin-bottom: 12px;
}

.card-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 8px 14px;
  border-top: 1px solid #eee;
  font-size: 0.7rem;
  color: #666;
}

.card-footer .writer-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.card-footer .writer-info img {
  width: 24px;
  height: 24px;
  border-radius: 50%;
}

.card-footer .like {
  display: flex;
  align-items: center;
  gap: 4px;
}

.like i {
  color: #ff6b6b;
}
</style>
