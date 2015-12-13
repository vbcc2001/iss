package com.xxx.iss.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;

public class F000002 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String pat_name = requestParameter.getParams().get("pat_name");
		String pat_age = requestParameter.getParams().get("pat_age");
		if(StringHelper.isEmpty(pat_age)){
			 requestParameter.getParams().put("pat_age","0");
		}
		if(StringHelper.isEmpty(pat_name)){
			response.setErrorNo("-1");
			response.setErrorNo("名称不能为空！");
		}else{
			//生成一个sessionID
			String sessionID =StringHelper.getMD5(pat_name+String.valueOf(new Date().getTime()));
			requestParameter.getParams().put("weixin_open_id", sessionID);
			if(this.insert(requestParameter.getParams())>0){
				List<DataRow> list = new ArrayList<DataRow>();
				DataRow dataRow = new DataRow();
				dataRow.put("id", sessionID);
				list.add(dataRow);
				response.setList(list);;
			}else{
				response.setErrorNo("-2");
				response.setErrorNo("登记失败！");
			}
		}
		return response;
	}
	public int insert( Map<String,String> para) throws SQLException {	
		Object arg[] = new Object[7];
		arg[0]=para.get("pat_name");
		arg[1]=para.get("pat_sex");
		arg[2]=Integer.parseInt(para.get("pat_age"));
		arg[3]=para.get("pat_add");
		arg[4]=para.get("pat_phone");
		arg[5]=para.get("weixin_open_id");
		arg[6]=para.get("weixin_open_id");
		//arg[7]=para.get("pat_ill_date");
		//arg[8]=para.get("pat_ill_type");
		//arg[9]=para.get("pat_hospital");
		String sql="INSERT INTO t_user_info(name,sex,age,com,phone,user_type,user_code,weixin_open_id,weixin_id) VALUES (?,?,?,?,?,'patient',?,?,'test')";
		return getNewJdbcTemplate().update(sql,arg);
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		//F000002 f000002 = new F000002();
	}
}
