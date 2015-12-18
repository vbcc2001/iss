package com.xxx.iss.http.action;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class WeiXinAction extends BaseAction {

	public ActionResult doDefault() {
			/*
			String  signature = getStrParameter("interestId");
			String  timestamp = getStrParameter("timestamp");
			String  nonce = getStrParameter("nonce");
			String  echostr = getStrParameter("echostr");
			
			String token = "ytzq";  
			
			System.out.println(signature);
			System.out.println(timestamp);
			System.out.println(nonce);
			System.out.println(echostr);
			
			//对请求参数和自己的token进行排序，并连接排序后的结果为一个字符串        
			String[] strSet = new String[]{token, timestamp, nonce};       
			java.util.Arrays.sort(strSet);        
			String total = "";        
			for (String string : strSet) { 
				total = total + string;        
			} 
			//SHA-1加密实例        
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");        
			sha1.update(total.getBytes());        
			byte[] codedBytes = sha1.digest();        
			String codedString = new BigInteger(1, codedBytes).toString(16);
			//将加密后的字节数组转换成字符串。参见http://hi.baidu.com/aotori/item/c94813c4f15caa60f6c95d4a        
			if (codedString.equals(signature)) { 
				this.writeMessageToPage(echostr);
			}else{
				this.writeMessageToPage(echostr);
			}
			return null;
			 */
			String echostr = getRequest().getParameter("echostr");
			this.getWriter().print(echostr);
			return null;
	}
}
