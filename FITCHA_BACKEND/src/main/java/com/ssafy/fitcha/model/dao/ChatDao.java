package com.ssafy.fitcha.model.dao;

import java.util.List;

import com.ssafy.fitcha.model.dto.ChatMessage;
import com.ssafy.fitcha.model.dto.ChatRoom;

public interface ChatDao {
	List<ChatRoom> getRooms();

	void createRoom(ChatRoom room);

	ChatRoom getRoom(Long roomId);

	List<ChatMessage> getMessages(Long roomId);

	void sendMessage(ChatMessage message);

	ChatRoom findRoomByName(String name);
}
