package com.seven7.neihan.fragments;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.seven7.neihan.R;
import com.seven7.neihan.activitys.EssayDetailActivity;
import com.seven7.neihan.adapter.EssayAdapter;
import com.seven7.neihan.bean.DataStore;
import com.seven7.neihan.bean.EntityList;
import com.seven7.neihan.bean.TextEntity;
import com.seven7.neihan.client.ClientAPI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class TextListFragment extends Fragment
	implements OnClickListener,OnScrollListener,OnRefreshListener2<ListView>, OnItemClickListener{
	
	private TextView textViewNotifiy;
	private View quickTools;
	private View headerView;
	
//	private List<TextEntity> textEntities;
	private EssayAdapter essayAdapter;
	
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
	/**
	 * 请求的分类类型ID
	 */
	private int requestCategory=CATEGORY_TEXT;
	
	public TextListFragment() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (queue==null) {
			queue=Volley.newRequestQueue(getActivity());		
		}
		ClientAPI.getList(queue, 30, requestCategory, lastTime, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				listOnResponse(response);
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		if (savedInstanceState!=null) {
			lastTime=savedInstanceState.getLong("lastTime");
		}
		
		View view=inflater.inflate(R.layout.fragment_textlist, container,false);
		
		//获取标题控件，增加点击，进行 新消息悬浮窗显示的功能
		View titleView=view.findViewById(R.id.textlist_fragment_title);
		titleView.setOnClickListener(this);
		
		//TODO 获取ListView并加载数据(以后需要用PullToRefresh进行完善)--以完善		
		PullToRefreshListView refreshListView=(PullToRefreshListView)view.findViewById
				(R.id.textlist_fragment_listview);
		
		//设置上拉与下拉的事件
		refreshListView.setOnRefreshListener(this);
		refreshListView.setMode(Mode.BOTH);
		//设置替代的ListView
		ListView listView=refreshListView.getRefreshableView();
		//设置ListView的点击事件监听器
		listView.setOnItemClickListener(this);
		
			
		//TODO 添加列表上面的快速工具条(Header)
		headerView=inflater.inflate(R.layout.listview_header_textlist, listView,false);
		listView.addHeaderView(headerView);
		headerView.setVisibility(View.VISIBLE);
		
		View quickPublisView=headerView.findViewById(R.id.quick_tools_publish);
		quickPublisView.setOnClickListener(this);
	
		View quickReviewView=headerView.findViewById(R.id.quick_tools_review);
		quickReviewView.setOnClickListener(this);
		
		//判断是否加载过内容
		List<TextEntity> textEntities=DataStore.getInstance().getTextEntities();
		
		if (textEntities==null) {
			textEntities=new LinkedList<TextEntity>();
		}
		//设置适配器
		essayAdapter=new EssayAdapter(getActivity(), textEntities);
		listView.setAdapter(essayAdapter);
		listView.setOnScrollListener(this);
		
		essayAdapter.setClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v instanceof TextView) {
					String string=(String) v.getTag();
					if (string!=null) {
						int position=Integer.parseInt(string);
						
						Intent intent=new Intent(getActivity(),EssayDetailActivity.class);
						intent.putExtra("currentEssayPosition", position);
						intent.putExtra("category", requestCategory);
						
						startActivity(intent);	
					}
				}
			}
		});
				
		//TODO 获取快速的工具条(发布和审核)，用于列表滚动的显示和隐藏
		
		quickTools=view.findViewById(R.id.textlist_fragment_quick_tools);
		quickTools.setVisibility(View.INVISIBLE);
		
		quickPublisView=headerView.findViewById(R.id.quick_tools_publish);
		quickPublisView.setOnClickListener(this);
	
		quickReviewView=headerView.findViewById(R.id.quick_tools_review);
		quickReviewView.setOnClickListener(this);

		
		//TODO 获取新条目通知控件，每次有新数据要显示，过一段时间隐藏
		
		textViewNotifiy=(TextView) view.findViewById(R.id.textlist_fragment_notifiy);
		textViewNotifiy.setVisibility(View.INVISIBLE);
		
		
		return view;
	}
		
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putLong("lastTiem", lastTime);		
		
		Log.i("TextListFragment", "Reload state:lastTime"+lastTime);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.essayAdapter=null;
		this.handler=null;
		this.quickTools=null;
		this.textViewNotifiy=null;
	}

//	///////////////////////////////////////////////////////////
	//列表滚动，显示工具条
	
	private int lastIndex=0;
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	
		int offset=lastIndex-firstVisibleItem;
		Log.i("TextListFragment", "-----onScroll--quickTools-----"+firstVisibleItem);
		if (offset<0||firstVisibleItem==0) {
			//证明现在是向上移动
			if (quickTools!=null) {
				quickTools.setVisibility(View.INVISIBLE);
				if (headerView.getVisibility()==View.INVISIBLE) {
					headerView.setVisibility(View.VISIBLE);
				}
			}
			
		}else if(offset>0){
			//证明现在是向下移动
			if (quickTools!=null) {
				quickTools.setVisibility(View.VISIBLE);
				//设置Header第一次为显示，其他为隐藏
				if (headerView.getVisibility()==View.VISIBLE) {
					headerView.setVisibility(View.INVISIBLE);
				}
			}
			
		}
		lastIndex=firstVisibleItem;
	}
	
	
	
// //////////////////////////////////////////////////////////
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			int what =msg.what;
			
			if (what==1) {
				//TODO what=1 代表有新消息提醒
				textViewNotifiy.setVisibility(View.INVISIBLE);
			}
		}
		
	};
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		
		switch (id) {
		case R.id.textlist_fragment_title:
			textViewNotifiy.setVisibility(View.VISIBLE);
			handler.sendEmptyMessageDelayed(1, 3000);
			break;

		default:
			break;
		}
	}

//  ///////////////////////////////////////////
//	列表下拉刷新，上拉加载
	
	/**
	 * 列表网络获取回调部分，这个方法是在Volley联网响应的时候调用，
	 * 它是在主线程执行的，可以直接更新UI
	 * 
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
//				Log.i("TestActivity", "Current Response min_time:"+lastTime);
			}else {//没有更多数据，需要等待			
				String tip=entityList.getTip();
//				Log.i("TestActivity", "Current Response tip:"+tip);
			}
			
			//获取段子列表内容
			
			//TODO 把entityList 这个段子的数据集合体，传递给Listview之类的Adapter即可显示
			List<TextEntity> ets=entityList.getEntities();
			
			if (ets!=null) {
				if (!ets.isEmpty()) {
					//把ets中的内容按照迭代器的顺序添加，需要验证一下
					
					DataStore.getInstance().addTextEntities(ets);
					
//					textEntities.addAll(0, ets);
					
					//手动添加
//					int len=ets.size();
//					for (int index = len-1; index >=0; index--) {
//						//把object添加到指定的location位置，原有location以及以后的内容向后移动
//						textEntities.add(0, ets.get(index));
//					}
					
					essayAdapter.notifyDataSetChanged();//提醒Adapter变更
					
				}else {
					//TODO 没有更多的数据了，需要提示一下
					
				}
			}else {
				//TODO 没有获取到网络数据，可能是数据解析错误或者是网络错误，需要提示一下
			}
			
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
	/**
	 * 从上向下拉动列表，那么就要进行加载新数据的操作
	 */
	@Override
	public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		ClientAPI.getList(
				queue, 
				30, 
				requestCategory, 
				lastTime, 
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO 加载新数据，
						refreshView.onRefreshComplete();
						listOnResponse(response);
					}
		});
	}
	
	/**
	 * 从下向上拉动列表，那么就要考虑是否进行加载旧的数据
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
	
//	////////////////////////////////////////
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
//		position--;
//		
//		Intent intent=new Intent(getActivity(),EssayDetailActivity.class);
//		intent.putExtra("currentEssayPosition", position);
//		intent.putExtra("category", requestCategory);
//		
//		startActivity(intent);
	}
}
