package com.seven7.neihan.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Comment implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4766514780061558110L;
	private String userProfileImageUrl;
	private String userName;
	private String platform;
	private boolean userVerified;
	private int diggCount;
	private String description;
	private int buryCount;
	private long userId;
	private String userProfileUrl;
	private int userBury;
	private long id;
	private long createTime;
	private String text;
	private long uid;
	private int userDigg;

	public void parseJson(JSONObject json) throws JSONException{
		if(json!=null){
			uid = json.getLong("uid");
			text = json.getString("text");
			createTime = json.getLong("create_time");
			id = json.getLong("id");
			userBury = json.getInt("user_bury");
			userProfileUrl = json.getString("user_profile_url");
			userId = json.getLong("user_id");
			buryCount = json.getInt("bury_count");
			description = json.optString("description","null");
			diggCount = json.getInt("digg_count");
			userDigg = json.getInt("user_digg");
			userVerified = json.getBoolean("user_verified");
			platform = json.getString("platform");
			userName = json.getString("user_name");
			userProfileImageUrl = json.getString("user_profile_image_url");//用户头像网址
		}
		Log.i("CommentEntity", toString());
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getPlatform() {
		return platform;
	}

	public boolean isUserVerified() {
		return userVerified;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public String getDescription() {
		return description;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserProfileUrl() {
		return userProfileUrl;
	}

	public int getUserBury() {
		return userBury;
	}

	public long getId() {
		return id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getText() {
		return text;
	}

	public long getUid() {
		return uid;
	}

	public int getuserDigg() {
		// TODO Auto-generated method stub
		return userDigg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	/*
	 *  {
         "uid": 0, 
         "text": "你也想吃老孙一棒？ // @黄山小妖: 那是没路过黄山...", 
         "create_time": 1412087769, 
         "user_digg": 0, 
         "id": 3569939230, 
         "user_bury": 0, 
         "user_profile_url": "", 
         "user_id": 3328594365, 
         "bury_count": 0, 
         "description": "这个人到底有多懒……", 
         "digg_count": 1, 
         "user_verified": false, 
         "platform": "feifei", 
         "user_name": "别人骑马我骑驴丶后面还有要饭滴", 
         "user_profile_image_url": "http://p1.pstatp.com/thumb/1030/5256143796"
     }, 
	 */
	
	@Override
	public String toString() {
		return "Comment [userProfileImageUrl=" + userProfileImageUrl
				+ ", userName=" + userName + ", platform=" + platform
				+ ", userVerified=" + userVerified + ", diggCount=" + diggCount
				+ ", description=" + description + ", buryCount=" + buryCount
				+ ", userId=" + userId + ", userProfileUrl=" + userProfileUrl
				+ ", userBury=" + userBury + ", id=" + id + ", createTime="
				+ createTime + ", text=" + text + ", uid=" + uid
				+ ", userDigg=" + userDigg + "]";
	}
}
