package com.test.huc;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleServlet {
	
	@RequestMapping(value = "/SampleServlet", method = RequestMethod.POST)
	public String getURL(String id, String name) throws UnsupportedEncodingException {
		System.out.println("/SampleServlet 들어옴!");
		//req.setCharacterEncoding("utf-8");
		System.out.println("id:" + id);
		System.out.println("name:" + name);
		/*Enumeration<String> paramName = req.getParameterNames();
		while (paramName.hasMoreElements()) {
			String name1 = paramName.nextElement();
			System.out.println(name1 + "=" + req.getParameter(name1));
		}*/
		return "home";
	}
}
