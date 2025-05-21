<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <template v-for="video in videos" :key="video.id">
        <router-link class="detailLink">
          <FitTubeCard />
        </router-link>
      </template>
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import FitTubeCard from "@/components/fittube/FitTubeCard.vue";
import MainGridLayout from "@/components/common/MainGridLayout.vue";

import { ref } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
const BASE_URL = "http://localhost:8080/youtube/search";
const search = ref({});
const route = useRoute();
const router = useRouter();
const videos = ref([]);

async function requestFitTubeVideos() {
  const { data } = await axios.get(BASE_URL);

  videos.value = data;
}
requestFitTubeVideos();
</script>

<style scoped>
.detailLink {
  text-decoration: none;
}
</style>
