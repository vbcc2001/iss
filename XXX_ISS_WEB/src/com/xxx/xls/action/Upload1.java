package com.xxx.xls.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xxx.iss.http.action.ActionResult;
import com.xxx.iss.http.action.BaseAction;

/**
 * 文件上传
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class Upload1 extends BaseAction {

	public ActionResult doDefault() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String,Object> result = new HashMap<String,Object>();
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletContext servletContext = this.getSession().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (item.isFormField()) {
			        //processFormField(item);
			    } else {
			        //processUploadedFile(item);
			    	//String fieldName = item.getFieldName();
			        String fileName = item.getName();
			        result.put("name",fileName );
			        //String contentType = item.getContentType();
			        //boolean isInMemory = item.isInMemory();
			        //long sizeInBytes = item.getSize();
			        // Process a file upload
			        boolean writeToFile =true;
			        if (writeToFile) {
			            File uploadedFile = new File("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");
			            //File uploadedFile = new File("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/2.csv");
			            item.write(uploadedFile);
			        } else {
			            //InputStream uploadedStream = item.getInputStream();
			            //...
			            //uploadedStream.close();
			        }
			    }
			}
		}
		//String pathname  = "C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/2.csv";
		String pathname  = "D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/2.csv";
		File filename = new File(pathname); // 要读取以上路径的input。txt文件
		// 建立一个输入流对象reader
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br =null;
		try {
			br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String title = br.readLine(); 
			String[] titles =  title.split(",");
			result.put("titles", titles);
		} catch (Exception e) {
			throw e;
		} finally {
			if(br!=null) {
				br.close();
			}
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		String json =  gson.toJson(result);
		this.getWriter().print( json);
		return null;
	}
}
