package com.xxx.iss.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;
/**
 * 患者信息修改
 * @author men
 *
 */
public class F000009 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String pat_id = requestParameter.getParams().get("pat_id");			
		String pat_name = requestParameter.getParams().get("pat_name");
		String pat_age = requestParameter.getParams().get("pat_age");
		if(StringHelper.isEmpty(pat_age)){
			 requestParameter.getParams().put("pat_age","0");
		}
		if(StringHelper.isEmpty(pat_id)){
			response.setErrorNo("-3");
			response.setErrorInfo("用户ID错误！");
		}else if(StringHelper.isEmpty(pat_name)){
			response.setErrorNo("-1");
			response.setErrorInfo("名称不能为空！");
		}else{
			requestParameter.getParams().put("user_code", pat_id);
			if(this.update(requestParameter.getParams())>0 && this.update2(requestParameter.getParams())>0){
					List<DataRow> list = new ArrayList<DataRow>();
					DataRow dataRow = new DataRow();
					dataRow.put("id", pat_id);
					list.add(dataRow);
					response.setErrorNo("");
					response.setErrorInfo("修改成功！");
					response.setList(list);
			}else{
				response.setErrorNo("-2");
				response.setErrorInfo("修改失败！");
			}
		}
		return response;
	}
	public int update( Map<String,String> para) throws SQLException {	
		Object arg[] = new Object[5];
		arg[0]=para.get("pat_name");
		arg[1]=para.get("pat_sex");
		arg[2]=Integer.parseInt(para.get("pat_age"));
		arg[3]=para.get("pat_phone");
		arg[4]=para.get("user_code");
		String sql="update t_user_info set name=?,sex=?,age=?,phone=? where user_code=?";
		return getNewJdbcTemplate().update(sql,arg);
	}
	public int update2( Map<String,String> para) throws SQLException {	
		Object arg[] = new Object[10];
		arg[0]=para.get("pat_name");
		arg[1]=para.get("pat_sex");
		arg[2]=Integer.parseInt(para.get("pat_age"));
		arg[3]=para.get("pat_add");
		arg[4]=para.get("pat_phone");
		arg[5]=para.get("pat_ill_date");
		arg[6]=para.get("pat_ill_type");
		arg[7]=para.get("pat_hospital");
		arg[8]=para.get("user_code");
		arg[9]=para.get("user_code");
		String sql="update t_patient_info set name=?,sex=?,age=?,address=?,phone=?,ill_date=?,ill_type=?,hospital=? where user_code=? and patient_code=? ";
		return getNewJdbcTemplate().update(sql,arg);
	}	
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		RequestParameter req = new RequestParameter();
		req.addParameter("pat_id", "714242d4d15be7a229d2a0e2572b6286");
		req.addParameter("pat_name", "etest");
		F000009 f = new F000009();
		System.out.println( f.execute(req));
	}
}
