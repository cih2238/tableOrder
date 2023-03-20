package com.study.tableOrder.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

	@ResponseBody
	@PostMapping("/tableNumberConfig.do")
	public void tableNumberConfigDo(int tableNumber, HttpSession session) {
		System.out.println(tableNumber);
		session.setAttribute("tableNumber", tableNumber);
	}
}
