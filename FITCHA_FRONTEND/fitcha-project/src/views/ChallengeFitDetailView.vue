<template>
  <div>
    <MainHeader />
    <MainDetailLayout>
      <ChallengeFitCardDetail />
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";
import ChallengeFitCardDetail from "@/components/challengefit/ChallengeFitCardDetail.vue";
import MainHeader from "@/components/common/MainHeader.vue";
</script>

<style scoped></style>
