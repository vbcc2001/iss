package com.xxx.iss.function;


import java.util.ArrayList;
import java.util.List;

import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;
/**
 * 查询医生/患者使用计数
 * @author men
 *
 */
public class F000007 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String weixin_open_id = requestParameter.getParams().get("weixin_open_id");
		String user_type = requestParameter.getParams().get("user_type");
		String sql ="SELECT user_code FROM  t_user_info where weixin_open_id=? and user_type=? order by create_time DESC";
		String id = this.getNewJdbcTemplate().queryString(sql,new String[]{weixin_open_id,user_type});
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow dataRow = new DataRow();
		if(id!=null && !id.isEmpty()){
			dataRow.put("id",id );
		}else{
			response.setErrorNo("-2");
			response.setErrorInfo("未注册");
		}
		list.add(dataRow);
		response.setList(list);
		return response;
	}

	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000007 f = new F000007();
		RequestParameter req = new RequestParameter();
		req.addParameter("weixin_open_id", "ofKk3s-vqmvGB8PQGmSmlvSLRnO4");
		req.addParameter("user_type", "doctor");
		System.out.println( f.execute(req) );
	}
}
