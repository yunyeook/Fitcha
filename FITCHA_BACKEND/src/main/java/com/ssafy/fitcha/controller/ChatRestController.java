package com.ssafy.fitcha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.ChatMessage;
import com.ssafy.fitcha.model.dto.ChatRoom;
import com.ssafy.fitcha.model.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@GetMapping("/rooms")
	public List<ChatRoom> getRooms() {
		return chatService.getRooms();
	}

	@PostMapping("/rooms")
	public void createRoom(@RequestBody ChatRoom room) {
		chatService.createRoom(room);
	}

	@GetMapping("/rooms/{roomId}")
	public ChatRoom getRoom(@PathVariable Long roomId) {
		return chatService.getRoom(roomId);
	}

	@GetMapping("/messages/{roomId}")
	public List<ChatMessage> getMessages(@PathVariable Long roomId) {
		return chatService.getMessages(roomId);
	}

}
