<template>
  <div>
    <MainHeader />
    <MainDetailLayout>
      <FitLogCardDetail :fitlog="fitlog" />
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";
import FitLogCardDetail from "@/components/fitlog/FitLogCardDetail.vue";
import axios from "axios";
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const proofBoardId = route.params.id;

const fitlog = ref({});
const BASE_URL = "http://localhost:8080/proof";

const requestFitLogDetail = async () => {
  const { data } = await axios.get(`${BASE_URL}/${proofBoardId}`);
  fitlog.value = data;
};

requestFitLogDetail();
</script>

<style scoped></style>
