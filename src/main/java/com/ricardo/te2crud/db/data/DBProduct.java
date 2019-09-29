package com.ricardo.te2crud.db.data;

import com.ricardo.te2crud.jdbc.JDBCConnector.RSGetter;

public class DBProduct {
	public static final RSGetter<DBProduct> getter=(r)->new DBProduct(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5));
	private int id;
	private String name;
	private String imgUrl;
	private String shortDescription;
	private String longDescription;
	public DBProduct() {
	}
	public DBProduct(int id, String name, String imgUrl, String shortDescription, String longDescription) {
		this.id = id;
		this.name = name;
		this.imgUrl = imgUrl;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImg_url(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
}
