<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <FitTubeCard />
      <!-- 아래는 테스트 위해 복붙 한것. 나중에 v-for로 변경 -->
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
      <FitTubeCard />
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import FitTubeCard from "@/components/fittube/FitTubeCard.vue";
import MainGridLayout from "@/components/common/MainGridLayout.vue";
</script>

<style scoped></style>
