<template>
  <div>
    <!-- 메인 영역의 헤더 (인사와 검색) -->
    <header class="main-header"></header>

    <!-- 게시글들 레이아웃을 전체 감싸는 div -->
    <div class="main-content-wrapper">
      <div class="main-content-grid">
        <template v-for="challenge in challenges" :key="challenge">
          <ChallengeFitCard :challenge="challenge" @click="requestChallengeDetail(challenge.challengeBoardId)" />
        </template>
      </div>
    </div>
  </div>
</template>

<script setup>
import ChallengeFitCard from '@/components/challengeFit/ChallengeFitCard.vue';
import { ref } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
const BASE_URL = 'http://localhost:8080/challenge';
const search = ref({});
const route = useRoute();
const router = useRouter();
let challenges = ref({});

async function requestChallengeSearch() {
  const { data } = await axios.get(BASE_URL);
  challenges.value = data;
}
requestChallengeSearch();

async function requestChallengeDetail(challengeBoardId) {
  router.push({ name: 'challengeFitDetail', params: { id: challengeBoardId } });
}
</script>

<style scoped></style>
