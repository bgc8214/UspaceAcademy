package com.uspaceacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FAQController
{
	
	@RequestMapping("/insert")
	public String insert()
	{
		//검증하는 것
		//서비스를 불러서 실제 업무처리
		
		return "FAQ/FAQ_list.tiles";
	}
}
