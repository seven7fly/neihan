package com.seven7.neihan.test;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.seven7.neihan.R;
import com.seven7.neihan.bean.Comment;
import com.seven7.neihan.bean.CommentList;
import com.seven7.neihan.bean.EntityList;
import com.seven7.neihan.client.ClientAPI;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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
	private long lastTime=0;
	private int offset=0;
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		final int itemCount = 30;//参数这里不能作为常量
		
		queue = Volley.newRequestQueue(this);
		
//		ClientAPI.getList(queue,itemCount, CATEGORY_TEXT,0,this);
		
//		textView=(TextView)this.findViewById(R.id.test_anniu);
//		textView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ClientAPI.getList(queue, itemCount, CATEGORY_TEXT, lastTime, TestActivity.this);
//				System.out.println(lastTime);
//			}
//		});
		
		//3550036269是对应文本段子的ID
		final long groupId=3550036269l;
		
		textView=(TextView)this.findViewById(R.id.test_anniu);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClientAPI.getComments(queue,groupId, offset,TestActivity.this);
				System.out.println(lastTime);
			}
		});
		
		ClientAPI.getComments(queue,groupId, offset,this);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	
	/**
	 * 列表网络获取回调部分
	 * @param response 列表Json数据字符串
	 */
	public void listOnResponse(String response) {
		// TODO Auto-generated method stub    
	    try {
			JSONObject jsonObject=new JSONObject(response);
	
			//获取根节点下的data对象
			JSONObject object=jsonObject.getJSONObject("data");
			//解析段子列表完整数据
			EntityList entityList=new EntityList();
			//这个方法是解决Json的方法，其中包含的支持图片、文本、广告的解析
			entityList.parseJson(object);
			//如果isHasMore返回true，代表还可以更新一次数据
			if (entityList.isHasMore()) {
				lastTime=entityList.getMinTime();//得到min_time，获取到更新时间标识
				Log.i("TestActivity", "Current Response min_time:"+lastTime);
			}else {//没有更多数据，需要等待
				
				String tip=entityList.getTip();
				Log.i("TestActivity", "Current Response tip:"+tip);
			}
			
			//获取段子列表内容
			
			//TODO 把entityList 这个段子的数据集合体，传递给Listview之类的Adapter即可显示
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
				try {
					
					JSONObject json=new JSONObject(response);
					response=json.toString(4);
					
					/**
					 * 测试代码
					 */
//									Iterator<String> keys=json.keys();
//									while (keys.hasNext()) {
//										String key = (String) keys.next();
//										Log.i("TestActivity", "JSON key"+key);
//									}
					
									Log.i("TestActivity", "Comment List Json"+response);
					//解析获取到的评论列表									
					CommentList commentList=new CommentList();
					/*
					 * 评论列表包含两组数据，一个是热门评论，一个是新鲜评论
					 * 热门评论和新鲜评论都有可能是空的
					 */
					commentList.parseJson(json);
					//是否还有更多评论
					boolean hasMore=commentList.isHasMore();
					//评论总数
					int totalNumber=commentList.getTotalNumber();
					
					//热门评论列表,可能为空，第一次offset为0时可能有数据
					List<Comment> topComments=commentList.getTopComments();
					//新鲜评论列表,可能有数据
					List<Comment> recentComments=commentList.getRecentComments();
					
					//测试代码
					Log.i("TestActivity", "ttttttttt"+commentList.getGroupId());
					Log.i("TestActivity", "ttttttttt"+commentList.isHasMore());
					
					
					//TODO 直接把CommentList 提交给ListView 的Adapter 这样可以进行是否还有内容的判断
					//利用Adapter更新数据
					
					//分页标识，要求服务器每次必须返回20条评论，通过hasMore来进行判断是否还需要分页
					offset+=20;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

}
