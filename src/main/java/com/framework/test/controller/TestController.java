package com.framework.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/download")
	public void hello(HttpServletRequest req, HttpServletResponse resp) throws Exception 
	{
		System.out.println("download call....");
		FileOutputStream fos = null;
		FileInputStream fis = null;
		OutputStream os = null;
		
		byte[] temp = new byte[1024];
		int length = 0;
		
		String fileName = "C://DevPJT/workspace/test2/lib/000310_20180750000231.hwp"; 
		
		File file = new File(fileName);
		fis = new FileInputStream(file);
		
		try {
			os = resp.getOutputStream();
			resp.setHeader("Transfer-Encoding", "chunked");  
			resp.setContentType("application/octet-stream");
			
			String s1 = "000310_20180750000231.hwp"; 
			String len = Integer.toHexString(s1.length());
			os.write(len.getBytes());   
			os.write(13);
			os.write(10);
			os.write(s1.getBytes());  
			os.write(13);
			os.write(10);
			s1 = Integer.toHexString((int)(file.length())); 
			System.out.println("file length => " + file.length());
			os.write(s1.getBytes());   
			os.write(13);
			os.write(10);
			
			while((length = fis.read(temp)) > 0) {
				os.write(temp, 0, length);
			}
			os.write(13); 
			os.write(10);
			os.flush();
			System.out.println("file write end...");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			fis.close();
			os.close();
		}
	}
}
