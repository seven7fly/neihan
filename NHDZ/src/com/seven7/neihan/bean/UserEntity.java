package com.seven7.neihan.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6631201150796978759L;
	/**
	 * 头像网址
	 */
	private String avatarUrl;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 
	 */
	private boolean userVerified;
	
	public void parseJson(JSONObject json) throws JSONException{
		if(json!=null){
			this.avatarUrl=json.getString("avatar_url");
			this.name=json.getString("name");
			Log.i("UserEntity", name);
			this.userId=json.getLong("user_id");
			this.userVerified=json.getBoolean("user_verified");
		}
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public boolean isUserVerified() {
		return userVerified;
	}
	
	
	/*
	 *  "user": {
                "avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454",
                "user_id": 3080520868,
                "name": "请叫我梓安哥",
                "user_verified": false
            },
	 */

}
