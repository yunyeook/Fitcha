<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <FitLogCard
        v-for="fitlog in fitlogs"
        :key="fitlog.proofBoardId"
        :fitlog="fitlog"
      />
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import MainGridLayout from "@/components/common/MainGridLayout.vue";
import FitLogCard from "@/components/fitlog/FitLogCard.vue";
import { ref } from "vue";
import axios from "axios";

const BASE_URL = "http://localhost:8080/proof";

const fitlogs = ref([]);
const requestFitLogAll = async () => {
  const { data } = await axios.get(BASE_URL);
  fitlogs.value = data;
};

requestFitLogAll();
</script>

<style scoped></style>
