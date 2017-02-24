package com.test.huc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("홈");
		HttpURLConnection conn=null;
		try {
			//URL url = new URL("http://localhost/huc/SampleServlet");
			//URL url = new URL("http://loverman85.cafe24.com/test/SampleServlet");
			URL url = new URL("http://loverman85.cafe24.com/bigtower/government/getData");
			/*
			// HTTP 접속 구하기
			conn = (HttpURLConnection) url.openConnection();

			// 리퀘스트 메소드를 POST로 설정
			conn.setRequestMethod("POST");

			// 연결된 connection 에서 출력도 하도록 설정
			conn.setDoInput(true);
			conn.setDoOutput(true);
			System.out.println("out에 쓰기 전");

			// 요청 파라미터 출력
			// - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
			// - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.
			
			OutputStream out = conn.getOutputStream();
			out.write("id=javaking".getBytes());
			out.write("&".getBytes());
			out.write(("name=" + URLEncoder.encode("자바킹", "UTF-8")).getBytes());
			out.flush();
			out.close();
			System.out.println("out에 쓴 후");

			// 응답받은 메시지의 길이만큼 버퍼를 생성하여 읽어들이고, "EUC-KR"로 디코딩해서 읽어들인다.
			BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream(), "EUC-KR" ), conn.getContentLength() );
			 
			String buf;
			 
			// 표준출력으로 한 라인씩 출력
			while( ( buf = br.readLine() ) != null ) {
			System.out.println( buf );
			}
			 
			// 스트림을 닫는다.
			br.close();*/
			 


			
			// 응답 내용(BODY) 구하기
			/*InputStream in = conn.getInputStream();
			System.out.println("1");
			ByteArrayOutputStream out1 = new ByteArrayOutputStream();
			System.out.println("2");
			byte[] buf = new byte[1024 * 8];
			System.out.println("3");
			System.out.println("buf:"+buf);
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				out1.write(buf, 0, length);
			}*/
			// System.out.println(new String(out1.toByteArray(), "UTF-8"));
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
		 
		    conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
		    conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
		 
		    conn.setConnectTimeout(60); //타임아웃 시간 설정 (default : 무한대기)
		 
		    OutputStream os = conn.getOutputStream();
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //캐릭터셋 설정
		    
		    writer.write(
		        "id=아이디테스트" + 
		         "&name=네임테스트" 
		    ); //요청 파라미터를 입력
		    writer.flush();
		    writer.close();
		    os.close();
		 
		    conn.connect();
		 
		    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
		 
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = br.readLine()) != null) {
		        if(sb.length() > 0) {
		            sb.append("\n");
		        }
		        sb.append(line);
		    }
		 
		    System.out.println("response:" + sb.toString());
		 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			// 접속 해제
			conn.disconnect();
			System.out.println("접속해제");
		}
		return "home";
	}

	

}
