package com.cn.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
	
	
	@RequestMapping("uploadfile")
	public String upLoadFile(){
		return "/uploadfile";
   }
   @RequestMapping("downlaodfile")
   public String downloadfile() {
	   return "/downLoad";
   }
}
