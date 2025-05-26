package com.ssafy.fitcha.model.service;

import java.util.List;

import com.ssafy.fitcha.model.dto.ChatMessage;
import com.ssafy.fitcha.model.dto.ChatRoom;

public interface ChatService {

	List<ChatRoom> getRooms();

	void createRoom(ChatRoom room);

	ChatRoom getRoom(Long roomId);

	List<ChatMessage> getMessages(Long roomId);

	void sendMessage(ChatMessage message);

}
