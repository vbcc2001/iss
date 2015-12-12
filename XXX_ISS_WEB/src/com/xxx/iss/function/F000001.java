package com.xxx.iss.function;

import java.sql.SQLException;

import com.xxx.iss.jdbc.DBConfigure;

public class F000001 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		isLogin("1","1");
		return response;
	}
	public int execute1() throws SQLException {	
		String sql="INSERT INTO t_user_info(weixin_id) VALUES ('1')";
		return getNewJdbcTemplate().update(sql);
	}
	public boolean isLogin(String userID,String sessionID) throws SQLException{
		String sql="select * from  t_user_info";
		return getNewJdbcTemplate().query(sql).size()>0;
	}
	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000001 f000001 = new F000001();
		f000001.execute1();
	}
}
