<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <ChallengeFitCard />
      <!-- 임시로 테스트 위해 복붙. 나중에 지우고 v-for 돌리기-->
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
      <ChallengeFitCard />
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainGridLayout from "@/components/common/MainGridLayout.vue";
import ChallengeFitCard from "@/components/challengefit/ChallengeFitCard.vue";
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
</script>

<style scoped></style>
