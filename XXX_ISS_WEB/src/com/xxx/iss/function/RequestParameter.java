package com.xxx.iss.function;

import java.util.Map;
import java.util.HashMap;
/**
 * 请求参数的服务类
 * @author 门士松  20121031
 * @version 1.0
 * @since
 */
public class RequestParameter {
	
	private String flowNo = "";	    	//请求流水号
    private String funcNo = "";	    	//功能号码
    private String clientIP = "";	//客户端IP
    private String userID = "";		//用户ID
    private String sessionID = "";	//SessionID	  
    private String basePath = "";	//请求的路径
	private Map<String,String> params = new HashMap<String,String>(); //全部请求参数	
	
    /**
    * 请求路径
    */    	
    public String getBasePath() {
		return basePath;
	}
    /**
    * 请求路径
    */        
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}	
    /**
    * 流水编号
    */    
	public String getFlowNo() {
		return flowNo;
	}
    /**
    * 流水编号
    */ 	
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
    /**
    * 功能编号
    */ 	
	public String getFuncNo() {
		return funcNo;
	}
    /**
    * 流水编号
    */ 
	public void setFuncNo(String funcNo) {
		this.funcNo = funcNo;
	}
    /**
    * 客户IP
    */ 
	public String getClientIP() {
		return clientIP;
	}
    /**
    * 客户IP
    */ 	
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
    /**
    * 客户ID
    */ 	
    public String getUserID() {
		return userID;
	}
    /**
    * 客户ID
    */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
    * 服务器的SessionID
    */ 	
	public String getSessionID() {
		return sessionID;
	}
    /**
    * 服务器的SessionID
    */ 		
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
    /**
    * 全部请求参数Map
    */ 		
	public Map<String, String> getParams() {
		return params;
	}
    /**
    * 全部请求参数Map
    */ 		
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
    /**
     * 获得请求参数
     */
    public String getStrParameter(String name){
        Object obj = params.get(name);
        return (obj == null) ? null : obj.toString();
    }
    /**
     * 获得请求参数
     */    
    public Integer getIntParameter(String name){
    	Integer value = null;
        Object obj = params.get(name);
        if (obj instanceof Integer) {
            value = (Integer) obj;        	
        } else {
        	value = Integer.parseInt(obj.toString());
        }
        return value;
    }
    /**
     * 获得请求参数
     */    
    public Boolean getBooleanParameter(String name){
    	Boolean value = null;
        Object obj = params.get(name);
        if (obj instanceof Boolean) {
            value = (Boolean) obj;
        } else {
        	value = Boolean.valueOf(obj.toString());       	
        }
        return value;
    }   
    /**
     * 添加请求参数
     */
    public void addParameter(String name, String value){
        params.put(name, value);
    }
}
