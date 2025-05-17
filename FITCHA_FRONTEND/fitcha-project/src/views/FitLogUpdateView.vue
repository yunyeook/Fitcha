<template>
  <div>
    <MainHeader />
    <MainDetailLayout>
      <FitLogUpdate />
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";
import FitLogUpdate from "@/components/fitlog/FitLogUpdate.vue";
</script>

<style scoped></style>
