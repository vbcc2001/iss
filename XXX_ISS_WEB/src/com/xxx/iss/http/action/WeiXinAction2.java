package com.xxx.iss.http.action;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class WeiXinAction2 extends BaseAction {

	public ActionResult doDefault() throws Exception {
			//String echostr = getRequest().getParameter("echostr");
			//this.getWriter().print(echostr);
			System.out.println(    this.getBodyString(    this.getRequest().getReader()  ));
			String a = "<xml><ToUserName><![CDATA[ovF0It_KS2cOA8p7fGEu6GRUXKd8]]></ToUserName>"
					+ "<FromUserName><![CDATA[gh_ae38047fd5d4]]></FromUserName>"
					+ "<CreateTime>12345678</CreateTime>"
					+ "<MsgType><![CDATA[text]]></MsgType>"
					+ "<Content><![CDATA[你好]]></Content>"
					+ "</xml>";
			String a1 = "<xml><ToUserName><![CDATA[ovF0It_KS2cOA8p7fGEu6GRUXKd8]]></ToUserName>"
					+ "<FromUserName><![CDATA[gh_ae38047fd5d4]]></FromUserName>"
					+ "<CreateTime>12345678</CreateTime>"
					+ "<MsgType><![CDATA[music]]></MsgType>"
					+ "<Music>"
					+ "<Title><![CDATA[你好]]></Title>"
					+ "<Description><![CDATA[你好]]></Description>"
					+ "<MusicUrl><![CDATA[http://www.m-trauma.com/mp3/1.mp3]]></MusicUrl>"
					+ "<HQMusicUrl><![CDATA[http://www.m-trauma.com/mp3/1.mp3]]></HQMusicUrl>"
					+ "<FuncFlag>0</FuncFlag>"
					+ "</Music></xml>";
			String a2 = "<xml><ToUserName><![CDATA[ovF0It4wAY3yOIiS3BGbTohPOMbw]]></ToUserName>"
					+ "<FromUserName><![CDATA[gh_ae38047fd5d4]]></FromUserName>"
					+ "<CreateTime>12345678</CreateTime>"
					+ "<MsgType><![CDATA[music]]></MsgType>"
					+ "<Music>"
					+ "<Title><![CDATA[你好]]></Title>"
					+ "<Description><![CDATA[你好]]></Description>"
					+ "<MusicUrl><![CDATA[http://www.m-trauma.com/mp3/3.mp3]]></MusicUrl>"
					+ "<HQMusicUrl><![CDATA[http://www.m-trauma.com/mp3/3.mp3]]></HQMusicUrl>"
					+ "<FuncFlag>0</FuncFlag>"
					+ "</Music></xml>";
	
			
			this.getWriter().print(a2);

			return null;
	}   
	public  String getBodyString(BufferedReader br) {
		  String inputLine;
	       String str = "";
	     try {
	       while ((inputLine = br.readLine()) != null) {
	        str += inputLine;
	       }
	       br.close();
	     } catch (IOException e) {
	       System.out.println("IOException: " + e);
	     }
	     return str;
	 }
}
