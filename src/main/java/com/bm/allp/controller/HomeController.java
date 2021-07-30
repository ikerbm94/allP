package com.bm.allp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"", "/"})
	public String index() {
		// viewResolvcer에 의해 boards값이 index.jsp로 넘어간다
		// /WEB-INF/views/index.jsp
		return "index";
	}

}
