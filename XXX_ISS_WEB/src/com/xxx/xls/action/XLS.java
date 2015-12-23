package com.xxx.xls.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.xxx.iss.http.action.ActionResult;
import com.xxx.iss.http.action.BaseAction;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class XLS extends BaseAction {

	public ActionResult doDefault() throws Exception {
		response.setContentType("application/csv; charset=GBK");
		response.setHeader("Content-disposition","filename=\"3.csv\"");
		/* 读取一个文件 */
		//HashMap<String,HashMap<String,String>> f1 = get("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
		//HashMap<String,HashMap<String,String>> f2 = get("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");
		HashMap<String,HashMap<String,String>> f1 = get("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
		HashMap<String,HashMap<String,String>> f2 = get("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");
		//将f2合并到f1
		for(String key :f1.keySet()){
			for( String key2 :   f2.get(key).keySet()){
				f1.get(key).put(key2,f2.get(key).get(key2));
			}
		}
		int count = 0;
		for(String key :f1.keySet()){
			count ++;
			if(count ==1){
				String title = "行数";
				for(String key2 : f1.get(key).keySet()){
					title+=","+key2;
				}
				this.getWriter().println(title);
			}
			String line = ""+count;
			for(String key2 : f1.get(key).keySet()){
				line+=","+f1.get(key).get(key2);
			}
			this.getWriter().println(line);
		}
		return null;
	}   
	public HashMap<String,HashMap<String,String>> get(String pathname ) throws Exception {
		BufferedReader br =null;
		HashMap<String,HashMap<String,String>> main =  new HashMap<String,HashMap<String,String>>();
		try {
			/* 读入第一个文件 */
			File filename = new File(pathname); // 要读取以上路径的input。txt文件
			// 建立一个输入流对象reader
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String title = br.readLine(); 
			String[] titles =  title.split(",");
			String line = ""; 
			while (line != null) {
				line = br.readLine(); // 一次读入一行数据
				if(line != null){
					System.out.println(line);
					String[] lines =  line.split(",");
					HashMap<String,String> sub =  new HashMap<String,String>();
					for(int i=0; i<lines.length; i++){
						sub.put(titles[i], lines[i]);
					}
					main.put(sub.get("编号"), sub);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null) {
				br.close();
			}
		}
		return main;

	}

}
