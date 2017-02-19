package com.strongliu.blog.entity;

import java.util.Date;

public class Post {
	private String id;
	private String title;
	private String excerpt;
	private String content;
	private Date create_time;
	private Date update_time;
	private String status;
	private String comment_status;
	private String type;
	private String mime_type;
	private Long comment_count;
	private int creater_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment_status() {
		return comment_status;
	}

	public void setComment_status(String comment_status) {
		this.comment_status = comment_status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public Long getComment_count() {
		return comment_count;
	}

	public int getCreater_id() {
		return creater_id;
	}

	public void setCreater_id(int creater_id) {
		this.creater_id = creater_id;
	}

	public void setComment_count(Long comment_count) {
		this.comment_count = comment_count;
	}
}
