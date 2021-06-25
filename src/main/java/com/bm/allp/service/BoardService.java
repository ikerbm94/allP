package com.bm.allp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.allp.model.Board;
import com.bm.allp.model.User;
import com.bm.allp.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// 누가 글을 작성했는지 알기위해 User도 파라미터값으로 받는다
	@Transactional
	public void 글작성(Board requestBoard, User user) {

		// 조회수는 0으로 설정
		requestBoard.setCount(0);
		// 글 작성자에 대한 정보를 생성
		requestBoard.setUser(user);
		// 글 등록
		boardRepository.save(requestBoard);

	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패! 다음의 Id 값을 찾을 수 없습니다 : " + id);
		});
	}

	@Transactional
	public void 글수정(int id, Board updateBoard) {
		
		// 영속화
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패! 다음의 Id 값을 찾을 수 없습니다 : " + id);
		});
		
		board.setTitle(updateBoard.getTitle());
		board.setContent(updateBoard.getContent());
		
	}
	
	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}

}