<template>
  <div>
    <MainHeader />
    <MainDetailLayout>
      <FitLogCardDetail />
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";
import FitLogCardDetail from "@/components/fitlog/FitLogCardDetail.vue";
</script>

<style scoped></style>
