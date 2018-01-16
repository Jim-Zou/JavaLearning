package com.hwua.service;

import java.util.List;

import com.hwua.entity.Message;
import com.hwua.entity.Pager;

public interface IMessageService {
	/**
	 * 发消息业务处理
	 * @param msg 消息对象
	 * @return 1成功 其他失败
	 */
	int sendMsg(Message msg);
	/**
	 * 获取当前登录用户消息列表业务处理
	 * @param uid 当前登录用户id
	 * @return 成功返回消息集合 失败返回null
	 */
	List<Message> showList(int uid);
	/**
	 * 根据id读取消息记录业务处理
	 * @param mid
	 * @return 成功返回消息对象  失败返回null
	 */
	Message readMsg(String mid);
	/**
	 * 根据id p查询区间集合
	 * @param uid 用户id
	 * @param p 分页对象
	 * @return 成功返回消息集合 失败返回null
	 */
	List<Message> showList(int uid, Pager p);

}
