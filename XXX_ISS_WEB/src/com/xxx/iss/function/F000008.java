package com.xxx.iss.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;
/**
 * 医生信息修改
 * @author men
 *
 */
public class F000008 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
			
		String doc_id = requestParameter.getParams().get("doc_id");	
		String doc_name = requestParameter.getParams().get("doc_name");
		if(StringHelper.isEmpty(doc_id)){
			response.setErrorNo("-3");
			response.setErrorInfo("用户ID错误！");
		}else if(StringHelper.isEmpty(doc_name)){
			response.setErrorNo("-1");
			response.setErrorInfo("名称不能为空！");
		}else{
			requestParameter.getParams().put("user_code", doc_id);
			if(this.updateDoctor(requestParameter.getParams())>0){
				List<DataRow> list = new ArrayList<DataRow>();
				DataRow dataRow = new DataRow();
				dataRow.put("id", doc_id);
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
	public int updateDoctor( Map<String,String> para) throws SQLException {	
		String arg[] = new String[8];
		arg[0]=para.get("doc_name");
		arg[1]=para.get("doc_hospital");
		arg[2]=para.get("doc_room");
		arg[3]=para.get("doc_job");
		arg[4]=para.get("doc_tel");
		arg[5]=para.get("doc_email");
		arg[6]=para.get("doc_phone");
		arg[7]=para.get("user_code");
		String sql="update t_user_info set name=?,com=?,dept=?,post=?,tel=?,email=?,phone=? where user_code=?";
		return getNewJdbcTemplate().update(sql,arg);
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		RequestParameter req = new RequestParameter();
		req.addParameter("doc_id", "057ed3da24f4b03d776ecd8e0e40c09f");
		req.addParameter("doc_name", "etest");
		F000008 f = new F000008();
		System.out.println( f.execute(req));
	}
}
