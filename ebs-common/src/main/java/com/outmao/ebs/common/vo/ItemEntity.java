package com.outmao.ebs.common.vo;

import lombok.Data;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@Data
@MappedSuperclass
public class ItemEntity extends SortEntity implements Itemable {

	private String type;
	private String title;
	private String subtitle;
	private String url;
	@Lob
	private String content;
	private String image;
	private String video;

	@Override
	public Item toItem() {
		return new Item(this.getId(),type,title,subtitle,url,content,image,video);
	}



}
