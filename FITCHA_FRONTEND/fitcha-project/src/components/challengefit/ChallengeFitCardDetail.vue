<template>
  <div class="challenge-detail__container">
    <img :src="imgUrl" alt="러닝 이미지" class="challenge-detail__image" />

    <div class="challenge-detail__content">
      <div class="challenge-detail__header">
        <h2>{{ challenge.title }}</h2>

        <template v-if="challenge.writer === nickName">
          <div class="challenge-detail__options" @click="openChallengeFitModal">
            <i class="fas fa-ellipsis-v"></i>
          </div>
        </template>
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

      <!-- 프로그레스 바  -->
      <div class="challenge-detail__progress">
        <div class="challenge-detail__progress-bar">
          <div
            class="challenge-detail__progress-fill"
            :style="{
              width: progressWidth,
              background: challenge.finish ? '#7e7e7e' : '#4caf50',
            }"
          >
            <span class="challenge-detail__progress-text">
              {{ challenge.finish ? "기간만료" : daysText }}
            </span>
          </div>
        </div>

        <p class="challenge-detail__participants">
          참여 : {{ challenge.participantCount }} /
          {{ challenge.totalParticipantCount }}명
        </p>
      </div>

      <div class="avatars-like-container">
        <div class="challenge-detail__avatars-section">
          <div class="challenge-detail__host">
            <img src="https://via.placeholder.com/32" alt="Host" />
            <span class="challenge-detail__host-badge">{{
              challenge.writer
            }}</span>
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
        <div class="like-btn">
          <i
            class="fa-heart fa-2x"
            :class="isLike == 1 ? 'fas' : 'far'"
            :style="isLike == 1 ? { color: '#ff6b6b' } : { color: '#ccc' }"
            @click="updateLike"
          ></i>
        </div>
      </div>

      <!-- 종료된 경우  -->
      <template v-if="challenge.finish">
        <div class="challenge-detail__actions">
          <button class="challenge-detail__limit-over-btn">챌린지 종료</button>
        </div>
      </template>

      <!-- 종료되지 않은 경우  -->
      <template v-else>
        <!-- 현재 참여중인경우 -->
        <template v-if="challenge.participated">
          <div class="challenge-detail__actions">
            <button
              class="challenge-detail__join-btn"
              @click="deleteChallengeParticipate"
              v-if="nickName != challenge.writer"
            >
              <i class="fas fa-sign-in-alt"></i>
              참여 취소
            </button>
            <button class="challenge-detail__certify-btn">
              <router-link
                :to="{
                  name: 'FitLogRegistView',
                  params: {
                    challengeBoardId: challenge.challengeBoardId,
                    writer: challenge.writer,
                  },
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
              <button class="challenge-detail__limit-over-btn">정원초과</button>
            </div>
          </template>
        </template>
      </template>

      <div class="challenge-detail__meta">
        <span>{{ challenge.regDate }}</span>
        <div class="challenge-detail__meta-right">
          <span>댓글 {{ comments?.length || 0 }}개</span>
          <span class="challenge-detail__likes">
            <i class="fas fa-heart"></i>
            {{ likeCount }}명
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
            v-for="comment in comments"
            :key="comment.challengeCommentId"
          >
            <img src="https://via.placeholder.com/36/FF5733" />
            <div class="challenge-detail__comment-body">
              <div>
                <div class="challenge-detail__comment-author">
                  {{ comment.writer }}
                </div>

                <template
                  v-if="editChallengeCommentId !== comment.challengeCommentId"
                >
                  <div class="challenge-detail__comment-text">
                    {{ comment.content }}
                  </div>
                </template>
                <template v-else>
                  <div class="challenge-detail__comment-form">
                    <input type="text" v-model="editCommentContent" />
                    <button
                      @click="
                        requestChallengeCommentUpdate(editChallengeCommentId)
                      "
                    >
                      수정완료
                    </button>
                  </div>
                </template>

                <div class="challenge-detail__comment-date">
                  {{ comment.regDate }}
                </div>
              </div>

              <template v-if="comment.writer === nickName">
                <div
                  class="challenge-detail__options"
                  @click="
                    openCommentModal(
                      comment.challengeCommentId,
                      comment.content
                    )
                  "
                >
                  <i class="fas fa-ellipsis-v"></i>
                </div>
              </template>
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

      <a class="challenge-detail__back" @click="goBack">
        <i class="fas fa-arrow-left"></i>
        뒤로 가기
      </a>
    </div>
  </div>

  <!-- 댓글 수정/삭제 모달 -->
  <div
    v-if="showCommentModal"
    class="modal-overlay"
    @click.self="closeCommentModal(false)"
  >
    <div class="modal-box">
      <button class="modal-close-button" @click="closeCommentModal(false)">
        ×
      </button>
      <div class="modal-title">댓글 관리</div>
      <button class="modal-button" @click="closeCommentModal(true)">
        수정하기
      </button>
      <button class="modal-button delete" @click="requestDeleteComment">
        삭제하기
      </button>
    </div>
  </div>

  <!-- 챌린지 수정/삭제 모달 -->
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
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import api, { BASE_URL } from "@/api/api";
import axios from "axios";
import { useUserStore } from "@/stores/user";
import dayjs from "dayjs";

const { userId, nickName } = useUserStore();

const imgUrl = ref("");
const route = useRoute();
const router = useRouter();
const isViewCounted = ref(route.query.isViewCounted);
const challengeBoardId = ref(route.params.id);
const challenge = ref({});

// 1) 경과 일수 (0~duration)
const daysElapsed = computed(() => {
  if (!challenge.value.regDate || !challenge.value.duration) return 0;
  const start = dayjs(challenge.value.regDate);
  const diff = dayjs().diff(start, "day");
  if (diff < 0) return 0;
  if (diff > challenge.value.duration) return challenge.value.duration;
  return diff;
});

// 2) “몇 일차” 계산 (경과일 + 1, 최대 duration)
const dayCount = computed(() => {
  const cnt = daysElapsed.value + 1;
  return cnt > challenge.value.duration ? challenge.value.duration : cnt;
});

// 3) 프로그레스바 너비 (dayCount / duration)
const progressWidth = computed(() => {
  if (!challenge.value.duration) return "0%";
  const pct = Math.round((dayCount.value / challenge.value.duration) * 100);
  return pct + "%";
});

// 4) 화면에 표시할 텍스트 (예: “1일차”, “2일차”)
const daysText = computed(() => {
  return dayCount.value + "일차";
});

onMounted(async () => {
  try {
    await requestChallengeDetail();
    await requestChallengeLike();
  } catch (e) {
    console.error("영상 요청 실패", e);
  }
});

// 챌린지글 조회
async function requestChallengeDetail() {
  const { data } = await api.get(`/challenge/${challengeBoardId.value}`, {
    params: {
      isViewCounted: isViewCounted.value,
      writer: nickName,
    },
  });
  challenge.value = data;
  imgUrl.value = `${BASE_URL}/${data.challengeFiles[0].fileUploadName}`;
  isViewCounted.value = "false";
  comments.value = data.comments;
  // console.log(challenge.value);
}

//챌린지 참여 등록.
async function requestChallengeParticipate() {
  const { status } = await api.post(
    `/challenge/${challengeBoardId.value}/participate`,
    {
      challengeBoardId: challengeBoardId.value,
      writer: nickName,
    }
  );
  if (status === axios.HttpStatusCode.Ok) {
    await requestChallengeDetail();
    //실패시
  } else {
    //
  }
}
//챌린지 참여 취소
async function deleteChallengeParticipate() {
  await api.delete(
    `/challenge/${challengeBoardId.value}/participate/${nickName}`
  );
  await requestChallengeDetail();
}

const editChallengeCommentId = ref(-1);
const editCommentContent = ref("");
const comments = ref([]);
const comment = ref("");

//댓글등록.
async function requestChallengeCommentRegist() {
  const { status } = await api.post(
    `/challenge/${challengeBoardId.value}/comment`,
    {
      challengeBoardId: challengeBoardId.value,
      userId: userId,
      content: comment.value,
      writer: nickName,
    }
  );
  comment.value = "";
  //성공시 다시 전체 댓글 목록 불러오기
  if (status === axios.HttpStatusCode.Created) {
    const { data } = await api.get(
      `/challenge/${challengeBoardId.value}/comment`
    );
    comments.value = data;
    //실패시
  } else {
    //작성하기
  }
}
//댓글 수정.
async function requestChallengeCommentUpdate(id) {
  await api.put(`/challenge/${challengeBoardId.value}/comment/${id}`, {
    challengeCommentId: id,
    challengeBoardId: challengeBoardId.value,
    content: editCommentContent.value,
  });

  editChallengeCommentId.value = -1;
  editCommentContent.value = "";
  requestChallengeComments();
}

//댓글들 조회.
async function requestChallengeComments() {
  const { data } = await api.get(
    `/challenge/${challengeBoardId.value}/comment`
  );
  comments.value = data;
}

//댓글 삭제.
const requestDeleteComment = async () => {
  await api.delete(
    `/challenge/${challengeBoardId.value}/comment/${editChallengeCommentId.value}`
  );
  requestChallengeComments();
  closeCommentModal(false);
};

const props = defineProps({ challenge: Object });

const showCommentModal = ref(false);
const showChallengeFitModal = ref(false);

const openChallengeFitModal = () => {
  showChallengeFitModal.value = true;
};

const closeChallengeFitModal = () => {
  showChallengeFitModal.value = false;
};

const openCommentModal = (challengeCommentId, content) => {
  showCommentModal.value = true;
  editChallengeCommentId.value = challengeCommentId;
  editCommentContent.value = content;
};

const closeCommentModal = (isContinue) => {
  showCommentModal.value = false;
  if (!isContinue) {
    editChallengeCommentId.value = -1;
  }
};

const editChallengeFit = () => {
  closeChallengeFitModal();
  router.push({
    name: "ChallengeFitUpdate",
    params: { id: challengeBoardId.value },
  });
};

const deleteChallengeFit = async () => {
  closeChallengeFitModal();

  await api.delete(`/challenge/${challengeBoardId.value}/${nickName}`);
  router.push({ name: "ChallengeFit" });
};

//좋아요

const likeCount = ref(0);
const isLike = ref(0);
//좋아요 조회
async function requestChallengeLike() {
  const { data } = await api.get(
    `/challenge/${challengeBoardId.value}/like/${nickName}`
  );
  isLike.value = data.like;
  likeCount.value = data.likeCount;
}

//좋아요 수정
async function updateLike() {
  isLike.value = isLike.value == 0 ? 1 : 0;

  const { data } = await api.post(`/challenge/${challengeBoardId.value}/like`, {
    boardId: challengeBoardId.value,
    writer: nickName,
    like: isLike.value,
  });
  requestChallengeLike();
}

function goBack() {
  router.back(); // 이전 페이지로 이동
}
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
  margin: 16px 0px;
}

.challenge-detail__progress-bar {
  background: #e0e0e0;
  height: 15px;
  border-radius: 10px;
  overflow: hidden;
}

.challenge-detail__progress-fill {
  height: 100%;
  background: #4caf50;
  display: flex; /* ★ 텍스트 중앙 정렬을 위해 flex 사용 */
  align-items: center;
  justify-content: center;
  color: #fff; /* 텍스트 색 흰색 */
  font-size: 0.75rem; /* 필요에 따라 조절 */
  white-space: nowrap; /* “D-10” 같은 짧은 텍스트 용 */
}

.challenge-detail__participants {
  font-size: 14px;
  color: #555;
  margin-top: 6px;
}
/* 아바타-좋아요  */
.avatars-like-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* 아바타 */
.challenge-detail__avatars-section {
  display: flex;
  gap: 15px;
  align-items: center;
  /* margin-top: 18px; */
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
.like-btn {
  margin: 0px;
  cursor: pointer;
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
.challenge-detail__limit-over-btn {
  background: #7e7e7e;
  cursor: default;
  pointer-events: none;
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
  cursor: pointer;
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
