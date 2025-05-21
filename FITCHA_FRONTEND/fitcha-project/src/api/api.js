import axios from "axios";
import router from "@/router";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

// 요청 인터셉터
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("access-token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// 응답 인터셉터: 토큰 만료 처리
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      //  토큰 제거
      localStorage.removeItem("access-token");

      // 로그인 페이지로 이동
      alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
      router.push("/login");
    }

    return Promise.reject(error);
  }
);

export default api;
