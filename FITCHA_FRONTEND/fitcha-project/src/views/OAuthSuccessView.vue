<template>
  <div>
    <p>로그인 중...</p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '@/stores/user';
import Signup from '@/components/login/Signup.vue';

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();

onMounted(() => {
  const { token, userId, nickName, signup } = route.query;

  if (!token) {
    // 토큰이 없으면 로그인으로
    return router.replace('/login');
  }

  if (signup === 'true') {
    router.replace({
      name: 'Signup',
      query: {
        userId,
        nickName,
      },
    });
  } else {
    // 토큰 저장
    localStorage.setItem('access-token', token);

    // Pinia 상태 저장
    userStore.setUser({ userId, nickName });
    router.replace('/home');
  }
});
</script>
