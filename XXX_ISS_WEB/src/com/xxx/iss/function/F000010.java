package com.xxx.iss.function;

import java.util.List;

import com.xxx.iss.helper.StringHelper;
import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.DataRow;
/**
 * 查询医生信息
 * @author men
 *
 */
public class F000010 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String doc_id = requestParameter.getParams().get("doc_id");
		if(StringHelper.isEmpty(doc_id)){
			response.setErrorNo("-1");
			response.setErrorInfo("用户ID错误！");
		}else{
			String sql ="SELECT name,com,dept,post,phone,email,tel FROM  t_user_info  where user_code=?";
			List<DataRow> list = this.getNewJdbcTemplate().query(sql,new String[]{doc_id});
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
		F000010 f = new F000010();
		RequestParameter req = new RequestParameter();
		req.addParameter("doc_id", "ebd58d1660aa95e38d4d6de92a6233a7");
		System.out.println( f.execute(req)    );
	}
}
