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
public class F000005 extends BaseFunction   {

	@Override
	public ResponseParameter execute(RequestParameter requestParameter) throws Exception {
		
		String click_content = requestParameter.getParams().get("click_content");
		String sql ="select count(*) as click_count from t_click_count where click_content=?";
		int click_count = this.getNewJdbcTemplate().queryInt(sql,new String[]{click_content});
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow dataRow = new DataRow();
		if(click_count>0){
			dataRow.put("click_count",click_count );
		}else{
			dataRow.put("click_count","--" );
		}
		list.add(dataRow);
		response.setList(list);
		return response;
	}

	public static void main(String arg[] ) throws Exception{
		DBConfigure dbConfigure = new DBConfigure()  ;
		dbConfigure.loadConfig();
		Thread.sleep(2000);
		F000005 f = new F000005();
		RequestParameter req = new RequestParameter();
		req.addParameter("click_content", "医生使用");
		System.out.println( f.execute(req)    );
	}
}
