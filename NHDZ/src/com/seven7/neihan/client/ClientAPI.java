package com.seven7.neihan.client;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.seven7.neihan.bean.CommentList;
import com.seven7.neihan.test.TestActivity;

/**
 * 所有和服务器接口对接的方法，全部在这个类中
 * @author aaa
 *
 */
public class ClientAPI {

	/***
	 * 获取内涵段子列表内容
	 * @param itemCount  服务器一次传回来的条目数量
	 * @param categoryType  要获取的参数类型
	 * @param responseListener 用于获取段子列表的回调接口，任何调用getList方法时，此参数用于更新段子列表
	 * @param minTime 用于分页加载数据或者下拉刷新时用，代表上一次服务器返回的时间信息
	 * @see  TestActivity#CATEGORY_TEXT
	 * @see  TestActivity#CATEGORY_IAMGE
	 */
	public static void getList(
			RequestQueue queue,
			int itemCount,
			int categoryType,
			long minTime,
			Response.Listener<String> responseListener) {
		//TODO 测试内涵段子接口列表，获取文本列表
				
		String CATEGORY_LIST_API="http://ic.snssdk.com/2/essay/zone/category/data/";
	
		//分类参数，根据类型获取不同的数据
		String categoryParam="category_id="+categoryType;
		
		String countParam="count="+itemCount;
		
		String deviceTypeParam="device_type=KFTT";
		
		String openUDID="openudid=b90ca6a3a19a78d6";
				
		String url=CATEGORY_LIST_API
					+"?"
					+categoryParam
					+"&"
					+countParam
					+"&"
					+deviceTypeParam
					+"&"
					+openUDID
					+"&level=6&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&os_api=15&os_version=4.0.3";
		
		if (minTime>0) {
			url=url+"&minTime="+minTime;
		}
		
		queue.add(new StringRequest(Request.Method.GET, url, 
				responseListener, new Response.ErrorListener() {
	
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
								
					}
				}));
	
	}

	public static void getComments(
				RequestQueue queue,
				long groupId, 
				int offset,
				Response.Listener<String> responseListener) {
			String COMMENT_API="http://isub.snssdk.com/2/data/get_essay_comments/";
			
			String groupIdParam="group_id="+groupId;
			
			String offsetParam="offset="+offset;
			
			String url=
					COMMENT_API+
					"?"+
					groupIdParam+
					"&"+
					offsetParam+
					"&"+
					"count=20&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6";
			
			queue.add(
					
					new StringRequest(Request.Method.GET, url,
							responseListener, new Response.ErrorListener() {
	
								@Override
								public void onErrorResponse(VolleyError error) {
									// TODO Auto-generated method stub
									
								}
							})
					
					);
		}

}
