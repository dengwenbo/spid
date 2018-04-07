package com.cn.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("index")
    public String index(HttpServletRequest request,HttpServletResponse response,Model model) {
    	
      model.addAttribute("welcome", "欢迎来到网络爬虫");
      return "/index";
      
    }

}
