<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <template v-for="challenge in challenges" :key="challenge">
        <router-link class="detailLink" :to="{ name: 'ChallengeFitDetail', params: { id: challenge.challengeBoardId }, query: { isViewCounted: true } }">
          <ChallengeFitCard :challenge="challenge" />
        </router-link>
      </template>
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainGridLayout from "@/components/common/MainGridLayout.vue";
import ChallengeFitCard from "@/components/challengefit/ChallengeFitCard.vue";
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import { ref } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
const BASE_URL = "http://localhost:8080/challenge";
const search = ref({});
const route = useRoute();
const router = useRouter();
let challenges = ref({});

async function requestChallengeSearch() {
  const { data } = await axios.get(BASE_URL);
  challenges.value = data;
}
requestChallengeSearch();
</script>

<style scoped>
.detailLink {
  text-decoration: none;
}
</style>
