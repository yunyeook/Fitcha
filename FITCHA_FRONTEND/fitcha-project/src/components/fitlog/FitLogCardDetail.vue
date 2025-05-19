<template>
  <div>
    <div class="proof-detail">
      <!-- 상단 작성자 정보 -->
      <div class="header">
        <div class="userAndTitle">
          <img
            class="user-profile-image"
            src="../assets/images/user1.jpg"
            alt="작성자 프로필"
          />
          <div class="user-info">
            <span class="title">{{ fitlog.title }}</span>
            <span class="user-name">{{ fitlog.writer }}</span>
          </div>
        </div>
        <div class="proof-menu" @click="openModal">
          <i class="fas fa-ellipsis-v"></i>
        </div>
      </div>

      <!-- 인증 이미지 -->
      <div class="proof-image">
        <img src="../../assets/images/run.jpg" alt="운동 인증" />
      </div>

      <!-- 운동 정보 뱃지 -->
      <div class="badges">
        <span class="badge distance">{{ fitlog.exerciseType }}</span>
        <span class="badge time">{{ fitlog.bodyPart }}</span>
        <span class="badge kcal">🔥 {{ fitlog.level }}</span>
      </div>

      <!-- 인증글 내용 -->
      <div class="proof-content">
        <p>
          {{ fitlog.content }}
        </p>

        <div class="content-bottom">
          <div class="hashtags">#5일차성공 #아침러닝 #챌린지인증</div>
          <a class="go-challenge" href="challengeFitDetail.html"
            >참여한 챌린지 보기 &rarr;</a
          >
        </div>
      </div>

      <!-- 하단 날짜 + 좋아요 -->
      <div class="footer">
        <div class="write-date">{{ fitlog.regDate }}</div>
        <div class="stats">
          <div class="views">
            <i class="fas fa-eye"></i>
            <span>{{ fitlog.viewCount }}</span>
          </div>
          <div class="like">
            <i class="fas fa-heart"></i>
            <span>{{ fitlog.likeCount }}</span>
          </div>
        </div>
      </div>

      <!-- 댓글 영역 -->
      <div class="comment-list">
        <div class="comment-card">
          <img
            class="comment-profile"
            src="../assets/images/user1.jpg"
            alt="프로필"
          />
          <div class="comment-body">
            <div class="comment-header">
              <span class="comment-author">사용자1</span>
              <div class="comment-menu" @click="openModal">
                <i class="fas fa-ellipsis-v"></i>
              </div>
            </div>
            <div class="comment-text">
              저도 참가할게요! 매일 아침 달리기 기대돼요.
            </div>
            <div class="comment-date">2025년 5월 5일</div>
          </div>
        </div>
      </div>
      <!-- 댓글 수정/삭제 모달 -->
      <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
        <div class="modal-box">
          <button class="modal-close-button" @click="closeModal">×</button>
          <div class="modal-title">댓글 관리</div>
          <button class="modal-button" @click="editComment">수정하기</button>
          <button class="modal-button delete" @click="deleteComment">
            삭제하기
          </button>
        </div>
      </div>
      <!-- 인증글 수정/삭제 모달 -->
      <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
        <div class="modal-box">
          <button class="modal-close-button" @click="closeModal">×</button>
          <div class="modal-title">인증글 관리</div>
          <button class="modal-button" @click="editProof">수정하기</button>
          <button class="modal-button delete" @click="deleteProof">
            삭제하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  fitlog: {
    type: Object,
  },
});

// 댓글 수정 삭제 모달
import { ref } from "vue";

const showModal = ref(false);

const openModal = () => {
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
};

const editComment = () => {
  alert("수정 기능은 여기에 구현하면 됨.");
  closeModal();
};

const deleteComment = () => {
  alert("삭제 기능은 여기에 구현.");
  closeModal();
};
const editProof = () => {
  alert("수정 기능은 여기에 구현하면 됨.");
  closeModal();
};

const deleteProof = () => {
  alert("삭제 기능은 여기에 구현.");
  closeModal();
};
</script>

<style scoped>
.proof-detail {
  max-width: 600px;
  margin: 20px auto 30px;
  padding: 12px 20px 24px;
  background-color: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  color: #333;
}

/* 작성자 정보 */
/* 작성자 정보 */
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px;
  gap: 10px;
}

.userAndTitle {
  display: flex;
  align-items: center;
  gap: 10px;
}

.user-profile-image {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
}

.user-info {
  display: flex;
  flex-direction: column;
}

.user-info .title {
  font-weight: bold;
  font-size: 1.3rem;
  color: #222;
}

.user-info .user-name {
  font-size: 1rem;
  color: #777;
  margin-top: 7px;
}

.proof-menu {
  font-size: 1.3rem;
  width: 20px;
  height: 30px;
  cursor: pointer;
  display: flex;
  align-items: start;
  justify-content: end;
}

/* 인증 이미지 */
.proof-image img {
  width: 100%;
  height: auto;
  border-radius: 12px;
  margin-bottom: 16px;
}

/* 운동 정보 뱃지 */
.badges {
  display: flex;
  gap: 8px;
  margin-bottom: 12px;
}

.badge {
  font-size: 0.75rem;
  padding: 2px 10px;
  border-radius: 12px;
  font-weight: 500;
  display: flex;
  align-items: center;
}

.badge.distance {
  background-color: #d3f9d8;
  color: #2b8a3e;
}

.badge.time {
  background-color: #d0ebff;
  color: #1c7ed6;
}

.badge.kcal {
  background-color: #ffe3e3;
  color: #e03131;
}

/* 인증글 본문 */
.proof-content p {
  font-size: 1rem;
  line-height: 1.6;
  color: #444;
  margin-bottom: 8px;
}

/* 해시태그 + 챌린지 링크 라인 */
.content-bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.hashtags {
  color: #3cb371;
  font-size: 0.8rem;
}

.go-challenge {
  font-size: 0.8rem;
  color: #3cb371;
  font-weight: bold;
  text-decoration: none;
}

.go-challenge:hover {
  text-decoration: underline;
  color: #2b8a3e;
}

/* 하단 날짜 + 좋아요 */
.footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top: 1px solid #eee;
  padding-top: 12px;
  font-size: 0.85rem;
  color: #666;
}

.write-date {
  font-size: 0.8rem;
  color: #888;
}

.stats {
  display: flex;
  align-items: center;
  gap: 12px;
}

.stats .like,
.stats .views {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1rem;
  gap: 7px;
}

.stats .like {
  color: #ff6b6b;
  cursor: pointer;
}

.stats .views {
  color: #868e96;
}
/* 댓글 섹션 */
.comments-section {
  margin-top: 32px;
  border-top: 1px solid #eee;
  padding-top: 20px;
}

/* 댓글 작성 폼 */
.comment-form {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}

.comment-form input {
  flex: 1;
  padding: 10px 12px;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-size: 0.9rem;
}

.comment-form button {
  padding: 10px 16px;
  background-color: #3cb371;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.comment-form button:hover {
  background-color: #2b8a3e;
}

/* 댓글 리스트 */
.comment-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 24px;
}

.comment-card {
  display: flex;
  background-color: #f9f9f9;
  padding: 12px 16px;
  border-radius: 12px;
  align-items: flex-start;
  position: relative;
}

.comment-profile {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 12px;
}

.comment-body {
  flex: 1;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.comment-author {
  font-weight: bold;
  font-size: 0.9rem;
  color: #222;
}

.comment-menu {
  color: #666;
  cursor: pointer;
  font-size: 1rem;
}

.comment-text {
  margin: 4px 0 6px;
  font-size: 0.88rem;
  color: #444;
  line-height: 1.4;
}

.comment-date {
  font-size: 0.75rem;
  color: #999;
}

/* 모달 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(4px);
  background-color: rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.modal-box {
  position: relative;
  background: #fff;
  width: 280px;
  padding: 24px 20px 20px;
  border-radius: 16px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  align-items: stretch;
  animation: fadeIn 0.25s ease;
}

.modal-title {
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 16px;
  text-align: center;
  color: #333;
}

.modal-button {
  font-size: 0.95rem;
  padding: 10px;
  margin: 6px 0;
  border-radius: 10px;
  border: none;
  cursor: pointer;
  background-color: #f1f3f5;
  color: #333;
  transition: background-color 0.2s;
}

.modal-button:hover {
  background-color: #e9ecef;
}

.modal-button.delete {
  background-color: #ffe3e3;
  color: #e03131;
}

.modal-button.delete:hover {
  background-color: #ffc9c9;
}

.modal-close-button {
  position: absolute;
  top: 12px;
  right: 14px;
  background: transparent;
  border: none;
  font-size: 1.2rem;
  color: #888;
  cursor: pointer;
  transition: color 0.2s;
}

.modal-close-button:hover {
  color: #222;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
