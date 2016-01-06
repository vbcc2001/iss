package com.xxx.iss.function;

import java.util.List;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;
/**
 * 查询患者信息
 * @author men
 *
 */
public class F000011 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String pat_id = requestParameter.getParams().get("pat_id");
		if(StringHelper.isEmpty(pat_id)){
			response.setErrorNo("-1");
			response.setErrorInfo("用户ID错误！");
		}else{
			String sql ="SELECT t.name,t.sex,t.age,t.phone,"
					+ "t1.address,t1.hospital,t1.ill_date,t1.ill_type "
				+ "FROM  t_user_info t,t_patient_info t1 where t.user_code=t1.patient_code and t.user_code=?";
			List<DataRow> list = this.getNewJdbcTemplate().query(sql,new String[]{pat_id});
			if(list!=null && list.size()>0){
				response.setList(list);
			}else{
				response.setErrorNo("-2");
				response.setErrorInfo("未查询到用户信息！");
			}
		}
		return response;
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000011 f = new F000011();
		RequestParameter req = new RequestParameter();
		req.addParameter("pat_id", "ebd58d1660aa95e38d4d6de92a6233a7");
		System.out.println( f.execute(req)    );
	}
}
