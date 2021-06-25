package com.bm.allp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bm.allp.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// size = 3 => 한 페이지에 3개의 글을 띄운다
	// sort = "id" => 기준은 id 값으로 (PK)
	// direction = Sort.Direction.DESC => 최신순으로 글을 띄운다
	// http://localhost:8000
	// http://localhost:8000/
	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));

		// viewResolvcer에 의해 boards값이 index.jsp로 넘어간다
		// /WEB-INF/views/index.jsp
		return "index";
	}

	// 맵핑 주소가 "/auth/**"가 아니기 때문에 USER의 권한이 필요
	// http://localhost:8000/board/saveForm
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/detail/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/updateForm/{id}") 
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}


}