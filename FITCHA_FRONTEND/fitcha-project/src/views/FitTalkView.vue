<template>
  <div class="page-wrapper">
    <MainHeader />
    <MainContentSearch />

    <h2>💬 채팅방 목록</h2>
    <div class="chat-room-input">
      <input v-model="newRoom" placeholder="채팅방 이름 입력" @keyup.enter="createRoom" />
      <button @click="createRoom">➕ 채팅방 생성</button>
    </div>

    <NoContent v-if="noContent" />

    <MainDetailLayout v-else>
      <div class="chat-room-list">
        <ul class="room-list">
          <li class="room-item" v-for="room in filteredRooms" :key="room.id">
            <span class="room-name">{{ room.name }}</span>
            <router-link class="enter-btn" :to="`/fittalk/room/${room.id}`">입장</router-link>
            <button v-if="room.writer == nickName" @click="deleteRoom(room.id)">채팅방 삭제</button>
          </li>
        </ul>
      </div>
    </MainDetailLayout>
  </div>
</template>

<script setup>
import MainHeader from '@/components/common/MainHeader.vue';
import MainContentSearch from '@/components/common/MainContentSearch.vue';
import MainDetailLayout from '@/components/common/MainDetailLayout.vue';
import NoContent from '@/components/error/NoContent204.vue';

import api from '@/api/api';
import { ref, onMounted, computed, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user';

const route = useRoute();
const router = useRouter();
const { userId, nickName } = useUserStore();
const rooms = ref([]);
const newRoom = ref('');
const noContent = ref(false);

const filteredRooms = computed(() => (Array.isArray(rooms.value) ? rooms.value.filter(r => r?.name) : []));

async function loadRooms(searchKey, searchWord) {
  try {
    let response = await api.get('/api/chat/rooms', {
      params: {
        key: searchKey,
        word: searchWord,
      },
    });
    rooms.value = response.data;
    noContent.value = rooms.value.length === 0;
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
    await api.post('/api/chat/rooms', { name, writer: nickName });
    newRoom.value = '';
    await loadRooms();
  } catch (err) {
    console.error('채팅방 생성 실패:', err);
  }
}

async function deleteRoom(roomId) {
  try {
    await api.delete(`/api/chat/${roomId}`);

    await loadRooms();
  } catch (err) {
    console.error('채팅방 삭제 실패:', err);
  }
}

onMounted(loadRooms);

// 쿼리 변경 감지 시 재검색
watch(
  () => [route.query.key, route.query.word], // key 또는 word 둘 중 하나라도 바뀌면
  ([newKey, newWord]) => {
    loadRooms(newKey, newWord);
  }
);
</script>
<style scoped>
.chat-room-list {
  padding: 0px 20px 20px;
  max-width: 640px;
  margin: 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

/* 생성 인풋 영역 (기존) */
.chat-room-input {
  display: flex;
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
  padding: 8px 16px;
  background-color: #40c057;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

/* 방 목록을 카드 형태로 */
.room-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.room-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 14px 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  margin-bottom: 12px;
  background-color: #f8f9fa;
  transition: background-color 0.2s, box-shadow 0.2s;
}
.room-item:hover {
  background-color: #e9ecef;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
}

.room-name {
  font-weight: 500;
  color: #495057;
}

/* 댓글 등록 버튼 스타일과 비슷하게 */
.enter-btn {
  padding: 6px 14px;
  background-color: #40c057;
  color: white;
  border-radius: 6px;
  text-decoration: none;
  font-size: 14px;
  transition: background-color 0.2s;
}
.enter-btn:hover {
  background-color: #37b24d;
}
</style>
