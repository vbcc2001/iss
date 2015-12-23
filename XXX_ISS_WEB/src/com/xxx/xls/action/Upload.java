package com.xxx.xls.action;


import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xxx.iss.http.action.ActionResult;
import com.xxx.iss.http.action.BaseAction;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class Upload extends BaseAction {

	public ActionResult doDefault() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
			        this.getWriter().print("{\"name\":\""+fileName+"\"}");
			        //String contentType = item.getContentType();
			        //boolean isInMemory = item.isInMemory();
			        //long sizeInBytes = item.getSize();
			        // Process a file upload
			        boolean writeToFile =true;
			        if (writeToFile) {
			            //File uploadedFile = new File("D:/iss/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
			            File uploadedFile = new File("C:/XXX_ISS_DEV/XXX_ISS_WEB/WebRoot/xls/csv/1.csv");
			            item.write(uploadedFile);
			        } else {
			            //InputStream uploadedStream = item.getInputStream();
			            //...
			            //uploadedStream.close();
			        }
			    }
			}
		}
		
		return null;
	}   
}
