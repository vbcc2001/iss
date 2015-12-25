package com.xxx.xls.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		
		BufferedWriter bufferWritter = null;
		try{
			response.setContentType("application/csv; charset=GBK");
			response.setHeader("Content-disposition","filename=\"3.csv\"");
			/* 读取一个文件 */
			//HashMap<String,HashMap<String,String>> f1 = get("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
			//HashMap<String,HashMap<String,String>> f2 = get("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");  
			HashMap<String,HashMap<String,String>> f1 = get("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
			HashMap<String,HashMap<String,String>> f2 = get("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");
			//File f3 = new File("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/3.csv");
			//File f3 = new File("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/3.csv");
			//if(!f3.exists()){  f3.createNewFile();   }
			//FileWriter fileWritter = new FileWriter(f3.getName(),true);
			//bufferWritter = new BufferedWriter(fileWritter);
		      
			//将f2合并到f1
			for(String key : f1.keySet()){
				//System.out.println( "f1==="+key+":"+f1.get(key));
				if(f2.get(key)!=null){
					//System.out.println( "f2==="+key+":"+f2.get(key));
					for( String key2 : f2.get(key).keySet()){
						f1.get(key).put(key2,f2.get(key).get(key2));
					}
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
					//bufferWritter.write(title+"\n");
					this.getWriter().println(title);
				}
				String line = ""+count;
				for(String key2 : f1.get(key).keySet()){
					line+=","+f1.get(key).get(key2);
				}
				//bufferWritter.write(line+"\n");
				this.getWriter().println(line);
			}
		}catch(Exception e){
			throw e ;
		}finally {
			if(bufferWritter!=null){
			    bufferWritter.close();
			}
		}
		//return new ActionResult("/xls/csv/3.csv");
		return null;
	} 
	/**
	 * 将CSV文件转换成Map对象
	 * @param pathname
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,HashMap<String,String>> get(String pathname ) throws Exception {
		BufferedReader br =null;
		HashMap<String,HashMap<String,String>> main =  new HashMap<String,HashMap<String,String>>();
		try {
			File filename = new File(pathname); // 要读取以上路径的input。txt文件
			// 建立一个输入流对象reader
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String title =this.getLine(br,0); 
			String[] titles =  title.split(",");
			String line = "";
			while (line != null) {
				line = this.getLine(br,0); // 一次读入一行数据
				if(line != null){
					String[] lines =  line.split(",",titles.length);
					HashMap<String,String> sub =  new HashMap<String,String>();
					for(int i=0; i<titles.length; i++){
						if(i<titles.length){
							if(titles[i] !=null && !"".equals(titles[i])){
								if(lines[i]==null || "".equals(lines[i])){
									sub.put(titles[i], " ");
								}else{
									if("身份证号".equals(titles[i])){
										sub.put(titles[i], "@#@"+lines[i]+"@#@");
									}else{
										sub.put(titles[i], lines[i]);
									}
								}
							}
						}
					}
					main.put(sub.get("编号"), sub);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(br!=null) {
				br.close();
			}
		}
		main.remove("");
		main.remove(null);
		return main;
	}
	/**
	 * 读取一行数据，当有单个\" 号时候多读一行。
	 * @param br 
	 * @param x : \" 的个数
	 * @return
	 * @throws Exception
	 */
	public String getLine(BufferedReader br ,int x) throws Exception {
		String line = br.readLine(); 
		if(line != null){
	          //遍历数组的每个元素    
	          for(int i=0;i<=line.length()-1;i++) {  
	              String getstr=line.substring(i,i+1);  
	              if(getstr.equals("\"")){  
	                  x++;  
	              }  
	          } 
		}
        if( x%2==1){
          System.out.println(line);
      	  line =line+this.getLine(br,x); 
          System.out.println("------------------------------------------");
          System.out.println(line);
        }
        return line;
	}
}
