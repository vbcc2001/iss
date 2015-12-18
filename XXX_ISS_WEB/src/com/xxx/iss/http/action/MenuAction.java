package com.xxx.iss.http.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 微信认证
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class MenuAction extends BaseAction {

	public ActionResult doDefault() throws UnsupportedEncodingException {
		String secret = "9e6ec87af00ea0bc3039b1a601eb7a22";
		String appid = "wx91f0fb6fe746a4bc";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		String result = this.sendPost(url, "".getBytes());
		logger.info(result);
		Gson gson = new GsonBuilder().serializeNulls().create();
		@SuppressWarnings("unchecked")
		Map<String,String> params = (Map<String,String>)gson.fromJson(result, new TypeToken<Map<String,String>>() {}.getType());   
		String access_token =params.get("access_token");
		logger.info(access_token);
		url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		String  button_url = "https://open.weixin.qq.com/connect/oauth2/authorize"
				+ "?appid=wx91f0fb6fe746a4bc&redirect_uri=http%3A%2F%2Fwww.m-trauma.com/action/iss/http/action/OpenIdAction"
				+ "&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
		String  body= " {\"button\":[{ \"type\":\"view\",\"name\":\"ISS评分\",\"url\":\""+button_url+"\"}] }";
		
		//{"button":[{ "type":"view","name":"ISS评分","url":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx91f0fb6fe746a4bc&redirect_uri=http%3A%2F%2Fwww.m-trauma.com/action/iss/http/action/OpenIdAction&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirec"}] }
		//[web] 2015-12-18 10:46:12 [INFO ] (MenuAction:28) {"access_token":"9jax85VapdeYS3BtzROfOCJptgWA5-SdAjjm0lerEOHs1oYh2sBb7j6ET3t8M51pv89zLp2ujb9aceDlJe9mH94ju3bQTLsf85eByNZZ2KcHRYcAGAAKR","expires_in":7200}
		
		result = this.sendPost(url, body.getBytes("UTF-8"));
		
		logger.info(result);
		this.getWriter().print(result);
		return null;
	}
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, byte[] param) {
    	OutputStream os = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            os = conn.getOutputStream();
            os.write(param);//传入参数    
            os.flush();
            os.close();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(os!=null){
                	os.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
}
