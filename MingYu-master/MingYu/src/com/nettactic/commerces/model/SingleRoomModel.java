package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

public class SingleRoomModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name = "";
	private String imageUrl = "";
	private String intro = "";
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public String getIntro()
	{
		return intro;
	}
	
	public void setIntro(String intro)
	{
		this.intro = intro;
	}
	
	public SingleRoomModel() {
		super();
	}
	
	public SingleRoomModel(JSONObject json) {
		super();
		this.name = json.optString("RoomName", "");
		this.imageUrl = json.optString("RoomImgUrl", "");
		this.intro = json.optString("RoomDetailIntro", "");
	}
	
}
