package com.hwua.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.huwa.dao.IMessageDao;
import com.huwa.dao.IUserDao;
import com.huwa.dao.impl.MessageDaoImpl;
import com.huwa.dao.impl.UserDaoImpl;
import com.hwua.entity.Message;
import com.hwua.entity.Pager;
import com.hwua.entity.User;
import com.hwua.service.IMessageService;

public class MessageServiceImpl implements IMessageService{
	private IMessageDao imd = new MessageDaoImpl();
	private IUserDao iud = new UserDaoImpl();
	@Override
	public int sendMsg(Message msg) {
		if(msg == null){
			throw new RuntimeException("传入参数为空");
		}
		int result = -1;
		try {
			result = imd.insert(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<Message> showList(int uid) {
		List<Message> list = null;
		try {
			list = imd.queryByReceiveId(uid);
			if(list!=null){
				//循环填充发送者对象
				for (int i = 0; i < list.size(); i++) {
					User user = iud.queryById(list.get(i).getSendid());
					list.get(i).setSendUser(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Message readMsg(String mid) {
		if(mid==null||"".equals(mid)){
			throw new RuntimeException("传入参数有误!!!");
		}
		//1.根据id查询消息记录
		Message msg = null;
		try {
			msg = imd.queryById(mid);
			//2.如果状态是1(未读)则改为0(已读)
			if(msg!=null&&msg.getState()==1){
				//已读的0改为未读的1
				imd.updateState(msg.getId(),0);
			}
			if(msg!=null){
				//填充发送者对象
				User user = iud.queryById(msg.getSendid());
				msg.setSendUser(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public List<Message> showList(int uid, Pager p) {
		List<Message> list = null;
		try {
			//3.获取总记录数
			int count = imd.queryCount(uid);
			p.setRecordCount(count);//设置总记录数
			//4.获取区间范围start&end
			int start = (p.getCurrentPage()-1)*Pager.PAGE_RECORD;
			int end = start+Pager.PAGE_RECORD;
			//5.根据区间start&end,用户id查询区间集合
			list = imd.queryListByReceiveId(start,end,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}








