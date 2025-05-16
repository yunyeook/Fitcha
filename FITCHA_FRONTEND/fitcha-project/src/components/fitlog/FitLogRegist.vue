<template>
  <!-- 챌린지 인증글 작성 구조 -->
  <div class="challenge-form-wrapper">
    <h2>챌린지 인증하기</h2>

    <form class="challenge-form">
      <!-- 썸네일 미리보기 -->
      <div class="form-group">
        <label for="thumbnail">썸네일 이미지</label>
        <div class="image-preview" id="imagePreview">
          <span>이미지를 선택하면 미리보기가 표시됩니다</span>
        </div>
        <input type="file" id="thumbnail" accept="image/*" />
      </div>

      <!-- 제목 -->
      <div class="form-group">
        <label for="title">인증글 제목</label>
        <input
          type="text"
          id="title"
          placeholder="예: 30일 아침 러닝 챌린지!!"
        />
      </div>

      <!-- 설명 -->
      <div class="form-group">
        <label for="description">설명</label>
        <textarea
          id="description"
          rows="4"
          placeholder="챌린지 내용을 입력하세요."
        ></textarea>
      </div>

      <!-- 운동 타입 -->
      <div class="form-group">
        <label for="type">운동 타입</label>
        <select id="type">
          <option value="">선택</option>
          <option>러닝</option>
          <option>헬스</option>
          <option>요가</option>
          <option>필라테스</option>
          <option>홈트</option>
        </select>
      </div>

      <!-- 지역 -->
      <div class="form-group">
        <label for="location">지역</label>
        <select id="location">
          <option value="">선택</option>
          <option>서울</option>
          <option>부산</option>
          <option>대구</option>
          <option>기타</option>
        </select>
      </div>

      <!-- 난이도 -->
      <div class="form-group">
        <label for="level">난이도</label>
        <select id="level">
          <option value="">선택</option>
          <option>초보</option>
          <option>중급</option>
          <option>고급</option>
        </select>
      </div>

      <!-- 모집 인원 -->
      <div class="form-group">
        <label for="limit">모집 인원</label>
        <input type="number" id="limit" placeholder="예: 10" min="1" />
      </div>

      <!-- 날짜 -->
      <div class="form-group date-group">
        <div>
          <label for="start-date">시작 날짜</label>
          <input type="date" id="start-date" />
        </div>
        <div>
          <label for="end-date">종료 날짜</label>
          <input type="date" id="end-date" />
        </div>
      </div>

      <!-- 제출 버튼 -->
      <div class="challenge-regist">
        <button type="submit" class="submit-btn">등록하기</button>
      </div>
    </form>
  </div>
</template>

<script setup></script>

<style scoped>
/* 챌린지 게시글 작성 폼 css */
/* 일단 인증글 작성폼도 동일하게 만들었기 때문에 인증글 작성폼에서도 이 css 파일 불러오면 됨 */
.challenge-form-wrapper {
  width: 100%;
  max-width: 500px;
  margin: 0 auto;
  background-color: #ffffff;
  border-radius: 15px;
  box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.1);
  padding: 24px;
  font-family: "Arial", sans-serif;
}

.challenge-form-wrapper h2 {
  font-size: 1.3rem;
  color: #222;
  font-weight: bold;
  margin-bottom: 20px;
}

.challenge-form .form-group {
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
}

.challenge-form label {
  font-size: 0.9rem;
  margin-bottom: 6px;
  color: #333;
  font-weight: 500;
}

.challenge-form input,
.challenge-form select,
.challenge-form textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 0.85rem;
  background-color: #f9f9f9;
  color: #333;
}

.challenge-form textarea {
  resize: vertical;
}

.challenge-form .date-group {
  display: flex;
  justify-content: space-between;
  gap: 12px;
}

.challenge-form .date-group > div {
  flex: 1;
}

.challenge-regist-button-wrapper {
  display: flex;
  justify-content: end;
}
.challenge-form .submit-btn {
  background-color: #3cb371;
  color: #fff;
  border: none;
  padding: 12px;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.challenge-form .submit-btn:hover {
  background-color: #2e9c64;
}

.challenge-form .image-preview {
  width: 100%;
  height: 160px;
  border: 1px dashed #ccc;
  border-radius: 8px;
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #aaa;
  font-size: 0.8rem;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
</style>
