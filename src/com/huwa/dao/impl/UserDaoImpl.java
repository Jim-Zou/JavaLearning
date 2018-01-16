package com.huwa.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.huwa.dao.IUserDao;
import com.hwua.entity.User;
import com.hwua.utils.JDBCUtils;

public class UserDaoImpl implements IUserDao {

	@Override
	public User queryByName(String username) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT ID,USERNAME,PASSWORD,EMAIL FROM USERS "
				+ "WHERE USERNAME = ?";
		User user = run.query(sql, new BeanHandler<>(User.class),username);
		return user;
	}

	@Override
	public int insert(User user) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "INSERT INTO USERS(ID,USERNAME,PASSWORD,EMAIL) "
				+ "VALUES(SEQ_USERS.NEXTVAL,?,?,?)";
		int row = run.update(sql
				,user.getUsername()
				,user.getPassword()
				,user.getEmail());
		return row;
	}

	@Override
	public User queryByNamePwd(User user) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT ID,USERNAME,PASSWORD,EMAIL FROM USERS "
				+ "WHERE USERNAME = ? AND PASSWORD = ?";
		User result = run.query(sql
				, new BeanHandler<>(User.class)
				,user.getUsername()
				,user.getPassword());
		return result;
	}

	@Override
	public List<User> queryOthersById(int id) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT ID,USERNAME FROM USERS "
				+ "WHERE ID != ?";
		List<User> list = run.query(sql, new BeanListHandler<>(User.class),id);
		return list;
	}

	@Override
	public User queryById(int id) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT ID,USERNAME,EMAIL FROM USERS "
				+ "WHERE ID = ?";
		User user = run.query(sql, new BeanHandler<>(User.class),id);
		return user;
	}

}
