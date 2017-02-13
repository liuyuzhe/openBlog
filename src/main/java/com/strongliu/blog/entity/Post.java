package com.strongliu.blog.entity;

public class Post {
	private String id;
	private String post_title;
	private String post_excerpt;
	private String post_content;
	private String post_create_time;
	private String post_modified_time;
	private String post_status;
	private String post_comment_status;
	private String post_type;
	private String post_mime_type;
	private Long post_comment_count;
	private String post_author_Id;
	private String post_category_Id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_excerpt() {
		return post_excerpt;
	}

	public void setPost_excerpt(String post_excerpt) {
		this.post_excerpt = post_excerpt;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getPost_create_time() {
		return post_create_time;
	}

	public void setPost_create_time(String post_create_time) {
		this.post_create_time = post_create_time;
	}

	public String getPost_modified_time() {
		return post_modified_time;
	}

	public void setPost_modified_time(String post_modified_time) {
		this.post_modified_time = post_modified_time;
	}

	public String getPost_status() {
		return post_status;
	}

	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}

	public String getPost_comment_status() {
		return post_comment_status;
	}

	public void setPost_comment_status(String post_comment_status) {
		this.post_comment_status = post_comment_status;
	}

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	public String getPost_mime_type() {
		return post_mime_type;
	}

	public void setPost_mime_type(String post_mime_type) {
		this.post_mime_type = post_mime_type;
	}

	public Long getPost_comment_count() {
		return post_comment_count;
	}

	public void setPost_comment_count(Long post_comment_count) {
		this.post_comment_count = post_comment_count;
	}

	public String getPost_author_Id() {
		return post_author_Id;
	}

	public void setPost_author_Id(String post_author_Id) {
		this.post_author_Id = post_author_Id;
	}

	public String getPost_category_Id() {
		return post_category_Id;
	}

	public void setPost_category_Id(String post_category_Id) {
		this.post_category_Id = post_category_Id;
	}
}
