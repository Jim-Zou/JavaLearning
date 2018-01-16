package com.hwua.service;

import java.util.List;

import com.hwua.entity.User;

public interface IUserService {
	/**
	 * 注册业务处理
	 * @param user 带有数据的用户对象
	 * @return 1成功 -1用户名重复 其他失败
	 */
	int register(User user);
	/**
	 * 登录业务处理
	 * @param user 带有用户名密码的用户对象
	 * @return 成功返回用户对象 失败返回null
	 */
	User login(User user);
	/**
	 * 获取其他所有用户
	 * @param id 当前登录用户id
	 * @return 成功返回list集合 失败返回null
	 */
	List<User> showOtherUsers(int id);
	/**
	 * 验证用户名是否存在业务处理
	 * @param name 用户名
	 * @return 存在返回user对象  不存在返回null
	 */
	User checkName(String name);

}






