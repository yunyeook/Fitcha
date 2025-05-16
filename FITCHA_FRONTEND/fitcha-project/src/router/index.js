import { createRouter, createWebHistory } from "vue-router";
import ChallengeFitView from "@/views/ChallengeFitView.vue";
import ChallengeFitDetailView from "@/views/ChallengeFitDetailView.vue";
import FitTubeView from "@/views/FitTubeView.vue";

const routes = [
  // 패스 경로는 테스트 위해서 임시로 해놓은거니 알아서 수정 필요
  { path: "/challenge", component: ChallengeFitView },
  { path: "/challenge/detail", component: ChallengeFitDetailView },
  { path: "/fittube", component: FitTubeView },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
