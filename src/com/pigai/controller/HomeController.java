package com.pigai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	 @RequestMapping(value="/home/homepage")
     public String dispacher()
     {
		 return "/home/homepage";
     }

}
