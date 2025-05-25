<template>
  <div class="main-content-search">
    <h3>{{ menu }}</h3>

    <div class="search-bar">
      <!-- 검색 기준 선택 -->
      <select v-model="searchKey">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="both">제목+내용</option>
        <option value="writer">작성자</option>
        <option value="exerciseType" v-if="menu === 'challengefit'">운동타입</option>
        <option value="bodyPart" v-if="menu === 'challengefit'">운동부위</option>
        <option value="level" v-if="menu === 'challengefit'">난이도</option>
      </select>

      <!-- 검색어 입력 -->
      <input type="text" placeholder="검색어를 입력하세요" v-model="searchWord" @keyup.enter="search" />

      <!-- 검색 버튼 -->
      <button @click="search"><i class="fas fa-search"></i></button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const searchWord = ref('');
const searchKey = ref('title');
const menu = ref(window.location.pathname.split('/')[1]);

function search() {
  router.push({
    path: window.location.pathname,
    query: {
      key: searchKey.value,
      word: searchWord.value,
    },
  });
}
</script>

<style scoped>
.main-content-search {
  margin: 20px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.main-content-search h3 {
  margin-bottom: 12px;
  font-size: 1.4rem;
  color: #333;
}

/* 통합 검색 바 스타일 */
.search-bar {
  display: flex;
  align-items: center;
  background-color: #f1f3f4;
  border-radius: 30px;
  padding: 0px 12px 0px 0px;
  width: 100%;
  max-width: 550px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* select */
.search-bar select {
  border: none;
  background: transparent;
  color: #333;
  font-size: 0.9rem;
  margin-right: 10px;
  padding: 6px 8px;
  border-radius: 8px;
  /* background-color: #e0e0e0; */
  cursor: pointer;
}

/* input */
.search-bar input {
  flex: 1;
  padding: 8px 12px;
  border: none;
  outline: none;
  font-size: 1rem;
  margin-right: 8px;
}

/* 버튼 */
.search-bar button {
  border: none;
  color: #333;
  font-size: 1.1rem;
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f1f3f4;
}
</style>
