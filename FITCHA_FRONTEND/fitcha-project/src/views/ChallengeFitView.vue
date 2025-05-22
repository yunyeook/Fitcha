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
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import api from '@/api/api';
const search = ref({});
const route = useRoute();
const router = useRouter();
let challenges = ref({});

async function requestChallengeSearch() {
  const { data } = await api.get('/challenge');
  challenges.value = data;
}
requestChallengeSearch();
</script>

<style scoped>
.page-wrapper {
  height: 780px;
  overflow-y: hidden;
}
</style>
