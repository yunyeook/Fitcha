<template>
  <div class="main-area">
    <!-- 히어로 이미지 + CTA -->
    <section class="hero-cta hero-full">
      <div class="hero-overlay">
        <div class="hero-image-wrapper">
          <img
            v-for="(image, index) in images"
            :key="index"
            :src="image"
            :class="['hero-image-large', { active: index === currentIndex }]"
            alt="hero"
          />
        </div>
        <div class="cta-text-hero">
          <h1>운동 챌린지로 건강한 삶 시작하기! 💪</h1>
          <p>
            매일 작은 실천이 모여 큰 변화를 만듭니다.<br />지금 인기 있는
            챌린지에 참여해보세요!
          </p>
          <button class="btn-primary btn-large">챌린지 둘러보기</button>
        </div>
      </div>
    </section>

    <section class="popular-challenges">
      <h3>🔥 지금 인기 있는 챌린지</h3>
      <div class="challenge-cards">
        <div class="challenge-card">
          <img src="../../assets/images/run.jpg" alt="요가챌린지" />
          <h4>하루 10분 요가</h4>
          <p>몸과 마음의 균형을 위한 짧은 요가 챌린지</p>
        </div>
        <div class="challenge-card">
          <img src="../../assets/images/run.jpg" alt="플랭크챌린지" />
          <h4>30일 플랭크 도전</h4>
          <p>복근과 체력 향상을 위한 꾸준한 챌린지</p>
        </div>
        <div class="challenge-card">
          <img src="../../assets/images/run.jpg" alt="걷기챌린지" />
          <h4>하루 만보 걷기</h4>
          <p>매일 만보! 함께 걸어보는 건강한 습관</p>
        </div>
        <div class="challenge-card">
          <img src="../../assets/images/run.jpg" alt="달리기챌린지" />
          <h4>매일 3km 달리기</h4>
          <p>지속 가능한 러닝 습관 만들기</p>
        </div>
        <div class="challenge-card">
          <img src="../../assets/images/run.jpg" alt="스트레칭챌린지" />
          <h4>출근 전 5분 스트레칭</h4>
          <p>아침을 깨우는 간단 스트레칭 루틴</p>
        </div>
      </div>
    </section>

    <!-- 랭킹 섹션과 추천 동영상 한 줄에 배치 -->
    <section class="ranking-and-recommendation">
      <section class="ranking-section">
        <h3>🏆 챌린지 참여 랭킹 TOP 5</h3>
        <ol class="ranking-list">
          <li>
            <span class="rank rank-1">1위</span> @fitmaster –
            <span class="score">27개 참여</span>
          </li>
          <li>
            <span class="rank rank-2">2위</span> @runnerkim –
            <span class="score">24개 참여</span>
          </li>
          <li>
            <span class="rank rank-3">3위</span> @yogaboy –
            <span class="score">20개 참여</span>
          </li>
          <li>
            <span class="rank rank-4">4위</span> @plankqueen –
            <span class="score">18개 참여</span>
          </li>
          <li>
            <span class="rank rank-5">5위</span> @homefit123 –
            <span class="score">16개 참여</span>
          </li>
        </ol>
      </section>
      <section class="recommendation-section">
        <h3>🎥 오늘의 추천 동영상</h3>
        <div class="video-list">
          <div class="video-item">
            <iframe
              src="https://www.youtube.com/embed/HPPBGosyOOM?si=FoCu_IoX__4zak-H"
              title="추천 영상 1"
              frameborder="0"
              allowfullscreen
            ></iframe>
            <p>건강한 루틴 만들기</p>
          </div>
        </div>
      </section>
    </section>

    <!-- 사진 슬라이더 -->
    <section class="proof-gallery">
      <div class="slide-track">
        <!-- 무한 슬라이드를 위해 복제 -->
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
        <img src="../../assets/images/run.jpg" alt="인증사진1" />
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import img1 from "@/assets/images/run.jpg";
import img2 from "@/assets/images/2.jpg";
import img3 from "@/assets/images/3.jpg";

const images = [img1, img2, img3];
const currentIndex = ref(0);
let intervalId;

onMounted(() => {
  intervalId = setInterval(() => {
    currentIndex.value = (currentIndex.value + 1) % images.length;
  }, 3000);
});

onUnmounted(() => {
  clearInterval(intervalId);
});
</script>

<!-- <style scoped>
.main-area {
  max-width: 800px;
  margin: 0 auto;
  padding: 30px 20px;
  border-radius: 20px;
  font-family: "Noto Sans KR", sans-serif;
  color: #2e4d43;
}

/* 히어로 이미지 + CTA */
.hero-full {
  position: relative;
  height: 500px;
  margin-bottom: 60px;
  border-radius: 25px;
  overflow: hidden;
  box-shadow: 0 20px 45px rgba(46, 139, 87, 0.25);
  animation: fadeInUp 1.8s ease forwards;
}
.hero-overlay {
  width: 100%;
  height: 100%;
  position: relative;
}
.hero-image-large {
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: brightness(0.65);
}
.cta-text-hero {
  position: absolute;
  z-index: 10;
  top: 22%;
  left: 7%;
  transform: translate(-50%, -50%);
  color: white;
  text-align: center;
  padding: 20px;
  max-width: 90%;
  animation: fadeInMove 1.5s ease forwards;
}
.cta-text-hero h1 {
  margin: 0px;
  font-size: 2.8rem;
  font-weight: 800;
  /* margin-bottom: 20px; */
  text-shadow: 0 3px 8px rgba(0, 0, 0, 0.5);
}
.cta-text-hero p {
  margin: 0px;
  font-size: 1.4rem;
  color: rgba(255, 255, 255, 0.9);
  line-height: 1.7;
  margin-top: 20px;
  margin-bottom: 25px;
  text-shadow: 0 2px 6px rgba(0, 0, 0, 0.4);
}
.btn-large {
  font-size: 1.2rem;
  padding: 14px 34px;
  border-radius: 40px;
  cursor: pointer;
}

/* 인기 챌린지 - 지그재그 그리드 */
.popular-challenges {
  margin-bottom: 60px;
  padding: 0 10px;
  /* 흐림 제거 */
  filter: none !important;
  opacity: 1 !important;
}
.popular-challenges h3 {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 30px;
  color: #3a6b47;
  font-weight: 700;
  text-shadow: 0 1px 3px rgba(58, 107, 71, 0.4);
  animation: fadeInUp 1.3s ease forwards;
}

/* flex로 감싸고, wrap과 중앙 정렬 */
.challenge-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px 18px;
  max-width: 760px;
  margin: 0 auto;
}

/* 카드 너비 고정, 높이는 자동 */
.challenge-card {
  flex: 0 0 180px;
  background: #ffffff;
  border-radius: 20px;
  padding: 18px 14px;
  box-shadow: 0 6px 15px rgba(58, 107, 71, 0.15),
    0 3px 10px rgba(58, 107, 71, 0.1);
  text-align: center;
  color: #2e4d38;
  font-weight: 600;
  cursor: pointer;
  animation: floatShakeSoft 4.5s ease-in-out infinite;
  transform-origin: center bottom;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

/* 홀짝 카드 위아래 차이로 지그재그 배치 */
.challenge-card:nth-child(odd) {
  margin-top: 25px;
  animation-delay: 0s;
}
.challenge-card:nth-child(even) {
  margin-top: 0;
  animation-delay: 2.25s;
}

/* 호버 효과 */
.challenge-card:hover {
  transform: translateY(-15px) scale(1.08) rotate(-1.5deg);
  box-shadow: 0 15px 30px rgba(46, 139, 87, 0.3),
    0 10px 20px rgba(46, 139, 87, 0.25);
}

.challenge-card img {
  width: 100%;
  height: 140px;
  border-radius: 15px;
  object-fit: cover;
  margin-bottom: 14px;
  box-shadow: 0 5px 12px rgba(46, 139, 87, 0.15);
  transition: filter 0.3s ease;
}

.challenge-card h4 {
  margin-bottom: 6px;
  font-size: 1.15rem;
  color: #33643d;
  text-shadow: 0 0 3px rgba(51, 100, 61, 0.3);
}
.challenge-card p {
  font-size: 0.9rem;
  color: #4a6b56;
  line-height: 1.35;
}

/* 랭킹 + 추천 영상 한 줄 배치 */
.ranking-and-recommendation {
  display: flex;
  gap: 30px;
  justify-content: center;
  margin-bottom: 60px;
  flex-wrap: wrap;
}
.ranking-section,
.recommendation-section {
  border-radius: 25px;
  box-shadow: 0 12px 30px rgba(46, 139, 87, 0.15);
  flex: 1 1 360px;
  animation: fadeInUp 1.5s ease forwards;
}
.recommendation-section .video-item {
  margin: 0 auto;
  margin-top: 25px;
}
.recommendation-section .video-item iframe {
  width: 100%;
}
/* 랭킹 섹션 */
.ranking-section h3,
.recommendation-section h3 {
  margin: 0px;
  font-size: 1.8rem;
  margin-top: 15px;
  color: #276644;
  text-shadow: 0 1px 3px rgba(39, 102, 68, 0.4);
  text-align: center;
}

.ranking-section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 20px;
}
.ranking-list {
  list-style: none;
  padding: 0;
  margin: 0 auto;
  max-width: 380px;
  text-align: left;
}
.ranking-list li {
  background: white;
  margin-bottom: 12px;
  padding: 14px 22px;
  border-radius: 18px;
  font-size: 1.15em;
  font-weight: 600;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #2e6f44;
  box-shadow: 0 6px 18px rgba(46, 139, 87, 0.15);
  transition: background-color 0.3s ease;
  cursor: default;
}
.ranking-list li:hover {
  background-color: #c3e6be;
}
.rank {
  font-weight: 700;
  min-width: 50px;
  font-size: 1.2rem;
  color: #2e8b57;
  margin-right: 15px;
  text-shadow: 0 0 5px rgba(46, 139, 87, 0.3);
}
.rank-1 {
  color: #d4af37;
  text-shadow: 0 0 8px #d4af37;
}
.rank-2 {
  color: #c0c0c0;
  text-shadow: 0 0 8px #c0c0c0;
}
.rank-3 {
  color: #cd7f32;
  text-shadow: 0 0 8px #cd7f32;
}
.score {
  font-weight: 400;
  font-size: 1rem;
  color: #4a7c57;
}

/* 추천 동영상 */
.video-list {
  display: flex;
  flex-direction: column;
  gap: 18px;
}
.video-item iframe {
  width: 100%;
  height: 250px;
  border-radius: 15px;
  box-shadow: 0 6px 15px rgba(46, 139, 87, 0.3);
}
.video-item p {
  margin-top: 8px;
  font-weight: 600;
  color: #33643d;
  font-size: 1.05rem;
  text-align: center;
}

/* 인증 사진 슬라이더 */
.proof-gallery {
  overflow: hidden;
  height: 180px;
  position: relative;
  border-radius: 16px;
  margin-bottom: 30px;
}
.proof-gallery .slide-track {
  display: flex;
  gap: 18px;
  width: calc(180px * 12 + 18px * 11); /* 12 images, 11 gaps */
  animation: smoothSlide 25s ease-in-out infinite;
  margin-top: 40px;
}
.proof-gallery img {
  width: 180px;
  height: 140px;
  border-radius: 16px;
  object-fit: cover;
  flex-shrink: 0;
  /* transition: transform 0.3s ease; */
  transition: transform 0.4s ease, box-shadow 0.4s ease;
  animation: floatSwayZoom 6s ease-in-out infinite;
  box-shadow: 0 10px 20px rgba(46, 139, 87, 0.25);
}
.proof-gallery::before,
.proof-gallery::after {
  content: "";
  position: absolute;
  top: 0;
  width: 80px;
  height: 100%;
  z-index: 2;
  pointer-events: none;
}
.proof-gallery::before {
  left: 0;
  background: linear-gradient(to right, #f5fff7, rgba(245, 255, 247, 0));
}
.proof-gallery::after {
  right: 0;
  background: linear-gradient(to left, #f5fff7, rgba(245, 255, 247, 0));
}
.proof-gallery img:hover {
  transform: translateY(-10px) scale(1.08) rotate(-1deg);
  box-shadow: 0 15px 30px rgba(46, 139, 87, 0.35);
  z-index: 1;
}

/* 자연스럽고 부드러운 슬라이드 애니메이션 */
@keyframes smoothSlide {
  0% {
    transform: translateX(0);
  }
  25% {
    transform: translateX(calc(-12.5%));
  }
  50% {
    transform: translateX(calc(-25%));
  }
  75% {
    transform: translateX(calc(-37.5%));
  }
  100% {
    transform: translateX(calc(-50%));
  }
}

/* 조금 흔들림 효과 추가 (선택사항) */
.proof-gallery img {
  transition: transform 0.3s ease;
  animation: floatSway 6s ease-in-out infinite;
}

@keyframes floatSway {
  0%,
  100% {
    transform: translateY(0) rotate(0deg);
  }
  50% {
    transform: translateY(-5px) rotate(1deg);
  }
}

/* 공통 애니메이션 */
@keyframes fadeInMove {
  0% {
    opacity: 0;
    transform: translateY(15px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translateY(25px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
@keyframes floatShakeSoft {
  0%,
  100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-8px);
  }
}

@keyframes floatSwayZoom {
  0%,
  100% {
    transform: translateY(0) scale(1);
  }
  50% {
    transform: translateY(-6px) scale(1.05);
  }
}
.btn-primary.btn-large {
  position: relative;
  overflow: hidden;
  font-size: 1.2rem;
  padding: 14px 34px;
  border-radius: 40px;
  cursor: pointer;
  background: linear-gradient(135deg, #34c759, #2e8b57);
  color: white;
  font-weight: bold;
  animation: pulseGlow 2s infinite;
  transition: transform 0.3s ease;
}

.btn-primary.btn-large::after {
  content: "";
  position: absolute;
  top: 0;
  left: -100%;
  width: 200%;
  height: 100%;
  background: linear-gradient(
    120deg,
    transparent,
    rgba(255, 255, 255, 0.4),
    transparent
  );
  animation: shimmer 2.5s infinite;
}

.btn-primary.btn-large:hover {
  transform: scale(1.08);
}
.btn-primary.btn-large {
  position: relative;
  overflow: hidden;
  font-size: 1.2rem;
  padding: 12px 35px;
  border-radius: 40px;
  background: linear-gradient(135deg, #00d86d, #0ca678);
  color: #fff;
  font-weight: 700;
  letter-spacing: 0.5px;
  box-shadow: 0 8px 20px rgba(12, 166, 120, 0.4);
  transition: transform 0.25s ease, box-shadow 0.25s ease;
  animation: pulseGlow 2.4s ease-in-out infinite;
  border: none;
  z-index: 1;
}

.btn-primary.btn-large::before {
  content: "";
  position: absolute;
  top: 0;
  left: -75%;
  width: 150%;
  height: 100%;
  background: linear-gradient(
    120deg,
    rgba(255, 255, 255, 0.3) 0%,
    transparent 100%
  );
  transform: skewX(-20deg);
  transition: left 0.4s ease;
  z-index: 0;
}

.btn-primary.btn-large:hover::before {
  left: 100%;
}

.btn-primary.btn-large:hover {
  transform: translateY(-4px) scale(1.03);
  box-shadow: 0 10px 25px rgba(12, 166, 120, 0.5);
}

@keyframes pulseGlow {
  0%,
  100% {
    box-shadow: 0 0 0 0 rgba(12, 166, 120, 0.4);
  }
  50% {
    box-shadow: 0 0 0 10px rgba(12, 166, 120, 0);
  }
}

@keyframes shimmer {
  0% {
    left: -100%;
  }
  100% {
    left: 100%;
  }
}

@keyframes pulseGlow {
  0%,
  100% {
    box-shadow: 0 0 15px rgba(52, 199, 89, 0.4);
  }
  50% {
    box-shadow: 0 0 25px rgba(46, 139, 87, 0.8);
  }
}
.cta-text-hero h1 {
  font-size: 2.8rem;
  animation: slideScaleIn 1.6s ease-out forwards;
}

.cta-text-hero p {
  animation: fadeInMove 2s ease-out forwards;
}

@keyframes slideScaleIn {
  0% {
    opacity: 0;
    transform: scale(0.8) translateY(30px);
  }
  100% {
    opacity: 1;
    transform: scale(1) translateY(0);
  }
}

@keyframes floatParticles {
  from {
    background-position: 0 0;
  }
  to {
    background-position: 1000px 1000px;
  }
}
.hero-image-large {
  animation: zoomInSlow 30s ease-in-out infinite alternate;
}
.hero-image-large {
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: brightness(0.65);
  transition: opacity 1s ease;
  position: absolute;
  top: 0;
  left: 0;
}

/* 이미지 전환시 opacity 애니메이션 활용을 위해, 여러 이미지 겹치도록 위치 고정 */
.hero-overlay {
  position: relative;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.hero-image-wrapper {
  position: relative;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.hero-image-large {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0;
  transition: opacity 1s ease-in-out;
  z-index: 0;
}

.hero-image-large.active {
  opacity: 1;
  z-index: 1;
}
</style> -->

<style scoped>
/* 기본 레이아웃 */
.main-area {
  max-width: 800px;
  margin: 0 auto;
  padding: 30px 20px;
  border-radius: 20px;
  font-family: "Noto Sans KR", sans-serif;
  color: #2e4d43;
}

/* 히어로 섹션 */
.hero-full {
  position: relative;
  height: 500px;
  margin-bottom: 60px;
  border-radius: 25px;
  overflow: hidden;
  box-shadow: 0 20px 45px rgba(46, 139, 87, 0.25);
  animation: fadeInUp 1.8s ease forwards;
}

.hero-overlay,
.hero-image-wrapper {
  position: relative;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.hero-image-large {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: brightness(0.65);
  opacity: 0;
  transition: opacity 1s ease-in-out;
  z-index: 0;
  animation: zoomInSlow 30s ease-in-out infinite alternate;
}
.hero-image-large.active {
  opacity: 1;
  z-index: 1;
}

.cta-text-hero {
  position: absolute;
  z-index: 10;
  top: 22%;
  left: 7%;
  transform: translate(-50%, -50%);
  color: white;
  text-align: center;
  padding: 20px;
  max-width: 90%;
  animation: fadeInMove 1.5s ease forwards;
}

.cta-text-hero h1 {
  margin: 0;
  font-size: 2.8rem;
  font-weight: 800;
  text-shadow: 0 3px 8px rgba(0, 0, 0, 0.5);
  animation: slideScaleIn 1.6s ease-out forwards;
}

.cta-text-hero p {
  margin: 20px 0 25px;
  font-size: 1.4rem;
  line-height: 1.7;
  color: rgba(255, 255, 255, 0.9);
  text-shadow: 0 2px 6px rgba(0, 0, 0, 0.4);
  animation: fadeInMove 2s ease-out forwards;
}

/* 버튼 스타일 */
.btn-large {
  font-size: 1.2rem;
  padding: 14px 34px;
  border-radius: 40px;
  cursor: pointer;
}

.btn-primary.btn-large {
  position: relative;
  overflow: hidden;
  font-size: 1.2rem;
  padding: 12px 35px;
  border-radius: 40px;
  background: linear-gradient(135deg, #00d86d, #0ca678);
  color: #fff;
  font-weight: 700;
  letter-spacing: 0.5px;
  box-shadow: 0 8px 20px rgba(12, 166, 120, 0.4);
  animation: pulseGlow 2.4s ease-in-out infinite;
  border: none;
  transition: transform 0.25s ease, box-shadow 0.25s ease;
  z-index: 1;
}

.btn-primary.btn-large::before {
  content: "";
  position: absolute;
  top: 0;
  left: -75%;
  width: 150%;
  height: 100%;
  background: linear-gradient(
    120deg,
    rgba(255, 255, 255, 0.3) 0%,
    transparent 100%
  );
  transform: skewX(-20deg);
  transition: left 0.4s ease;
  z-index: 0;
}
.btn-primary.btn-large:hover::before {
  left: 100%;
}
.btn-primary.btn-large:hover {
  transform: translateY(-4px) scale(1.03);
  box-shadow: 0 10px 25px rgba(12, 166, 120, 0.5);
}

/* 인기 챌린지 섹션 */
.popular-challenges {
  margin-bottom: 60px;
  padding: 0 10px;
  filter: none !important;
  opacity: 1 !important;
}
.popular-challenges h3 {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 30px;
  color: #3a6b47;
  font-weight: 700;
  text-shadow: 0 1px 3px rgba(58, 107, 71, 0.4);
  animation: fadeInUp 1.3s ease forwards;
}

.challenge-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px 18px;
  max-width: 760px;
  margin: 0 auto;
}

.challenge-card {
  flex: 0 0 180px;
  background: #fff;
  border-radius: 20px;
  padding: 18px 14px;
  text-align: center;
  color: #2e4d38;
  font-weight: 600;
  cursor: pointer;
  box-shadow: 0 6px 15px rgba(58, 107, 71, 0.15),
    0 3px 10px rgba(58, 107, 71, 0.1);
  animation: floatShakeSoft 4.5s ease-in-out infinite;
  transform-origin: center bottom;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.challenge-card:nth-child(odd) {
  margin-top: 25px;
}
.challenge-card:nth-child(even) {
  margin-top: 0;
  animation-delay: 2.25s;
}
.challenge-card:hover {
  transform: translateY(-15px) scale(1.08) rotate(-1.5deg);
  box-shadow: 0 15px 30px rgba(46, 139, 87, 0.3),
    0 10px 20px rgba(46, 139, 87, 0.25);
}
.challenge-card img {
  width: 100%;
  height: 140px;
  border-radius: 15px;
  object-fit: cover;
  margin-bottom: 14px;
  box-shadow: 0 5px 12px rgba(46, 139, 87, 0.15);
  transition: filter 0.3s ease;
}
.challenge-card h4 {
  margin-bottom: 6px;
  font-size: 1.15rem;
  color: #33643d;
  text-shadow: 0 0 3px rgba(51, 100, 61, 0.3);
}
.challenge-card p {
  font-size: 0.9rem;
  color: #4a6b56;
  line-height: 1.35;
}

/* 랭킹 + 영상 */
.ranking-and-recommendation {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
  margin-bottom: 60px;
}
.ranking-section,
.recommendation-section {
  flex: 1 1 360px;
  border-radius: 25px;
  box-shadow: 0 12px 30px rgba(46, 139, 87, 0.15);
  animation: fadeInUp 1.5s ease forwards;
}
.ranking-section h3,
.recommendation-section h3 {
  margin: 15px 0 0;
  font-size: 1.8rem;
  text-align: center;
  color: #276644;
  text-shadow: 0 1px 3px rgba(39, 102, 68, 0.4);
}

/* 랭킹 리스트 */
.ranking-list {
  list-style: none;
  padding: 0;
  margin: 0 auto;
  max-width: 300px;
  text-align: left;
}
.ranking-list li {
  background: white;
  margin-top: 10px;
  margin-bottom: 12px;
  padding: 12px 22px;
  border-radius: 18px;
  font-size: 1.15em;
  font-weight: 600;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #2e6f44;
  box-shadow: 0 6px 18px rgba(46, 139, 87, 0.15);
  cursor: default;
  transition: background-color 0.3s ease;
}
.ranking-list li:hover {
  background-color: #c3e6be;
}
.rank {
  min-width: 50px;
  font-size: 1.2rem;
  font-weight: 700;
  color: #2e8b57;
  margin-right: 15px;
  text-shadow: 0 0 5px rgba(46, 139, 87, 0.3);
}
.rank-1 {
  color: #d4af37;
  text-shadow: 0 0 8px #d4af37;
}
.rank-2 {
  color: #c0c0c0;
  text-shadow: 0 0 8px #c0c0c0;
}
.rank-3 {
  color: #cd7f32;
  text-shadow: 0 0 8px #cd7f32;
}
.score {
  font-size: 1rem;
  font-weight: 400;
  color: #4a7c57;
}

/* 추천 동영상 */
.video-list {
  margin-top: 10px;
  padding: 0px 20px;
}
.video-item iframe {
  width: 100%;
  height: 250px;
  border-radius: 15px;
  box-shadow: 0 6px 15px rgba(46, 139, 87, 0.3);
}
.video-item p {
  margin-top: 8px;
  font-weight: 600;
  color: #33643d;
  font-size: 1.05rem;
  text-align: center;
}

/* 인증 사진 슬라이더 */
.proof-gallery {
  overflow: hidden;
  height: 180px;
  position: relative;
  border-radius: 16px;
  margin-bottom: 30px;
}
.proof-gallery::before,
.proof-gallery::after {
  content: "";
  position: absolute;
  top: 0;
  width: 80px;
  height: 100%;
  z-index: 2;
  pointer-events: none;
}
.proof-gallery::before {
  left: 0;
  background: linear-gradient(to right, #f5fff7, transparent);
}
.proof-gallery::after {
  right: 0;
  background: linear-gradient(to left, #f5fff7, transparent);
}
.proof-gallery .slide-track {
  display: flex;
  gap: 18px;
  width: calc(180px * 12 + 18px * 11);
  animation: smoothSlide 25s ease-in-out infinite;
  margin-top: 40px;
}
.proof-gallery img {
  width: 180px;
  height: 140px;
  border-radius: 16px;
  object-fit: cover;
  flex-shrink: 0;
  box-shadow: 0 10px 20px rgba(46, 139, 87, 0.25);
  transition: transform 0.4s ease, box-shadow 0.4s ease;
  animation: floatSwayZoom 6s ease-in-out infinite;
}
.proof-gallery img:hover {
  transform: translateY(-10px) scale(1.08) rotate(-1deg);
  box-shadow: 0 15px 30px rgba(46, 139, 87, 0.35);
  z-index: 1;
}

/* ===== 애니메이션 ===== */
@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translateY(25px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
@keyframes fadeInMove {
  0% {
    opacity: 0;
    transform: translateY(15px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
@keyframes slideScaleIn {
  0% {
    opacity: 0;
    transform: scale(0.8) translateY(30px);
  }
  100% {
    opacity: 1;
    transform: scale(1) translateY(0);
  }
}
@keyframes floatShakeSoft {
  0%,
  100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-8px);
  }
}
@keyframes floatSwayZoom {
  0%,
  100% {
    transform: translateY(0) scale(1);
  }
  50% {
    transform: translateY(-6px) scale(1.05);
  }
}
@keyframes floatSway {
  0%,
  100% {
    transform: translateY(0) rotate(0);
  }
  50% {
    transform: translateY(-5px) rotate(1deg);
  }
}
@keyframes smoothSlide {
  0% {
    transform: translateX(0);
  }
  25% {
    transform: translateX(-12.5%);
  }
  50% {
    transform: translateX(-25%);
  }
  75% {
    transform: translateX(-37.5%);
  }
  100% {
    transform: translateX(-50%);
  }
}
@keyframes zoomInSlow {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(1.1);
  }
}
@keyframes pulseGlow {
  0%,
  100% {
    box-shadow: 0 0 15px rgba(52, 199, 89, 0.4);
  }
  50% {
    box-shadow: 0 0 25px rgba(46, 139, 87, 0.8);
  }
}
</style>
