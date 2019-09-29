package com.ricardo.te2crud.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBCConnector{//template method pattern

	@FunctionalInterface
	public interface RSGetter<T>{
		T getObject(ResultSet rs) throws SQLException;
	}

	@FunctionalInterface
	public interface StatementBuilder{
		PreparedStatement getStatement(Connection conn) throws SQLException;
	}

	private static <T> ArrayList<T> RSToArrayList(ResultSet rs,RSGetter<T> getter) throws SQLException {
		ArrayList<T> result=null;
		while(rs.next()) {
			if(result==null) result=new ArrayList<T>();
			result.add(getter.getObject(rs));
		}
		return result;
	}

	public final int executeUpdate(String sql) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(Statement stmt=conn.createStatement()){
			return stmt.executeUpdate(sql);
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

	public final int executeUpdate(StatementBuilder builder) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(PreparedStatement stmt=builder.getStatement(conn)){
			return stmt.executeUpdate();
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

	public final <T> ArrayList<T> executeQuery(String sql, RSGetter<T> getter) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(Statement stmt=conn.createStatement()){
			try(ResultSet rs=stmt.executeQuery(sql)){
				return RSToArrayList(rs, getter);
			}
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

	public final <T> ArrayList<T> executeQuery(StatementBuilder builder, RSGetter<T> getter) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(PreparedStatement stmt=builder.getStatement(conn)){
			try(ResultSet rs=stmt.executeQuery()){
				return RSToArrayList(rs, getter);
			}
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

}
