package com.bm.allp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.allp.model.Board;
import com.bm.allp.model.Reply;
import com.bm.allp.model.User;
import com.bm.allp.repository.BoardRepository;
import com.bm.allp.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	public void 댓글등록(Reply requestReply, int boardId, User user) {
		
		//영속화
		Board requestBoard = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글등록 실패! 다음의 Id 값을 찾을 수 없습니다 : " + boardId);
		});
		
		requestReply.setBoard(requestBoard);
		requestReply.setUser(user);
		
		replyRepository.save(requestReply);
		
	}

	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

}