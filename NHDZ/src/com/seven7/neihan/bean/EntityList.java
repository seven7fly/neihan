package com.seven7.neihan.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/***
 * EntityList代表解析的对象是第一个data
 *
    "data": {
        "has_more": true,
        "min_time": 1411887357,
        "tip": "50条新内容",
        "data": [
            {}
            {}	]
        "max_time": 1411872657
    }
 * @author aaa
 *
 */
public class EntityList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605615299799607332L;
	private boolean hasMore;
	private long minTime;
	private String tip;
	private long maxTime;
	
	private List<TextEntity> entities;

	public void parseJson(JSONObject json) throws JSONException{
		if (json!=null) {
			
			hasMore = json.getBoolean("has_more");//是否可以加载更多			
			tip = json.getString("tip");
			if (hasMore==true) {
				
				minTime = json.getLong("min_time");
			}
			maxTime = json.getLong("max_time");
			
			//从data对象中获取名称为data的数组，代表的是图片段子列表的数据
			JSONArray array=json.getJSONArray("data");
			
			int len=array.length();
			
			if(len>0){
				entities=new LinkedList<TextEntity>();
				
				//遍历数组中的每一条图片段子信息
				for(int i=0;i<len;i++){
					JSONObject item=array.getJSONObject(i);
					
					int type=item.getInt("type");//获取类型，1 段子，5 广告
					if(type==5){
						//TODO 处理广告内容
						AdEntity adEntity=new AdEntity();
						adEntity.parseJson(item);
						
						String downLoadUrl=adEntity.getDownloadUrl();
						Log.i("TestActivity", "----------Recivied Url"+downLoadUrl);
						
					}else if (type==1) {
						//TODO 处理段子内容
						JSONObject group=item.getJSONObject("group");
						int cid=group.getInt("category_id");
						TextEntity entity=null;
						if(cid==1){
							//TODO 解析文本段子
							entity=new TextEntity();
							
						}else if (cid==2) {
							//TODO 解析图片段子
							entity=new ImageEntity();
						}
						entity.parseJson(item);
						entities.add(entity);
						
						long groupId=entity.getGroupId();
						Log.i("TestActivity", "-------------Recivied group id"+groupId);
					}
				}
			}
		}
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public long getMinTime() {
		return minTime;
	}

	public String getTip() {
		return tip;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public List<TextEntity> getEntities() {
		return entities;
	}
	
	

}
