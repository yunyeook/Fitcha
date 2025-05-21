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
import { ref, onMounted } from "vue";
import api from "@/api/api";

const fitlogs = ref([]);

onMounted(async () => {
  const { data } = await api.get("/proof");
  fitlogs.value = data;
  console.log(fitlogs.value);
});
</script>

<style scoped></style>
