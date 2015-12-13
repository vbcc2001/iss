package com.xxx.iss.function;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.xxx.iss.jdbc.DBConfigure;
import com.xxx.iss.jdbc.JdbcTemplate;
import com.xxx.iss.jdbc.Session;

/**
 * Service类的基类
 * @author 门士松  20121030
 * @version 1.0
 * @since
 */
public abstract class BaseFunction implements Function {
	
	private Logger logger = Logger.getLogger(this.getClass());
	ResponseParameter response = new ResponseParameter();
    /**
     * 获得新指定数据源连接对象
     */
    public Connection getNewConnection(String name){
    	try {
			return DBConfigure.getDataSource(name).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    /**
     * 获得新指定数据源连接对象
     */
    public Connection getNewConnection(){
    	try {
			return DBConfigure.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }    
    /**
     * 获得指定的会话对象
     */
    public Session getNewSession(String name){
        return new Session(getNewConnection(name));
    }
    /**
     * 获得指定的会话对象
     */
    public Session getNewSession(){
        return new Session(getNewConnection());
    }	    
    /**
     * 获得指定的数据库操作模版
     */
    public JdbcTemplate getNewJdbcTemplate(String name){
        return new JdbcTemplate(name);
    }   
    /**
     * 获得指定的数据库操作模版
     */
    public JdbcTemplate getNewJdbcTemplate(){
        return new JdbcTemplate();
    }

}