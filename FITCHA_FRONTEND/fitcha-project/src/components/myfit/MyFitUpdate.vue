<template>
  <!-- 프로필 정보 -->
  <div class="profile-update-wrapper">
    <!-- 상단 헤더 -->
    <div class="edit-header">
      <button class="back-btn" type="button" @click="closeEdit">
        <i class="fa fa-arrow-left"></i>
      </button>
      <span class="header-title">프로필 수정</span>
      <button class="save-btn" type="button" @click="closeEdit">저장</button>
    </div>

    <!-- 프로필 이미지 + 카메라 아이콘 -->
    <div class="edit-profile-img">
      <img src="../../assets/images/run.jpg" class="profile-img" />
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
        v-model="nickName"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  nickName: String,
});

const emit = defineEmits(["close"]);
const nickName = ref(props.nickName);

// 뒤로가기 또는 저장 후 닫기
const closeEdit = () => {
  emit("close");
};
</script>

<style scoped>
.profile-wrapper {
  font-family: "Noto Sans KR", sans-serif;
  max-width: 480px;
  margin: 0 auto;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.15);
}

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
