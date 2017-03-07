package com.test.huc;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String getURL(Model model, MultipartFile file, String id, String name) {
		System.out.println("/getFile 들어옴!");
		System.out.println("file:"+file);
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
	
	@RequestMapping(value = "/getParamAndFile", method = RequestMethod.POST)
	public void getData(Model model, MultipartFile file, String id, String name, String test) {
		System.out.println("/getData 들어옴!");
		System.out.println("fileName:"+file.getName());
		System.out.println("fileName:"+file.getOriginalFilename());
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		System.out.println("test:"+test);
		
		//파일생성경로 테스트하려면 변경해서 사용
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
		return ;
	}
	
	// json타입으로 받아 출력하고 json타입으로 response에 담아 응답한다.
	@ResponseBody
	@RequestMapping(value = "/getJson", method = RequestMethod.POST)
	public String getJson(Model model, String jsonStr) {
		System.out.println("/getJson 들어옴!");
		System.out.println("jsonStr:"+jsonStr);
		model.addAttribute("jsonStr", jsonStr);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		String result = "";
		try {
			//전달받은 String타입을 json타입으로 변환해서 전달받은 매개변수값 가져옴
			jsonObj = (JSONObject) parser.parse(jsonStr);
			String id = (String) jsonObj.get("id");
			String name = (String) jsonObj.get("name");
			System.out.println("id:"+id);
			System.out.println("name:"+name);
			
			//json타입으로 결과를 담아 response를 통해 응답
			JSONObject returnJson = new JSONObject();
			returnJson.put("result", "success");
			result = returnJson.toJSONString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
