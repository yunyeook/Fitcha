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
        <div v-if="isMyFitLog" class="proof-menu" @click="openProofModal">
          <i class="fas fa-ellipsis-v"></i>
        </div>
      </div>

      <!-- 인증 이미지 -->
      <div class="proof-image">
        <img :src="imgUrl" alt="운동 인증" />
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
          <div class="hashtags">
            <span v-for="hashtag in fitlog.hashTags">{{ hashtag }}</span>
          </div>
          <router-link
            class="go-challenge"
            :to="`/challengefit/${fitlog.challengeBoardId}`"
            >참여한 챌린지 보기 &rarr;
          </router-link>
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
        <!-- 댓글 입력 폼 -->
        <div class="comment-form">
          <img class="comment-profile" src="" alt="내 프로필" />
          <input
            type="text"
            placeholder="댓글을 입력하세요..."
            v-model="commentContent"
            @keyup.enter="submitComment"
          />
          <button @click="submitComment">등록</button>
        </div>
        <!-- 댓글 -->
        <FitlogCardComment
          v-for="comment in comments"
          :key="comment.proofCommentId"
          :comment="comment"
          :isEditing="editingCommentId === comment.proofCommentId"
          :editingContent="editingCommentContent"
          @updateEditingContent="updateCommentContent"
          @submitEdit="updateComment"
          @open-comment-modal="openCommentModal"
        />
      </div>
      <!-- 댓글 수정/삭제 모달 -->
      <div
        v-if="showCommentModal"
        class="modal-overlay"
        @click.self="closeCommentModal"
      >
        <div class="modal-box">
          <button class="modal-close-button" @click="closeCommentModal">
            ×
          </button>
          <div class="modal-title">댓글 관리</div>
          <button class="modal-button" @click="editComment">수정하기</button>
          <button class="modal-button delete" @click="deleteComment">
            삭제하기
          </button>
        </div>
      </div>
      <!-- 인증글 수정/삭제 모달 -->
      <div
        v-if="showProofModal"
        class="modal-overlay"
        @click.self="closeProofModal"
      >
        <div class="modal-box">
          <button class="modal-close-button" @click="closeProofModal">×</button>
          <div class="modal-title">인증글 관리</div>
          <button class="modal-button" @click="editProof">수정하기</button>
          <button
            class="modal-button delete"
            type="button"
            @click="deleteProof"
          >
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

const fitlog = computed(() => props.fitlog);
const proofBoardId = computed(() => {
  return fitlog.value?.proofBoardId;
});

import api from "@/api/api";
import { useUserStore } from "@/stores/user";
import { ref, onMounted, computed, watch } from "vue";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import FitlogCardComment from "./FitlogCardComment.vue";

const userStore = useUserStore();
const { nickName, userId } = storeToRefs(userStore);

const isMyFitLog = computed(() => {
  return props.fitlog?.writer === nickName.value;
});

const router = useRouter();
const showCommentModal = ref(false);
const showProofModal = ref(false);
const imgUrl = computed(() => {
  return props.fitlog?.proofFiles?.length > 0
    ? "http://localhost:8080/" + props.fitlog.proofFiles[0].fileUrl
    : "";
});

// 댓글 조회
const comments = ref([]);
watch(
  proofBoardId,
  async (id) => {
    if (id) {
      try {
        const { data } = await api.get(`/proof/${id}/comment`);
        comments.value = data;
      } catch (err) {
        console.error("댓글 로딩 실패:", err);
      }
    } else {
      console.warn("proofBoardId가 아직 정의되지 않음");
    }
  },
  { immediate: true }
);

// 댓글 등록
const commentContent = ref("");
const submitComment = async () => {
  try {
    const data = {
      content: commentContent.value,
      writer: nickName.value,
      userId: userId.value,
      proofBoardId: proofBoardId.value,
    };
    await api.post(`/proof/${proofBoardId.value}/comment`, data);
    commentContent.value = "";
    //  댓글 다시 불러오기
    const res = await api.get(`/proof/${proofBoardId.value}/comment`);
    comments.value = res.data;
  } catch (err) {
    console.error("댓글 등록 실패: ", err);
  }
};

// 댓글 수정
const editingCommentId = ref(null);
const editingCommentContent = ref("");
const updateCommentContent = (val) => {
  editingCommentContent.value = val;
};
const updateComment = async (proofCommentId) => {
  try {
    await api.put(`/proof/${proofBoardId.value}/comment/${proofCommentId}`, {
      content: editingCommentContent.value,
    });
    // 댓글 다시 불러오기
    const res = await api.get(`/proof/${proofBoardId.value}/comment`);
    comments.value = res.data;
    // 수정 모드 종료
    editingCommentId.value = null;
    editingCommentContent.value = "";
  } catch (error) {
    console.error("댓글 수정 실패", error);
  }
};

// 댓글 수정 삭제 모달
// 선택된 댓글 정보
const selectedComment = ref(null);
const openCommentModal = (comment) => {
  selectedComment.value = comment;
  showCommentModal.value = true;
};

const closeCommentModal = () => {
  showCommentModal.value = false;
};
const openProofModal = () => {
  showProofModal.value = true;
};

const closeProofModal = () => {
  showProofModal.value = false;
};

const editComment = () => {
  // 수정 모드로 전환, 수정할 댓글 id와 내용 세팅
  editingCommentId.value = selectedComment.value.proofCommentId;
  editingCommentContent.value = selectedComment.value.content;
  closeCommentModal();
};

const deleteComment = async () => {
  try {
    await api.delete(
      `proof/${proofBoardId.value}/comment/${selectedComment.value.proofCommentId}`
    );
    //  댓글 다시 불러오기
    const res = await api.get(`/proof/${proofBoardId.value}/comment`);
    comments.value = res.data;
  } catch (err) {}
  closeCommentModal();
};

// 인증글 수정 & 삭제
const editProof = () => {
  closeProofModal();
  router.push({
    name: "FitLogUpdateView",
    params: {
      proofBoardId: fitlog.value.proofBoardId,
    },
  });
};

const deleteProof = async () => {
  try {
    await api.delete(`proof/${props.fitlog.proofBoardId}`);
    closeProofModal();
    router.push(`/fitlog`);
  } catch (error) {
    console.error("인증글 삭제 중 오류 발생:", error);
  }
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
  font-size: 1.6rem;
  color: #222;
}

.user-info .user-name {
  font-size: 1.1rem;
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
  max-height: 500px;
  object-fit: cover; /* 비율 유지하면서 채우기 (일부 잘릴 수 있음) */
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
  font-size: 1rem;
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
  font-size: 1.25rem;
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
  font-size: 1rem;
  display: flex;
  gap: 10px;
}

.go-challenge {
  font-size: 1rem;
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
  font-size: 1rem;
  color: #666;
}

.write-date {
  font-size: 1rem;
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
