<template>
  <div class="page-wrapper">
    <MainHeader />
    <MainContentSearch />
    <MainDetailLayout>
      <div class="chat-room-list">
        <h2><i class="fa-solid fa-message"></i> 채팅방 목록</h2>

        <div class="chat-room-input">
          <input
            v-model="newRoom"
            placeholder="새 채팅방 이름"
            @keyup.enter="createRoom"
          />
          <button @click="createRoom">
            <i class="fa-solid fa-paper-plane"></i> 생성
          </button>
        </div>

        <ul class="room-list">
          <li class="room-item" v-for="room in filteredRooms" :key="room.id">
            <!-- room.name 아이콘 -->
            <span class="room-name">
              {{ room.name }}
            </span>
            <router-link class="enter-btn" :to="`/fittalk/room/${room.id}`"
              >입장</router-link
            >
          </li>
        </ul>
      </div>
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainHeader from "@/components/common/MainHeader.vue";
import MainContentSearch from "@/components/common/MainContentSearch.vue";
import MainDetailLayout from "@/components/common/MainDetailLayout.vue";

import api from "@/api/api";
import { ref, onMounted, computed } from "vue";

const rooms = ref([]);
const newRoom = ref("");

const filteredRooms = computed(() =>
  Array.isArray(rooms.value) ? rooms.value.filter((r) => r?.name) : []
);

async function loadRooms() {
  try {
    const { data } = await api.get("/api/chat/rooms");
    console.log("rooms 응답:", data);

    rooms.value = data;
  } catch (err) {
    console.error("방 목록 불러오기 실패:", err);
  }
}

async function createRoom() {
  console.log("버튼클릭함");
  const name = newRoom.value.trim();
  if (!name) return;
  console.log("채팅방 생성 요청:", name); // ✅ 이거 찍어보세요
  try {
    await api.post("/api/chat/rooms", { name });
    newRoom.value = "";
    await loadRooms();
  } catch (err) {
    console.error("채팅방 생성 실패:", err);
  }
}

onMounted(loadRooms);
</script>

<style scoped>
.chat-room-list {
  padding: 12px;
  max-width: 720px;
  margin: 10px auto;
  background: linear-gradient(135deg, #f8f9fa, #ffffff);
  border-radius: 16px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.04);
}

.chat-room-list h2 {
  font-size: 24px;
  margin-bottom: 24px;
  display: flex;
  align-items: center;
  gap: 10px;
  color: #2f9e44; /* 진한 초록 */
  border-bottom: 2px solid #e9ecef;
  padding-bottom: 10px;
}
.chat-room-list h2 i {
  color: #2f9e44;
  font-size: 22px;
}

.chat-room-input {
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #f1f3f5;
  padding: 12px 16px;
  border-radius: 10px;
  margin-bottom: 24px;
}

.chat-room-input input {
  flex: 1;
  padding: 10px 14px;
  border: none;
  border-radius: 8px;
  font-size: 15px;
  background-color: #ffffff;
  color: #212529;
  transition: box-shadow 0.2s;
}
.chat-room-input input:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(64, 192, 87, 0.3);
}

.chat-room-input button {
  padding: 10px 16px;
  background-color: #40c057;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: 500;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: background-color 0.2s;
}
.chat-room-input button:hover {
  background-color: #37b24d;
}

.room-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.room-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px;
  border: 1px solid #e9ecef;
  border-radius: 10px;
  background-color: #ffffff;
  transition: box-shadow 0.2s, transform 0.1s;
}
.room-item:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transform: translateY(-2px);
}

.room-name {
  font-size: 16px;
  font-weight: 600;
  color: #343a40;
  display: flex;
  align-items: center;
  gap: 30px;
}

.icon-room {
  color: #2f9e44;
  font-size: 18px;
}

.enter-btn {
  padding: 8px 16px;
  background-color: #40c057;
  color: white;
  border-radius: 8px;
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  transition: background-color 0.2s;
}
.enter-btn:hover {
  background-color: #37b24d;
}
.chat-room-list {
  padding: 3px 24px 20px;
  max-width: 720px;
  margin: 10px auto;
  background: linear-gradient(135deg, #f8f9fa, #ffffff);
  border-radius: 30px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.04);
}

.chat-room-list h2 {
  font-size: 24px;
  margin-bottom: 24px;
  display: flex;
  align-items: center;
  gap: 25px;
  color: #2f9e44; /* 진한 초록 */
  border-bottom: 2px solid #e9ecef;
  padding-bottom: 10px;
}
.chat-room-list h2 i {
  color: #2f9e44;
  font-size: 22px;
}

.chat-room-input {
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #f1f3f5;
  padding: 12px 16px;
  border-radius: 10px;
  margin-bottom: 24px;
}

.chat-room-input input {
  flex: 1;
  padding: 10px 14px;
  border: none;
  border-radius: 8px;
  font-size: 15px;
  background-color: #ffffff;
  color: #212529;
  transition: box-shadow 0.2s;
}
.chat-room-input input:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(64, 192, 87, 0.3);
}

.chat-room-input button {
  padding: 10px 16px;
  background-color: #fac74f;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: 500;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: background-color 0.2s;
}
.chat-room-input button:hover {
  background-color: #fab005;
}

.room-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.room-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px;
  border: 1px solid #e9ecef;
  border-radius: 10px;
  background-color: #ffffff;
  transition: box-shadow 0.2s, transform 0.1s;
}
.room-item:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transform: translateY(-2px);
}

.room-name {
  font-size: 16px;
  font-weight: 600;
  color: #343a40;
  display: flex;
  align-items: center;
  gap: 8px;
}

.icon-room {
  color: #40c057;
  font-size: 18px;
}

.enter-btn {
  padding: 8px 16px;
  background-color: #20c997;
  color: white;
  border-radius: 8px;
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  transition: background-color 0.2s;
}
.enter-btn:hover {
  background-color: #37b24d;
}

.room-name {
  gap: 16px;
}

.chat-room-list h2 i {
  color: #ffffff;
}

.chat-room-list h2 {
  background: linear-gradient(135deg, #69db7c 0%, #38d9a9 100%);
  border-radius: 30px;
  color: #ffffff;
  height: 50px;
  padding: 15px 40px;
}
</style>
