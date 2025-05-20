<template>
  <div class="main-content-search">
    <div class="search-wrapper">
      <div class="search-box">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="search..." v-model="search" @keyup.enter="challengeFitSearch" />
      </div>

      <template v-if="menu === 'challengefit'">
        <div class="filter-options">
          <div class="custom-select">
            <select v-model="exerciseType">
              <option disabled hidden value="">운동 타입</option>
              <option>근력 운동</option>
              <option>유산소</option>
              <option>필라테스</option>
              <option>스트레칭</option>
            </select>
          </div>

          <div class="custom-select">
            <select v-model="bodyPart">
              <option disabled hidden value="">운동 부위</option>
              <option>전신</option>
              <option>상체</option>
              <option>하체</option>
              <option>복부</option>
            </select>
          </div>

          <div class="custom-select">
            <select v-model="level">
              <option disabled hidden value="">난이도</option>
              <option>초급</option>
              <option>중급</option>
              <option>고급</option>
            </select>
          </div>

          <div class="custom-select">
            <select v-model="sortBy">
              <option disabled hidden value="">정렬 기준</option>
              <option>최신순</option>
              <option>인기순</option>
            </select>
          </div>
        </div>
      </template>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const menu = ref(window.location.pathname.split('/')[1]);

const level = ref('');
const bodyPart = ref('');
const exerciseType = ref('');
const sortBy = ref('');

const challengeFitSearch = () => {
  const search = ref({});
  if (level) {
    search.value['level'] = level.value;
    search.value['bodyPart'] = bodyPart.value;
    search.value['exerciseType'] = exerciseType.value;
    search.value['sortBy'] = sortBy.value;
  }
};
</script>

<style scoped>
.main-content-search {
  border-bottom: 1px solid #ccc;
  margin-bottom: 15px;
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
}

.search-wrapper {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 12px;
}

.search-box {
  position: relative;
}

.search-box i {
  position: absolute;
  top: 27%;
  left: 12px;
  font-size: 1rem;
  color: #9fa3a6;
}

.search-box input {
  padding: 12px 16px 12px 36px;
  border: none;
  outline: none;
  border-radius: 14px;
  background: #fff;
}

.filter-options {
  display: flex;
  gap: 10px;
}

.custom-select select {
  appearance: none;
  background-color: #fff;
  border: 1px solid #dcdcdc;
  border-radius: 9999px;
  padding: 8px 36px 8px 12px; /* 오른쪽 여백 늘리기 */
  font-size: 0.9rem;
  color: #333;
  text-align: center;
  text-align-last: center;

  /* ▼ 아이콘 설정 */
  background-image: url("data:image/svg+xml;utf8,<svg fill='%23666' height='16' viewBox='0 0 24 24' width='16' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 16px; /* 아이콘 크기 키움 */
  cursor: pointer;
}
</style>
