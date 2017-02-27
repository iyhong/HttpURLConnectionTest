package com.test.huc;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SampleServlet {
	
	@RequestMapping(value = "/SampleServlet", method = RequestMethod.POST)
	public String getURL(String id, String name, Model model) throws UnsupportedEncodingException {
		System.out.println("/SampleServlet 들어옴!");
		System.out.println("id:" + id);
		System.out.println("name:" + name);
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "home";
	}
	
	@RequestMapping(value = "/getFile", method = RequestMethod.POST)
	public String getURL(Model model, MultipartFile file, String id) {
		System.out.println("/getFile 들어옴!");
		System.out.println("file:"+file);
		System.out.println("fileName:"+file.getName());
		System.out.println("fileName:"+file.getOriginalFilename());
		System.out.println("id:"+id);
		//File f= new File("C:\\Users\\user\\Desktop\\"+"new"+file.getOriginalFilename());
		File f= new File("C:\\Users\\Administrator\\Desktop\\"+"new"+file.getOriginalFilename());
		
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "file";
	}
	
	@RequestMapping(value = "/getParamAndFile", method = RequestMethod.POST)
	public String getData(Model model, MultipartFile file, String id, String name) {
		System.out.println("/getData 들어옴!");
		System.out.println("fileName:"+file.getName());
		System.out.println("fileName:"+file.getOriginalFilename());
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		//File f= new File("C:\\Users\\user\\Desktop\\"+"new"+file.getOriginalFilename());
		File f= new File("C:\\Users\\Administrator\\Desktop\\"+"new"+file.getOriginalFilename());
		
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "file";
	}
}
