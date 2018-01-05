package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

public class NewsModel implements Serializable
{

	/**
	 * 最新资讯model
	 */
	private static final long serialVersionUID = 1L;

	private String ID = "";
	private String title = "";
	private String contentsUrl = "";
	private String newsImg = "";

	public NewsModel(JSONObject json) {
		super();
		this.ID = json.optString("ID", "");
		this.title = json.optString("Title", "");
		this.contentsUrl = json.optString("ContentsUrl", "");
		this.newsImg = json.optString("NewsImg", "");
	}

	public String getID()
	{
		return ID;
	}

	public void setID(String iD)
	{
		ID = iD;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContentsUrl()
	{
		return contentsUrl;
	}

	public void setContentsUrl(String contentsUrl)
	{
		this.contentsUrl = contentsUrl;
	}

	public String getNewsImg()
	{
		return newsImg;
	}

	public void setNewsImg(String newsImg)
	{
		this.newsImg = newsImg;
	};

}
