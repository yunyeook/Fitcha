<template>
  <div>
    <!-- 메인 영역의 헤더 (인사와 검색) -->
    <header class="main-header"></header>

    <!-- 게시글들 레이아웃을 전체 감싸는 div -->
    <div class="main-content-wrapper">
      <div class="main-content-grid">
        <ChallengeCardDetail :challenge="challenge" />
      </div>
    </div>
  </div>
</template>

<script setup>
import ChallengeCardDetail from '@/components/challengeFit/ChallengeCardDetail.vue';
import { ref } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
const BASE_URL = 'http://localhost:8080/challenge';
const route = useRoute();
const router = useRouter();
const isViewCounted = ref(route.query.isViewCounted);
const challengeBoardId = ref(route.params.id);
const challenge = ref({});
async function requestChallengeDetail() {
  const { data } = await axios.get(`${BASE_URL}/${challengeBoardId.value}`, {
    params: {
      // user:{}
      isViewCounted: isViewCounted.value,
    },
  });
  challenge.value = data;
}
requestChallengeDetail();
</script>

<style lang="scss" scoped></style>
