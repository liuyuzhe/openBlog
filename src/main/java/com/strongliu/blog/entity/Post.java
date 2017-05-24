package com.strongliu.blog.entity;

import com.strongliu.blog.constant.Constant;

import java.util.Date;

public class Post {
	private Integer id;
	private String slug;
	private String thumb_url;
	private String title;
	private String excerpt;
	private String content;
	private Date create_time;
	private Date update_time;
	private String type = Constant.POST_TYPE_DEFAULT;
	private String fmt_type;
	private String status = Constant.POST_STATUS_DEFAULT;
	private String comment_status = Constant.POST_COMMENT_STATUS_DEFAULT;
	private Long comment_count = Constant.POST_COMMENT_COUNT;
	private Long read_count = Constant.POST_READ_COUNT;
	private Long spot_count = Constant.POST_SPOT_COUNT;
	private Integer creator_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFmt_type() {
		return fmt_type;
	}

	public void setFmt_type(String fmt_type) {
		this.fmt_type = fmt_type;
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

	public Long getComment_count() {
		return comment_count;
	}

	public void setComment_count(Long comment_count) {
		this.comment_count = comment_count;
	}

	public Long getRead_count() {
		return read_count;
	}

	public void setRead_count(Long read_count) {
		this.read_count = read_count;
	}

	public Long getSpot_count() {
		return spot_count;
	}

	public void setSpot_count(Long spot_count) {
		this.spot_count = spot_count;
	}

	public Integer getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}
}
