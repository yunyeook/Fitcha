<template>
  <div class="chat-room-wrapper">
    <h2>{{ roomTitle }}</h2>
    <div class="chat-messages" ref="messageBox">
      <div v-for="(msg, index) in messages" :key="index" class="chat-message">
        <strong>{{ msg.sender }}:</strong> {{ msg.message }}
      </div>
    </div>
    <div class="chat-input">
      <input v-model="input" type="text" placeholder="메시지를 입력하세요" @keyup.enter="sendMessage" />
      <button @click="sendMessage">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, nextTick } from 'vue';
import { useRoute } from 'vue-router';
import api, { BASE_URL } from '@/api/api';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { useUserStore } from '@/stores/user';

const route = useRoute();
const roomId = route.params.roomId;
const roomTitle = ref('');
const messages = ref([]);
const input = ref('');
const messageBox = ref(null);
const userStore = useUserStore();
let stompClient = null;

// 채팅방 제목 가져오기
const fetchRoomTitle = async () => {
  try {
    const { data } = await api.get(`/api/chat/rooms/${roomId}`);
    roomTitle.value = data.name ? `채팅방 #${data.name}` : '채팅방';
  } catch (err) {
    console.error('방 제목 로딩 실패:', err);
    roomTitle.value = '채팅방';
  }
};

// 기존 메시지 불러오기
const fetchMessages = async () => {
  try {
    const res = await api.get(`/api/chat/messages/${roomId}`);
    messages.value = res.data;
  } catch (err) {
    console.error('메시지 불러오기 실패:', err);
  }
};

// STOMP/WebSocket 연결 및 구독
const connectSocket = () => {
  const token = localStorage.getItem('access-token');
  const socket = new SockJS(`${BASE_URL}/ws?token=${token}`);

  stompClient = new Client({
    // webSocketFactory OR brokerURL 중 하나는 반드시 제공해야 합니다
    webSocketFactory: () => socket,
    // brokerURL: `ws://localhost:8080/ws?token=${token}`,

    connectHeaders: {
      Authorization: `Bearer ${token}`,
    },
    debug: msg => console.log('[STOMP DEBUG]', msg),
    reconnectDelay: 5000,
    onConnect: () => {
      console.log('✅ STOMP 연결 성공! 구독을 시작합니다.');
      stompClient.subscribe(`/topic/chat/${roomId}`, frame => {
        console.log('📥 GOT FRAME:', frame);
        const parsed = JSON.parse(frame.body);
        console.log('📨 PARSED MSG:', parsed);
        messages.value.push(parsed);
        // 스크롤 맨 아래로
        nextTick(() => {
          if (messageBox.value) {
            messageBox.value.scrollTop = messageBox.value.scrollHeight;
          }
        });
      });
    },
    onStompError: frame => {
      console.error('❌ STOMP 에러:', frame.headers['message']);
    },
    onWebSocketError: ev => {
      console.error('❌ WS 에러:', ev);
    },
  });

  stompClient.activate();
};

// 메시지 전송 함수
function sendMessage() {
  if (!input.value.trim()) return;
  console.log('🖱️ sendMessage 호출, input:', input.value);

  if (!stompClient || !stompClient.connected) {
    return alert('⛔ STOMP 연결이 되지 않았습니다. 잠시 후 다시 시도해주세요.');
  }

  stompClient.publish({
    destination: `/app/chat/${roomId}`,
    body: JSON.stringify({
      roomId: Number(roomId),
      sender: userStore.nickName,
      message: input.value,
    }),
  });

  input.value = '';
}

onMounted(async () => {
  await fetchRoomTitle();
  await fetchMessages();
  connectSocket();
  // 초기 로드 시 스크롤 맨 아래
  nextTick(() => {
    if (messageBox.value) {
      messageBox.value.scrollTop = messageBox.value.scrollHeight;
    }
  });
});

onBeforeUnmount(() => {
  if (stompClient) stompClient.deactivate();
});
</script>

<style scoped>
.chat-room-wrapper {
  max-width: 600px;
  margin: 0 auto;
  padding: 30px;
  background-color: #f5f5f5;
  border-radius: 15px;
}
.chat-messages {
  height: 400px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  background-color: #fff;
  border-radius: 10px;
  margin-bottom: 15px;
}
.chat-message {
  margin-bottom: 10px;
}
.chat-input {
  display: flex;
  gap: 10px;
}
.chat-input input {
  flex: 1;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
}
.chat-input button {
  padding: 10px 20px;
  border: none;
  border-radius: 6px;
  background-color: #40c057;
  color: white;
  cursor: pointer;
}
</style>
