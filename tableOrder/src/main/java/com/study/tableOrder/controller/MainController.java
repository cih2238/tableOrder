package com.study.tableOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String goMain() {
		return "/main";
	}

	@GetMapping("/orderList")
	public String goChat() {
		return "/orderList";
	}

	@GetMapping("/order")
	public String goOrder() {
		return "/order";
	}

}
