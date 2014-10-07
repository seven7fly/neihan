package com.seven7.neihan.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity extends TextEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4966978232234546136L;

	private ImageUrlList largeList;
	
	private ImageUrlList middleList;

	public void parseJson(JSONObject item) throws JSONException {
		super.parseJson(item);
		
		JSONObject group=item.getJSONObject("group");
		
		JSONObject largeIamgeObject=group.optJSONObject("large_image");
		JSONObject middleIamgeObject=group.optJSONObject("middle_image");
		
		largeList=new ImageUrlList();
		if (largeIamgeObject!=null) {
			largeList.parseJson(largeIamgeObject);
		}
		
		middleList=new ImageUrlList();		
		if (middleIamgeObject!=null) {
			middleList.parseJson(middleIamgeObject);
		}
		

	}

	


	
	
}
