<template>
  <div>
    <MainHeader />
    <MainDetailLayout>
      <ChallengeFitCardDetail :challenge="challenge" />
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";
import MainHeader from "@/components/common/MainHeader.vue";
import ChallengeFitCardDetail from "@/components/challengefit/ChallengeFitCardDetail.vue";
import { ref } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
const BASE_URL = "http://localhost:8080/challenge";
const route = useRoute();
const router = useRouter();
const isViewCounted = ref(route.query.isViewCounted);
const challengeBoardId = ref(route.params.id);
const challenge = ref({});
async function requestChallengeDetail() {
  const { data } = await axios.get(`${BASE_URL}/${challengeBoardId.value}`, {
    params: {
      // user:{}
      isViewCounted: isViewCounted.value,
    },
  });
  challenge.value = data;
}
requestChallengeDetail();
</script>

<style scoped></style>
