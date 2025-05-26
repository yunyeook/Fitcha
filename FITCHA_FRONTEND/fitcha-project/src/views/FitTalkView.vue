<template>
  <div class="chat-room-list">
    <h2>💬 채팅방 목록</h2>
    <div class="chat-room-input">
      <input v-model="newRoom" placeholder="채팅방 이름 입력" />
      <button @click="createRoom">➕ 채팅방 생성</button>
    </div>

    <ul>
      <li v-for="room in filteredRooms" :key="room.id">
        <span>{{ room.name }}</span>
        <router-link :to="`/fittalk/room/${room.id}`">입장</router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>
import api from '@/api/api';
import { ref, onMounted, computed } from 'vue';

const rooms = ref([]);
const newRoom = ref('');

const filteredRooms = computed(() => (Array.isArray(rooms.value) ? rooms.value.filter(r => r?.name) : []));

async function loadRooms() {
  try {
    const { data } = await api.get('/api/chat/rooms');
    console.log('rooms 응답:', data);

    rooms.value = data;
  } catch (err) {
    console.error('방 목록 불러오기 실패:', err);
  }
}

async function createRoom() {
  console.log('버튼클릭함');
  const name = newRoom.value.trim();
  if (!name) return;
  console.log('채팅방 생성 요청:', name); // ✅ 이거 찍어보세요
  try {
    await api.post('/api/chat/rooms', { name });
    newRoom.value = '';
    await loadRooms();
  } catch (err) {
    console.error('채팅방 생성 실패:', err);
  }
}

onMounted(loadRooms);
</script>

<style scoped>
.chat-room-list {
  padding: 20px;
  max-width: 600px;
  margin: 0 auto;
}

.chat-room-input {
  display: flex;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 20px;
}

.chat-room-input input {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.chat-room-input button {
  padding: 8px 12px;
  background-color: #40c057;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.room-list {
  list-style: none;
  padding: 0;
}

.room-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 16px;
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-bottom: 8px;
  background-color: #f8f9fa;
}
.enter-btn {
  padding: 6px 12px;
  background-color: #40c057;
  color: white;
  border-radius: 6px;
  text-decoration: none;
}
</style>
