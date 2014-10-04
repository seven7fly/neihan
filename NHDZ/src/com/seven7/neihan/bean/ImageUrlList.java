package com.seven7.neihan.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageUrlList {
	private List<String> lagetImageUrls;
	private String uri;
	private int width;
	private int height;

	public void parseJson(JSONObject json) throws JSONException {
		lagetImageUrls = parseIamgeUrlList(json);
		uri = json.getString("uri");
		width = json.getInt("width");
		height = json.getInt("height");
	}
	
	private List<String> parseIamgeUrlList(JSONObject largeImage) throws JSONException {
		JSONArray largeImgArray=largeImage.getJSONArray("url_list");
		
		List<String> lagetImageUrls=new ArrayList<String>();
		
		int ulen=largeImgArray.length();
		for(int j=0;j<ulen;j++){
			JSONObject largeImgObj=largeImgArray.getJSONObject(j);
			String url=largeImgObj.getString("url");
			lagetImageUrls.add(url);
			
		}
		return lagetImageUrls;
	}

	public List<String> getLagetImageUrls() {
		return lagetImageUrls;
	}

	public String getUri() {
		return uri;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
