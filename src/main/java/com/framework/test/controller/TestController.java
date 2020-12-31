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
		
		String fileName = "c:/a.pdf";
		
		File file = new File(fileName);
		fis = new FileInputStream(file);
		
		try {
			os = resp.getOutputStream();
			String s1 = "16\r\n";
			os.write(s1.getBytes(), 0, s1.getBytes().length);
			
			s1 = "tesdfafdafda.pdf\r\n";
			os.write(s1.getBytes(), 0, s1.getBytes().length);
			
			s1 = file.length()+"\r\n";
			os.write(s1.getBytes(), 0, s1.getBytes().length);
			
			while((length = fis.read(temp)) > 0) {
				os.write(temp, 0, length);
			}
			os.write(0x00);
			s1 = "\r\n";
			os.write(s1.getBytes(), 0, s1.getBytes().length);
			System.out.println("file write end...");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			fis.close();
			os.close();
		}
	}
}
