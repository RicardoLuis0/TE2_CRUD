package com.ricardo.te2crud.db.data;

public class DBProduct {
	private int id;
	private String name;
	private String img_url;
	private String description;
	public DBProduct() {
	}
	public DBProduct(int id, String name, String img_url, String description) {
		this.id = id;
		this.name = name;
		this.img_url = img_url;
		this.description = description;
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
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}