package com.hwua.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.huwa.dao.IUserDao;
import com.huwa.dao.impl.UserDaoImpl;
import com.hwua.entity.User;
import com.hwua.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao iud = new UserDaoImpl();
	@Override
	public int register(User user) {
		if(user==null){//非空验证
			throw new RuntimeException("参数为空");
		}
		int row = -1;
		try {
			//1判断用户名是否存在
			User result = iud.queryByName(user.getUsername());
			if(result == null){
				//2将用户数据插入数据库
				row = iud.insert(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	@Override
	public User login(User user) {
		if(user==null){//非空验证
			throw new RuntimeException("参数为空");
		}
		//调用dao完成数据的check
		User result = null;
		try {
			result = iud.queryByNamePwd(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<User> showOtherUsers(int id) {
		List<User> list = null;
		try {
			list = iud.queryOthersById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public User checkName(String name) {
		User result = null;
		try {
			result = iud.queryByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}










