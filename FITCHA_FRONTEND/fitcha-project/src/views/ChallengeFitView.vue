<template>
  <div class="page-wrapper">
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <template v-for="challenge in challenges" :key="challenge">
        <ChallengeFitCard :challenge="challenge" />
      </template>
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainGridLayout from '@/components/common/MainGridLayout.vue';
import ChallengeFitCard from '@/components/challengefit/ChallengeFitCard.vue';
import MainHeader from '@/components/common/MainHeader.vue';
import MainContentSearch from '@/components/common/MainContentSearch.vue';
import { onMounted, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import api from '@/api/api';
const route = useRoute();
const router = useRouter();
let challenges = ref({});

async function requestChallengeSearch(searchKey, searchWord) {
  let response;
  if (!searchKey || !searchWord) {
    // 검색 조건이 없으면 전체 조회
    response = await api.get('/challenge');
  } else {
    // 검색 조건이 있으면 해당 조건으로 검색
    response = await api.get('/challenge', {
      params: {
        key: searchKey,
        word: searchWord,
      },
    });
  }
  challenges.value = response.data;
}
onMounted(() => {
  requestChallengeSearch(route.query.key, route.query.word);
});

// 쿼리 변경 감지 시 재검색
watch(
  () => [route.query.key, route.query.word], // key 또는 word 둘 중 하나라도 바뀌면
  ([newKey, newWord]) => {
    requestChallengeSearch(newKey, newWord);
  }
);
</script>

<style scoped>
.page-wrapper {
  height: 780px;
  overflow-y: hidden;
}
</style>
