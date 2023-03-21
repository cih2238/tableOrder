package com.study.tableOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.tableOrder.service.BoardSerivce;

@Controller
public class MainController {

	@Autowired
	BoardSerivce boardService;

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
	public String goOrder(Model model) {
		model.addAttribute("List", boardService.getBoard());
		return "/order";
	}

	@GetMapping("/upload")
	public String goUpload() {
		return "/admin/upload";
	}

}
