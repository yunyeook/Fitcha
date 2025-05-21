// src/utils/logout.js
import { useUserStore } from "@/stores/user";
import router from "@/router";

export function logout() {
  const userStore = useUserStore();
  userStore.clearUser(); // Pinia 상태 초기화
  localStorage.removeItem("access-token"); // 토큰 제거
  alert("로그아웃 되었습니다.");
  router.push("/login"); // 로그인 페이지로 이동
}
