package com.ssafy.fitcha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Message;
import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.MessageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	// 메세지 전체 조회.
	@GetMapping
	public ResponseEntity<List<Message>> getMessages(HttpSession session) {
		List<Message> messages = null;
		try {
			User user =(User) session.getAttribute("loginUser");
			messages = messageService.getMessages(user.getNickName());

			if (messages == null || messages.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(messages);
	}

	// 메세지 등록(보내기)
	@PostMapping
	public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
		if (messageService.sendMessage(message))
			return ResponseEntity.status(HttpStatus.CREATED).build();
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	
	// 메세지 삭제
	@DeleteMapping()
	public ResponseEntity<Void> deleteMessage(@ModelAttribute Message message){
		
		if (messageService.deleteMessage(message))
			return ResponseEntity.status(HttpStatus.CREATED).build();
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	

	

}
