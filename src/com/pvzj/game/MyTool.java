package com.pvzj.game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class MyTool {
	public static void CopyFile(File srcFile, File desFile) throws IOException {
		// 使用缓冲字节流进行文件复制
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
		byte[] b = new byte[1024];
		Integer len = 0;
		//一次读取1024字节的数据
		while((len = bis.read(b)) != -1) {
		bos.write(b, 0, len);
		}
		bis.close();
		bos.close();
		}
	public static String readText(File f) {
		String reString = "";
		try {
			Scanner sc=new Scanner(f);
			while(sc.hasNext()) {
				reString=reString+sc.next();
			}
			sc.close();
			return reString;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("readText:读取文件失败!!");
			e.printStackTrace();
			new ImageIcon(toAbsolutePath("assets/biology/botany/wwss/wdss.json"));			
		}
		return reString;
	}
	public static String toAbsolutePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return file.getAbsolutePath();
	}
}
