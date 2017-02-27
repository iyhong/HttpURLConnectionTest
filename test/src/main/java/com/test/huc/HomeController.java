package com.test.huc;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//폼 보여주기
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	//매개변수 받아서 서버통신 실행
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(Dto dto) {
		System.out.println("홈");
		HttpURLConnection conn = null;
		System.out.println("id:"+dto.getId());
		System.out.println("name:"+dto.getName());

		try {
					        
			 URL url = new URL("http://localhost/huc/SampleServlet");
			// URL url = new URL("http://loverman85.cafe24.com/test/SampleServlet");
			// URL url = new URL("http://loverman85.cafe24.com/bigtower/government/getData");

			// 다른방식
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 요청 방식을 설정 (default : GET)

			conn.setDoInput(true); // input을 사용하도록 설정 (default : true)
			conn.setDoOutput(true); // output을 사용하도록 설정 (default : false)

			conn.setConnectTimeout(60); // 타임아웃 시간 설정 (default : 무한대기)

			//OutputStream에 전달할 data 쓰기
			OutputStream os = conn.getOutputStream();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // 캐릭터셋 설정
			
			writer.write("id=" + dto.getId() + "&name=" + dto.getName()+"&file="); // 요청 파라미터를 입력

			//스트림의 버퍼를 비워준다.
			writer.flush();
			writer.close();

			//스트림을 닫아준다.
			os.close();

			/*
			//BufferedWriter를 사용하지 않는 방식으로 OutputStream 쓰기 예제
				// - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
				// - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.
				OutputStream out = conn.getOutputStream();
				out.write("id=javaking".getBytes());
				out.write("&".getBytes());
				out.write(("name=" + URLEncoder.encode("자바킹", "UTF-8")).getBytes());
				out.flush();
				out.close();
			*/
			
			conn.connect();
			
			//응답결과 받아오기
			InputStream in = conn.getInputStream();
			/*BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 캐릭터셋 설정
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				if (sb.length() > 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
			System.out.println("response:" + sb.toString());*/

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 접속 해제
			conn.disconnect();
			System.out.println("접속해제");
		}
		return "home";
	}
	
	//파일전송 폼 보여주기
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String fileUpload() {
		return "file";
	}
	
	//파일전송
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String fileUpload(MultipartFile file) {
		final String boundary = "alskdfj993klnav211dsfa1";
		try {
			InputStream fileInputStream = file.getInputStream();
			//URL설정
			URL connectURL = new URL("http://localhost/huc/getFile");

			//새로운 접속을 연다.
			HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection(); 

			//읽기와 쓰기 모두 가능하게 설정
			conn.setDoInput(true);
			conn.setDoOutput(true);

			//캐시를 사용하지 않게 설정
			conn.setUseCaches(false); 

			//POST타입으로 설정
			conn.setRequestMethod("POST"); 

			//헤더 설정
			conn.setRequestProperty("Connection","Keep-Alive"); 
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary); 

			//Output스트림을 열어
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream()); 
			dos.writeBytes("--" + boundary + "\r\n"); 
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""+ file.getOriginalFilename() +"\"" + "\r\n"); 
			dos.writeBytes("\r\n"); 

			//버퍼사이즈를 설정하여 buffer할당
			int bytesAvailable = fileInputStream.available(); 
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize); 
			byte[] buffer = new byte[bufferSize];
			 
			//스트림에 작성
			int bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
			while (bytesRead > 0) 
			{ 
				// Upload file part(s) 
				dos.write(buffer, 0, bufferSize); 
				bytesAvailable = fileInputStream.available(); 
				bufferSize = Math.min(bytesAvailable, maxBufferSize); 
				bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
			} 
			dos.writeBytes("\r\n"); 
			dos.writeBytes("--" + boundary + "--" + "\r\n"); 
			fileInputStream.close();

			//써진 버퍼를 stream에 출력.  
			dos.flush(); 
			
			//전송. 결과를 수신.
			InputStream is = conn.getInputStream(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "file";
	}
	//파일전송 폼 보여주기
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String fileTest() {
		return "paramAndFile";
	}
	
	//파일전송 폼 보여주기
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String fileTest(String id, String name, MultipartFile file ) {
		System.out.println("fileTest 진입");
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		System.out.println("fileName:"+file.getOriginalFilename());
		Http http = new Http("http://127.0.0.1/huc/getParamAndFile");
		File f = new File(file.getOriginalFilename());
		try {
			file.transferTo(f);
			http.addParam("id", id).addParam("name", name).addParam("file", f).submit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "paramAndFile";
	}
	
}
