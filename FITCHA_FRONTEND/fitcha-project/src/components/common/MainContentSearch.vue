<template>
  <div class="main-content-search">
    <!-- 메인 컨텐트의 검색 박스 -->
    <!-- 다른 페이지일때는 없거나 인증글 페이지일때는 달라질 수 있어서 따로 뺐음 -->
    <h3>Challenge</h3>

    <div class="search-wrapper">
      <div class="search-box">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="search..." />
      </div>

      <div class="select-wrapper">
        <select>
          <option value="">전체</option>
          <option value="">초급</option>
          <option value="">중급</option>
          <option value="">고급</option>
        </select>
      </div>
    </div>
  </div>
</template>

<script setup></script>

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
}

.search-wrapper .search-box {
  position: relative;
  margin-right: 15px;
}

.search-wrapper .search-box i {
  position: absolute;
  top: 27%;
  left: 12px;
  font-size: 1rem;
  color: #9fa3a6;
}
.search-wrapper .search-box input {
  padding: 12px 16px 12px 36px;
  border: none;
  outline: none;
  border-radius: 14px;
}

.main-content-search .search-wrapper {
  display: flex;
}

.main-content-search h3 {
  margin: 0px;
  font-weight: 500;
  font-size: 1.2em;
  color: #333;
  display: flex;
  align-self: center;
}
.search-wrapper .select-wrapper {
  position: relative;
  display: inline-block;
  width: 60px;
}

.select-wrapper select {
  width: 100%;
  appearance: none; /* 기본 화살표 제거 (크롬, 사파리) */
  -webkit-appearance: none; /* 사파리 */
  -moz-appearance: none; /* 파이어폭스 */
  background: none;
  border: none;
  outline: none;
  padding: 8px 12px;
  font-size: 0.9rem;
  color: #333;
  background-color: #fff;
  border-radius: 10px;
  border: 1px solid #ccc;
  cursor: pointer;
}

.select-wrapper::after {
  content: "▼";
  position: absolute;
  top: 50%;
  right: 7px;
  transform: translateY(-50%);
  pointer-events: none;
  color: #888;
  font-size: 0.7rem;
}
</style>
