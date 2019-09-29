package com.ricardo.te2crud.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

	public static int executeUpdate(String sql) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(Statement stmt=conn.createStatement()){
			return stmt.executeUpdate(sql);
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}
	
	public static int executeUpdate(StatementBuilder builder) throws SQLException {
		return executeUpdate(builder,false);
	}

	public static int executeUpdate(StatementBuilder builder,boolean return_key) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(PreparedStatement stmt=builder.getStatement(conn)){
			if(return_key) {
				if(stmt.executeUpdate()>0) {
					try(var keys=stmt.getGeneratedKeys()){
						keys.next();
						return keys.getInt(1);
					}
				}else {
					return 0;
				}
			}else {
				return stmt.executeUpdate();
			}
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

	public static <T> ArrayList<T> executeQuery(String sql, RSGetter<T> getter) throws SQLException {
		Connection conn=ConnectionManager.retrieveConnection();
		try(Statement stmt=conn.createStatement()){
			try(ResultSet rs=stmt.executeQuery(sql)){
				return RSToArrayList(rs, getter);
			}
		}finally {
			ConnectionManager.returnConnection(conn);
		}
	}

	public static <T> ArrayList<T> executeQuery(StatementBuilder builder, RSGetter<T> getter) throws SQLException {
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
