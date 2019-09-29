package com.ricardo.te2crud.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ricardo.te2crud.db.data.DBProduct;
import com.ricardo.te2crud.jdbc.JDBCConnector;

public class DB extends AbstractDB {
	@Override
	public List<DBProduct> searchProducts(String query) throws SQLException {
		String[] args=query.split("[ +]");
		if(args.length<1) {
			return new ArrayList<>();
		}else{
			String sql="select id,name,img_url,short_description,long_description from Products where name like ? "+(" and name like ? ".repeat(args.length-1));
			return JDBCConnector.executeQuery((conn)->{
				PreparedStatement stmt=conn.prepareStatement(sql);
				for(int i=0;i<args.length;i++) {
					stmt.setString(i+1, "%"+args[i]+"%");
				}
				return stmt;
			}, DBProduct.getter);
		}
	}

	@Override
	public DBProduct addProduct(DBProduct prod) throws SQLException {
		String sql="insert into Products (name,img_url,short_description,long_description) values (?,?,?,?)";
		int result=JDBCConnector.executeUpdate((conn)->{
			PreparedStatement stmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, prod.getName());
			stmt.setString(2, prod.getImgUrl());
			stmt.setString(3, prod.getShortDescription());
			stmt.setString(4, prod.getLongDescription());
			return stmt;
		},true);
		if(result>0) {
			prod.setId(result);
			return prod;
		} else {
			return null;
		}
	}

	@Override
	public DBProduct getProduct(int id) throws SQLException {
		String sql="select id,name,img_url,short_description,long_description from Products where id = ? ";
		return JDBCConnector.executeQuery((conn)->{
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, id);
			return stmt;
		}, DBProduct.getter).get(0);
	}

	@Override
	public DBProduct updateProduct(DBProduct prod) throws SQLException {
		String sql="update products set name=?,img_url=?,short_description=?,long_desctiption=? where id=?";
		if(JDBCConnector.executeUpdate((conn)->{
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, prod.getName());
			stmt.setString(2, prod.getImgUrl());
			stmt.setString(3, prod.getShortDescription());
			stmt.setString(4, prod.getLongDescription());
			stmt.setInt(5, prod.getId());
			return stmt;
		})>0) {
			return prod;
		}else {
			return null;
		}
	}

	@Override
	public boolean removeProduct(int id) throws SQLException {
		String sql="delete from products where id=?";
		if(JDBCConnector.executeUpdate((conn)->{
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, id);
			return stmt;
		})>0) {
			return true;
		}else {
			return false;
		}
	}

}
