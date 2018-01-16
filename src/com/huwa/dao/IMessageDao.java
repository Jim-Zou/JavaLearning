package com.huwa.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.Message;

public interface IMessageDao {
	/**
	 * 将数据插入数据库
	 * @param msg 消息对象
	 * @return 1成功 其他失败
	 */
	int insert(Message msg) throws SQLException;
	/**
	 * 根据接收者id 查询消息
	 * @param receiveid 接收者id
	 * @return 成功返回消息集合 失败返回null
	 * @throws SQLException
	 */
	List<Message> queryByReceiveId(int receiveid) throws SQLException;
	/**
	 * 根据id查询消息记录
	 * @param mid 消息id
	 * @return 成功返回消息对象 失败返回null
	 * @throws SQLException
	 */
	Message queryById(String mid) throws SQLException;
	/**
	 * 根据传入id,state修改消息状态
	 * @param id 消息id
	 * @param state 消息状态
	 * @return 1成功  其他失败
	 * @throws SQLException
	 */
	int updateState(int id, int state) throws SQLException;
	/**
	 * 根据uid查询总记录数
	 * @return 成功返回总记录数，<0失败
	 * @throws SQLException
	 */
	int queryCount(int uid) throws SQLException;
	/**
	 * 根据用户id 给定区间范围查询记录集合
	 * @param start 开始区间(开区间)
	 * @param end 结束区间(闭区间)
	 * @param uid 用户id
	 * @return 成功返回消息集合 失败返回null
	 * @throws SQLException
	 */
	List<Message> queryListByReceiveId(int start, int end, int uid) throws SQLException;
}
















