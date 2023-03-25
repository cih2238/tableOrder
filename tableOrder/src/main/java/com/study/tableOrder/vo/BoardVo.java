package com.study.tableOrder.vo;

import lombok.Data;

public class BoardVo {
	private int no;
	private String image;
	private String content;
	private String title;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", image=" + image + ", content=" + content + ", title=" + title + "]";
	}
	
}
