package com.ricardo.te2crud.db;

import java.sql.SQLException;
import java.util.List;

import com.ricardo.te2crud.db.data.DBProduct;

public abstract class AbstractDB {
	public abstract List<DBProduct> searchProducts(String query) throws SQLException;
	public abstract DBProduct addProduct(DBProduct prod) throws SQLException;//id is ignored
	public abstract DBProduct getProduct(int id) throws SQLException;
	public abstract boolean updateProduct(DBProduct prod) throws SQLException;//id is used to find item in DB
	public abstract boolean removeProduct(int id) throws SQLException;
}
