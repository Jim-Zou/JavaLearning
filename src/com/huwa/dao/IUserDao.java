package com.huwa.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.User;

public interface IUserDao {
	/**
	 * 根据用户名查询用户
	 * @param username 用户名
	 * @return 成功返回用户对象  失败返回null
	 * @throws SQLException
	 */
	User queryByName(String username) throws SQLException;
	/**
	 * 将用户数据保存至数据库
	 * @param user 带有数据的用户对象
	 * @return 1成功其他失败
	 * @throws SQLException
	 */
	int insert(User user) throws SQLException;
	/**
	 * 根据用户名密码查询数据
	 * @param user 带有用户名密码的用户对象
	 * @return 成功返回用户对象  失败返回null
	 * @throws SQLException
	 */
	User queryByNamePwd(User user) throws SQLException;
	/**
	 * 根据用户id查询其他用户记录
	 * @param id 用户id
	 * @return 成功返回list集合 失败返回null
	 */
	List<User> queryOthersById(int id) throws SQLException;
	
	/**
	 * 根据用户id查询记录
	 * @param id 用户id
	 * @return 成功返回用户对象 失败返回null
	 * @throws SQLException
	 */
	User queryById(int id) throws SQLException;
}












