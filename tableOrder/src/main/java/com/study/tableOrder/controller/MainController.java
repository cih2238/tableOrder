package com.study.tableOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String goMain(@RequestParam(value="tableNumber", required=false) Integer tableNumber, Model model) {
		if(tableNumber != null) {
			model.addAttribute("tableNumber", tableNumber);
		}
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
