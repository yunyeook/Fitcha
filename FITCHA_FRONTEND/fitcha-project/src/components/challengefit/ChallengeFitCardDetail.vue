<template>
  <div class="challenge-detail__container">
    <img :src="imgUrl" alt="러닝 이미지" class="challenge-detail__image" />

    <div class="challenge-detail__content">
      <div class="challenge-detail__header">
        <h2>{{ challenge.title }}</h2>
        <div class="challenge-detail__options" @click="openChallengeFitModal">
          <i class="fas fa-ellipsis-v"></i>
        </div>
      </div>

      <div class="challenge-detail__badges">
        <span class="challenge-detail__badge running">
          <i class="fas fa-person-running"></i>
          {{ challenge.exerciseType }}
        </span>
        <span class="challenge-detail__badge location">
          <i class="fas fa-map-marker-alt"></i>
          {{ challenge.bodyPart }}
        </span>

        <span class="challenge-detail__badge hard">
          <i class="fas fa-star"></i>
          {{ challenge.level }}
        </span>
      </div>

      <p class="challenge-detail__desc">{{ challenge.content }}</p>

      <div class="challenge-detail__progress">
        <div class="challenge-detail__progress-bar">
          <div class="challenge-detail__progress-fill" style="width: 50%"></div>
        </div>
        <p class="challenge-detail__participants">
          참여:{{ challenge.participantCount }} /
          {{ challenge.totalParticipantCount }}명
        </p>
      </div>

      <div class="challenge-detail__avatars-section">
        <div class="challenge-detail__host">
          <img src="https://via.placeholder.com/32" alt="Host" />
          <span class="challenge-detail__host-badge">방장</span>
        </div>
        <div class="challenge-detail__avatar-stack">
          <img src="https://via.placeholder.com/32/FF5733" />
          <img src="https://via.placeholder.com/32/33C3FF" />
          <img src="https://via.placeholder.com/32/85FF33" />
          <img src="https://via.placeholder.com/32/FF33A6" />
          <img src="https://via.placeholder.com/32/FFD433" />
          <span class="challenge-detail__more-count">+5</span>
        </div>
      </div>

      <!-- 현재 참여중인경우 -->
      <template v-if="challenge.participated">
        <div class="challenge-detail__actions">
          <button class="challenge-detail__join-btn">
            <i class="fas fa-sign-in-alt"></i>
            참여중
          </button>
          <button class="challenge-detail__certify-btn">
            <router-link
              :to="{
                name: 'FitLogRegistView',
                params: { challengeBoardId: challenge.challengeBoardId },
              }"
              style="text-decoration: none"
            >
              <i class="fas fa-pen"></i>
              인증글 쓰기
            </router-link>
          </button>
        </div>
      </template>

      <!-- 현재 참여중이 아닌경우 -->
      <template v-else>
        <template
          v-if="challenge.participantCount < challenge.totalParticipantCount"
        >
          <div class="challenge-detail__actions">
            <button
              class="challenge-detail__join-btn"
              @click="requestChallengeParticipate"
            >
              <i class="fas fa-sign-in-alt"></i>
              참여하기
            </button>
          </div>
        </template>
        <template v-else>
          <div class="challenge-detail__actions">
            <button class="challenge-detail__join-btn">
              <i class="fas fa-sign-in-alt"></i>
              정원초과
            </button>
          </div>
        </template>
      </template>

      <div class="challenge-detail__meta">
        <span>{{ challenge.regDate }}</span>
        <div class="challenge-detail__meta-right">
          <!-- <span>댓글 {{ commentsCount }}개</span> -->
          <span class="challenge-detail__likes">
            <i class="fas fa-heart"></i>
            {{ challenge.likeCount }}명
          </span>
        </div>
      </div>

      <!-- 탭 섹션 시작 -->
      <div class="challenge-detail__tab-section">
        <div class="challenge-detail__tabs">
          <div class="challenge-detail__tab active" data-tab="comments">
            댓글 보기
          </div>
          <div class="challenge-detail__tab" data-tab="certs">인증글 보기</div>
        </div>

        <!-- 댓글 탭 -->
        <div class="challenge-detail__tab-content active" id="comments">
          <div class="challenge-detail__comment-form">
            <input
              type="text"
              placeholder="댓글을 남기세요..."
              v-model="comment"
            />
            <button @click="requestChallengeCommentRegist">작성</button>
          </div>
          <div
            class="challenge-detail__comment"
            v-for="comment in challenge.comments"
            :key="comment.commentId"
          >
            <img src="https://via.placeholder.com/36/FF5733" />
            <div class="challenge-detail__comment-body">
              <div>
                <div class="challenge-detail__comment-author">
                  {{ comment.writer }}
                </div>
                <div class="challenge-detail__comment-text">
                  {{ comment.content }}
                </div>
                <div class="challenge-detail__comment-date">
                  {{ comment.regDate }}
                </div>
              </div>

              <!--'길동이' -> 세션에서 사용자 닉네임가져오기-->
              <!-- <template v-if="comment.writer === '길동이'"> -->
              <div class="challenge-detail__options" @click="openCommentModal">
                <i class="fas fa-ellipsis-v"></i>
              </div>
              <!-- </template> -->
            </div>
          </div>
        </div>

        <!-- 인증글 탭 -->
        <div class="challenge-detail__tab-content" id="certs">
          <div class="challenge-detail__certification-item">
            <div class="challenge-detail__cert-header">
              <div class="challenge-detail__cert-author">
                <img src="https://via.placeholder.com/32/FF5733" />
                <span class="author">러너1</span>
              </div>
              <span class="date">5월 10일</span>
            </div>
            <div class="challenge-detail__cert-body">
              오늘도 5km 완주했어요! 상쾌한 하루 시작 💪
            </div>
          </div>
        </div>
      </div>

      <a href="#" class="challenge-detail__back">
        <i class="fas fa-arrow-left"></i>
        뒤로 가기
      </a>
    </div>
  </div>

  <!-- 댓글 수정/삭제 모달 -->
  <div
    v-if="showCommentModal"
    class="modal-overlay"
    @click.self="closeCommentModal"
  >
    <div class="modal-box">
      <button class="modal-close-button" @click="closeCommentModal">×</button>
      <div class="modal-title">댓글 관리</div>
      <button class="modal-button" @click="editComment">수정하기</button>
      <button class="modal-button delete" @click="deleteComment">
        삭제하기
      </button>
    </div>
  </div>
  <!--  수정/삭제 모달 -->
  <div
    v-if="showChallengeFitModal"
    class="modal-overlay"
    @click.self="closeChallengeFitModal"
  >
    <div class="modal-box">
      <button class="modal-close-button" @click="closeChallengeFitModal">
        ×
      </button>
      <div class="modal-title">첼린지 관리</div>
      <button class="modal-button" @click="editChallengeFit">수정하기</button>
      <button class="modal-button delete" @click="deleteChallengeFit">
        삭제하기
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
import api from "@/api/api";

const BASE_URL = "http://localhost:8080/challenge";
const IMG_BASE_URL = "http://localhost:8080/";
const imgUrl = ref("");
const route = useRoute();
const router = useRouter();
const isViewCounted = ref(route.query.isViewCounted);
const challengeBoardId = ref(route.params.id);
const challenge = ref({});
const updateCommwzentId = ref(-1);
const token = localStorage.getItem("access-token");

const commentsCount = computed(() => {
  const comments = challenge.value.comments;
  return Array.isArray(comments) ? comments.length : 0;
});

async function requestChallengeDetail() {
  const { data } = await api.get(`/challenge/${challengeBoardId.value}`, {
    params: {
      // user:
      isViewCounted: isViewCounted.value,
    },
  });
  challenge.value = data;
  imgUrl.value = IMG_BASE_URL + data.challengeFiles[0].fileUploadName;
  isViewCounted.value = !isViewCounted.value;
}
requestChallengeDetail();

const comment = ref("");

//댓글등록.
async function requestChallengeCommentRegist() {
  const { status } = await api.post(
    `/challenge/${challengeBoardId.value}/comment`,
    {
      boardId: challengeBoardId.value,
      userId: "fituser1", // 세션에서 가져오기
      content: comment.value,
      writer: "길동이", //세션에서 가져오기
    }
  );
  comment.value = "";
  //성공시 다시 전체 댓글 목록 불러오기
  if (status === axios.HttpStatusCode.Created) {
    const { data } = await api.get(
      `/challenge/${challengeBoardId.value}/comment`
    );
    challenge.value.comments = data;
    //실패시
  } else {
    //작성하기
  }
}

async function requestChallengeParticipate() {
  const { status } = await api.post(
    `/challenge/${challengeBoardId.value}/participate`,
    {
      boardId: challengeBoardId.value,
      writer: "길동이", //세션에서 가져오기
    }
  );
  if (status === axios.HttpStatusCode.Ok) {
    challenge.value.participated = true;
    //실패시
  } else {
    //
  }
}
const props = defineProps({ challenge: Object });
// const commentsCount = ref(props.challenge.comments.length);

const showCommentModal = ref(false);
const showChallengeFitModal = ref(false);

const openChallengeFitModal = () => {
  showChallengeFitModal.value = true;
};

const closeChallengeFitModal = () => {
  showChallengeFitModal.value = false;
};

const openCommentModal = () => {
  showCommentModal.value = true;
};

const closeCommentModal = () => {
  showCommentModal.value = false;
};

const editComment = () => {
  alert("수정 기능은 여기에 구현하면 됨.");
  closeModal();
};

const deleteComment = () => {
  alert("삭제 기능은 여기에 구현.");
  closeModal();
};
const editChallengeFit = () => {
  alert("수정 기능은 여기에 구현하면 됨.");
  closeModal();
};

const deleteChallengeFit = () => {
  alert("삭제 기능은 여기에 구현.");
  closeModal();
};
</script>

<style scoped>
/* 챌린지 카드 디테일 디자인 */

.challenge-detail__container {
  max-width: 640px;
  margin: 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

.challenge-detail__image {
  width: 100%;
  height: 330px;
  object-fit: cover;
}

.challenge-detail__content {
  padding: 20px;
}

.challenge-detail__header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.3rem;
  font-weight: 500;
}
.challenge-detail__header h2 {
  margin: 0px;
  font-size: 1.3rem;
}

.challenge-detail__options {
  cursor: pointer;
  width: 20px;
  display: flex;
  justify-content: center;
}

.challenge-detail__badges {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin: 12px 0;
}

.challenge-detail__badge {
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 12px;
  color: #ffffff;
}

.challenge-detail__badge.running {
  background-color: #3cb371;
}
.challenge-detail__badge.location {
  background-color: #4dabf7;
}
.challenge-detail__badge.hard {
  background-color: #ffa94d;
}

.challenge-detail__desc {
  margin-top: 20px;
  margin-bottom: 16px;
  line-height: 1.5;
  font-size: 1rem;
  color: #495057;
}

.challenge-detail__progress {
  margin: 30px 0 16px;
}

.challenge-detail__progress-bar {
  background: #e0e0e0;
  height: 8px;
  border-radius: 4px;
  overflow: hidden;
}

.challenge-detail__progress-fill {
  height: 100%;
  background: #4caf50;
}

.challenge-detail__participants {
  font-size: 14px;
  color: #555;
  margin-top: 6px;
}

/* 아바타 */
.challenge-detail__avatars-section {
  display: flex;
  gap: 15px;
  align-items: center;
  margin-top: 18px;
}

.challenge-detail__host {
  display: flex;
  align-items: center;
  gap: 6px;
  background-color: #e3fafc;
  padding: 4px 10px;
  border-radius: 20px;
}

.challenge-detail__host img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 2px solid #0c8599;
}

.challenge-detail__host-badge {
  background-color: #0c8599;
  color: #fff;
  font-size: 11px;
  font-weight: 600;
  padding: 3px 6px;
  border-radius: 12px;
}

.challenge-detail__avatar-stack {
  display: flex;
  position: relative;
}

.challenge-detail__avatar-stack img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 2px solid #fff;
  margin-left: -10px;
  background-color: #eee;
  box-shadow: 0 0 0 1px #ccc;
}

.challenge-detail__avatar-stack img:first-child {
  margin-left: 0;
}

.challenge-detail__more-count {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: #dee2e6;
  color: #495057;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 6px;
  font-size: 13px;
  font-weight: bold;
}

/* 버튼 */
.challenge-detail__actions {
  display: flex;
  gap: 12px;
  margin: 16px 0;
}

.challenge-detail__actions button {
  flex: 1;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  border: none;
  border-radius: 10px;
  color: #fff;
  cursor: pointer;
}

.challenge-detail__join-btn {
  background: #51cf66;
}

.challenge-detail__join-btn:hover {
  background: #40c057;
}

.challenge-detail__certify-btn {
  background: #339af0;
}

.challenge-detail__certify-btn:hover {
  background: #228be6;
}

.challenge-detail__certify-btn a {
  color: #ffffff;
}

/* 메타 정보 */
.challenge-detail__meta {
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  color: #777;
}

.challenge-detail__likes {
  margin-left: 12px;
  color: #e03131;
}

/* 탭 섹션 */
.challenge-detail__tab-section {
  background: #f9f9f9;
  border-radius: 12px;
  border: 1px solid #e5e5e5;
  margin-top: 24px;
  overflow: hidden;
}

.challenge-detail__tabs {
  display: flex;
  background: #f1f3f5;
  border-bottom: 1px solid #ddd;
}

.challenge-detail__tab {
  flex: 1;
  padding: 12px;
  text-align: center;
  cursor: pointer;
  font-weight: bold;
  color: #666;
  transition: all 0.2s ease;
}

.challenge-detail__tab:hover {
  background: #e7f5ff;
  color: #1c7ed6;
}

.challenge-detail__tab.active {
  background: #fff;
  color: #1c7ed6;
  border-bottom: 3px solid #1c7ed6;
}

.challenge-detail__tab-content {
  display: none;
  padding: 20px;
  background: #fff;
}

.challenge-detail__tab-content.active {
  display: block;
}

/* 댓글 영역 */
.challenge-detail__comment-form {
  display: flex;
  gap: 8px;
  margin-bottom: 20px;
}

.challenge-detail__comment-form input {
  flex: 1;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid #ccc;
}

.challenge-detail__comment-form button {
  padding: 10px 16px;
  background: #51cf66;
  color: #fff;
  font-weight: bold;
  border-radius: 8px;
  border: none;
  cursor: pointer;
}

.challenge-detail__comment {
  display: flex;
  gap: 10px;
  margin-bottom: 16px;
}

.challenge-detail__comment img {
  width: 36px;
  height: 36px;
  border-radius: 50%;
}

.challenge-detail__comment-body {
  flex: 1;
  display: flex;
  justify-content: space-between;
}

.challenge-detail__comment-author {
  font-size: 0.9rem;
  margin-bottom: 4px;
}

.challenge-detail__comment-text {
  margin-bottom: 4px;
  font-size: 0.8rem;
  color: #444;
}

.challenge-detail__comment-date {
  font-size: 12px;
  color: #888;
}

/* 인증글 */
.challenge-detail__certification-item {
  background: #fff;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 14px;
  margin-bottom: 16px;
}

.challenge-detail__cert-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.challenge-detail__cert-author {
  display: flex;
  align-items: center;
  gap: 6px;
}

.challenge-detail__cert-author img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

.challenge-detail__cert-body {
  font-size: 14px;
  line-height: 1.5;
}

/* 뒤로가기 */
.challenge-detail__back {
  display: inline-flex;
  align-items: center;
  margin-top: 24px;
  text-decoration: none;
  color: #444;
  gap: 6px;
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
