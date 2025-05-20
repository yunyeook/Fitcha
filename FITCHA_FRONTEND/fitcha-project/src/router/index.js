import { createRouter, createWebHistory } from 'vue-router';
import ChallengeFitView from '@/views/ChallengeFitView.vue';
import ChallengeFitDetailView from '@/views/ChallengeFitDetailView.vue';
import ChallengeFitRegistView from '@/views/ChallengeFitRegistView.vue';
import ChallengeFitUpdateView from '@/views/ChallengeFitUpdateView.vue';
import FitTubeView from '@/views/FitTubeView.vue';
import FitLogView from '@/views/FitLogView.vue';
import FitLogDetailView from '@/views/FitLogDetailView.vue';
import FitLogRegistView from '@/views/FitLogRegistView.vue';
import FitLogUpdateView from '@/views/FitLogUpdateView.vue';
import HomeView from '@/views/HomeView.vue';
import LoginView from '@/views/LoginView.vue';
import SignupView from '@/views/SignupView.vue';
import MyFitView from '@/views/MyFitView.vue';
import MyFitUpdateView from '@/views/MyFitUpdateView.vue';

const routes = [
  // 패스 경로는 테스트 위해서 임시로 해놓은거니 알아서 수정 필요
  { path: '/challengefit', name: 'ChallengeFit', component: ChallengeFitView },
  {
    path: '/challengefit/:id',
    name: 'ChallengeFitDetail',
    component: ChallengeFitDetailView,
    beforeEnter: (to, from, next) => {
      if (!to.query.isViewCounted) {
        next({
          path: to.path,
          query: { ...to.query, isViewCounted: 'false' },
        });
      } else {
        next();
      }
    },
  },

  {
    path: '/challengefit/regist',
    name: 'ChallengeFitRegist',
    component: ChallengeFitRegistView,
  },
  {
    path: '/challengefit/:id/update',
    name: 'ChallengeFitUpdate',
    component: ChallengeFitUpdateView,
  },
  { path: '/fittube', name: 'FitTube', component: FitTubeView },
  { path: '/fitlog', component: FitLogView },
  { path: '/fitlog/detail', component: FitLogDetailView },
  { path: '/fitlog/regist', component: FitLogRegistView },
  { path: '/fitlog/update', component: FitLogUpdateView },
  { path: '/home', component: HomeView },
  { path: '/login', component: LoginView },
  { path: '/signup', component: SignupView },
  { path: '/myfit', component: MyFitView },
  { path: '/myfit/update', component: MyFitUpdateView },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
