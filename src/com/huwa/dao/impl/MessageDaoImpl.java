package com.huwa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.huwa.dao.IMessageDao;
import com.hwua.entity.Message;
import com.hwua.utils.JDBCUtils;

public class MessageDaoImpl implements IMessageDao{
	@Override
	public int insert(Message msg) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO MESSAGES("
				+ "ID,SENDID,TITLE,MSGCONTENT,STATE,RECEIVEID,MSG_CREATE_DATE) "
				+ "VALUES(SEQ_MESSAGE.NEXTVAL,"
				+ "?,?,?,1,?,SYSDATE)";
		return run.update(sql,
				msg.getSendid(),
				msg.getTitle(),
				msg.getMsgcontent(),
				msg.getReceiveid());
	}

	@Override
	public List<Message> queryByReceiveId(int receiveid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM MESSAGES WHERE RECEIVEID=?";
		List<Message> list = run.query(sql, 
				new BeanListHandler<>(Message.class),receiveid);
		return list;
	}

	@Override
	public Message queryById(String mid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM MESSAGES WHERE ID=?";
		Message msg = run.query(sql, 
				new BeanHandler<>(Message.class),mid);
		return msg;
	}

	@Override
	public int updateState(int id, int state) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE MESSAGES SET STATE = ? WHERE ID = ?";
		int result = run.update(sql,state,id);
		return result;
	}

	@Override
	public int queryCount(int uid) throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(ID) FROM MESSAGES WHERE RECEIVEID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, uid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public List<Message> queryListByReceiveId(int start, int end, int uid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * "
				+ "FROM(SELECT ROWNUM R,MESSAGES.*"
					+ " FROM MESSAGES "
					+ "WHERE RECEIVEID=?) T "
				+ "WHERE T.R>? AND T.R<=?";
		return run.query(sql, 
				new BeanListHandler<>(Message.class),
				uid,
				start,
				end);
	}

}










