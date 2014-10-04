package com.seven7.neihan.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.framework.l;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.seven7.neihan.R;
import com.seven7.neihan.R.layout;
import com.seven7.neihan.R.menu;
import com.seven7.neihan.bean.ImageEntity;
import com.seven7.neihan.bean.ImageUrlList;
import com.seven7.neihan.client.ClientAPI;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;

/***
 * 这个文件是一个测试入口，所有的测试内容都可以放在这里
 * @author aaa
 *
 */
public class TestActivity extends Activity implements Response.Listener<String>{
	/***
	 * 分类ID 1代表文本
	 */
	public static final int CATEGORY_TEXT=1;
	/***
	 * 分类ID 2代表图片
	 */
	public static final int CATEGORY_IAMGE=2;
	
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		int itemCount = 30;//参数这里不能作为常量
		
		queue = Volley.newRequestQueue(this);
		
		ClientAPI.getList(queue,itemCount, CATEGORY_IAMGE,this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
//	    Log.i("TTT", response);	    
	    try {
			JSONObject jsonObject=new JSONObject(response);
			System.out.println("List:"+jsonObject.toString(4));
//			Log.i("json", "LIST:"+jsonObject.toString(4));
			
			//获取根节点下的data对象
			JSONObject object=jsonObject.getJSONObject("data");
			
			//从data对象中获取名称为data的数组，代表的是图片段子列表的数据
			JSONArray array=object.getJSONArray("data");
			
			int len=array.length();
			
			if(len>0){
				//遍历数组中的每一条图片段子信息
				for(int i=0;i<len;i++){
					JSONObject item=array.getJSONObject(i);
					ImageEntity imageEntity=new ImageEntity();
					imageEntity.parseJson(item);

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
