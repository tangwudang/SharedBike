package com.lishu.bike.entity;

import java.io.Serializable;

/**
 * 现场照片实体类
 * @author tang
 */
public class LivePictureEntity implements Serializable{
	private static final long serialVersionUID = -7060210544600464481L;
	private String id;	
	private String icon;
	private int type;// 0默认显示，1添加， 2删除
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
