<template>
  <div class="comment-card">
    <img
      class="comment-profile"
      src="../assets/images/user1.jpg"
      alt="프로필"
    />
    <div class="comment-body">
      <div class="comment-header">
        <span class="comment-author">{{ comment.writer }}</span>
        <div class="comment-menu" @click="openCommentModal">
          <i class="fas fa-ellipsis-v"></i>
        </div>
      </div>
      <div class="comment-text">
        {{ comment.content }}
      </div>
      <div class="comment-date">{{ comment.regDate }}</div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  comment: {
    type: Object,
  },
});
</script>

<style scoped>
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
</style>
