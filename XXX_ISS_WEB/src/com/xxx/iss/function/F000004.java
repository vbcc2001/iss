package com.xxx.iss.function;

import com.xxx.iss.jdbc.DBConfigure;
public class F000004 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {

			String click_content = requestParameter.getParams().get("click_content");
			String sql ="insert into t_click_count(click_content) values(?)";
			this.getNewJdbcTemplate().update(sql,new String[]{click_content});
			return response;
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000004 f = new F000004();
		RequestParameter req = new RequestParameter();
		req.addParameter("click_content", "患者使用");
		f.execute(req);
	}
}
