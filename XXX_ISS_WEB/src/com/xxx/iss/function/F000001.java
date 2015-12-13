package com.xxx.iss.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;

public class F000001 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String doc_name = requestParameter.getParams().get("doc_name");
		if(StringHelper.isEmpty(doc_name)){
			response.setErrorNo("-1");
			response.setErrorNo("名称不能为空！");
		}else{
			//生成一个sessionID
			String sessionID =StringHelper.getMD5(doc_name+String.valueOf(new Date().getTime()));
			requestParameter.getParams().put("weixin_open_id", sessionID);
			if(this.insertDoctor(requestParameter.getParams())>0){
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
	public int insertDoctor( Map<String,String> para) throws SQLException {	
		String arg[] = new String[9];
		arg[0]=para.get("doc_name");
		arg[1]=para.get("doc_hospital");
		arg[2]=para.get("doc_room");
		arg[3]=para.get("doc_job");
		arg[4]=para.get("doc_tel");
		arg[5]=para.get("doc_email");
		arg[6]=para.get("doc_phone");
		arg[7]=para.get("weixin_open_id");
		arg[8]=para.get("weixin_open_id");
		String sql="INSERT INTO t_user_info(name,com,dept,post,tel,email,phone,user_type,user_code,weixin_open_id,weixin_id) VALUES (?,?,?,?,?,?,?,'doctor',?,?,'test')";
		return getNewJdbcTemplate().update(sql,arg);
	}
	public boolean isLogin(String sessionID) throws SQLException{
		String sql="select * from  t_user_info where weixin_open_id = ?";
		return getNewJdbcTemplate().query(sql, new String[]{sessionID}).size()>0;
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000001 f000001 = new F000001();
		f000001.isLogin("");
	}
}
