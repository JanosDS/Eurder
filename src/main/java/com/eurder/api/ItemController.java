package com.eurder.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

	@GetMapping(path = "/hello")
	public void getHello(){
		System.out.println("hello");
	}
}
