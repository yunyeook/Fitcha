<template>
  <div>
    <MainHeader />
    <MainContentSearch />
    <MainGridLayout>
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
      <FitLogCard />
    </MainGridLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import MainGridLayout from "@/components/common/MainGridLayout.vue";
import FitLogCard from "@/components/fitlog/FitLogCard.vue";
</script>

<style scoped></style>
