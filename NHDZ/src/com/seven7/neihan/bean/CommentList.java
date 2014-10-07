package com.seven7.neihan.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.dropbox.c;

/***
 * 评论接口返回的data:{}数据部分的实体定义
 * 包含了top_comments和recent_comments两个数组
 * Json格式如下<br/>
 * <pre>
 * {
 * 	 "data": {
 *	        "recent_comments": [], 
 *	        "top_comments": []
 *	    }
 *	}
 *	</pre>
 * @author aaa
 *
 */
public class CommentList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5521252491844413406L;

	private List<Comment> topComments;
	
	private List<Comment> recentComments;
	
	private long groupId;
	
	private int totalNumber;
	
	private boolean hasMore;
	
	
	public void parseJson(JSONObject json) throws JSONException{
		
		if(json!=null){
			groupId=json.getLong("group_id");
			totalNumber=json.getInt("total_number");
			hasMore=json.getBoolean("has_more");
			
			JSONObject data=json.getJSONObject("data");
			
			JSONArray topArray=data.optJSONArray("top_comments");
			if (topArray!=null) {
				topComments=new LinkedList<Comment>();
				
				int len=topArray.length();
				if (len>0) {
					for (int index = 0; index < len; index++) {
						JSONObject topJsonObject=topArray.getJSONObject(index);
						Comment comment=new Comment();
						comment.parseJson(topJsonObject);
						topComments.add(comment);
					}
				}
			}
			
			JSONArray recentArray=data.optJSONArray("recent_comments");
			if (recentArray!=null) {
				recentComments=new LinkedList<Comment>();
				
				int len=topArray.length();
				if (len>0) {
					for (int index = 0; index < len; index++) {
						JSONObject recentJsonObject=recentArray.getJSONObject(index);
						Comment comment=new Comment();
						comment.parseJson(recentJsonObject);
						recentComments.add(comment);
					}
				}
			}
		}
	}


	public List<Comment> getTopComments() {
		return topComments;
	}


	public List<Comment> getRecentComments() {
		return recentComments;
	}


	public long getGroupId() {
		return groupId;
	}


	public int getTotalNumber() {
		return totalNumber;
	}


	public boolean isHasMore() {
		return hasMore;
	}
	

}
