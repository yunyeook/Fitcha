import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    userId: null,
    nickName: null,
  }),
  getters: {
    isLoggedIn: (state) => !!state.userId,
  },
  actions: {
    // 로그인 성공 시 호출할 유저 정보 세팅 함수
    setUser({ userId, nickName }) {
      this.userId = userId;
      this.nickName = nickName;
    },
    // 로그아웃 시 상태 초기화 함수
    clearUser() {
      this.userId = null;
      this.nickName = null;
    },
  },
  persist: {
    storage: localStorage, // 또는 sessionStorage로 바꿀 수도 있음
  },
});
