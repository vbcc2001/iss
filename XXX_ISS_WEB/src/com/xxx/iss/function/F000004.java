package com.xxx.iss.function;

import java.sql.SQLException;
import java.util.Map;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;

public class F000004 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String patient_code = requestParameter.getParams().get("patient_code");
		String iss_score = requestParameter.getParams().get("iss_score");
		if(StringHelper.isEmpty(iss_score)){
			 requestParameter.getParams().put("iss_score","0");
		}
		if(StringHelper.isEmpty(patient_code)){
			response.setErrorNo("-1");
			response.setErrorNo("名称不能为空！");
		}else{
			if(this.update(requestParameter.getParams())>=0 ){
				//无业务处理
			}else{
				response.setErrorNo("-2");
				response.setErrorNo("登记失败！");
			}
		}
		return response;
	}
	public int update( Map<String,String> para) throws SQLException {	
		Object arg[] = new Object[2];
		arg[0]=Integer.parseInt(para.get("iss_score"));
		arg[1]=para.get("patient_code");
		String sql="update t_patient_info set iss_score=? where patient_code=?";
		return getNewJdbcTemplate().update(sql,arg);
	}	
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		//F000002 f000002 = new F000002();
	}
}
