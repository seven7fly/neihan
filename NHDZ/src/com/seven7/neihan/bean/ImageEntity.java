package com.seven7.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity {
	private int type;
	private int coumentCount;
	private long groupId;
	private String content;
	private ImageUrlList largeList;
	private ImageUrlList middleList;

	public void parseJson(JSONObject item) throws JSONException {
		type = item.getInt("type");
		
		JSONObject group=item.getJSONObject("group");
		
		coumentCount = group.getInt("comment_count");
		
		JSONObject largeImage=group.getJSONObject("large_image");
		
		JSONObject middleImage=group.getJSONObject("middle_image");
		
		groupId = group.getLong("group_id");
		
		content = group.getString("content");
		
		largeList = new ImageUrlList();				
		largeList.parseJson(largeImage);
		
		middleList = new ImageUrlList();
		middleList.parseJson(middleImage);
	}
}
