package com.seven7.neihan.fragments;

import java.util.ArrayList;
import java.util.List;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.seven7.neihan.R;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TextListFragment extends Fragment
	implements OnClickListener,OnScrollListener,OnRefreshListener2<ListView>{
	
	private TextView textViewNotifiy;
	private View quickTools;
	private View headerView;
	
	public TextListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
			
		List<String> list=new ArrayList<String>();
		list.add("java");
		list.add("java1");
		list.add("java2");
		list.add("java3");
		list.add("java4");
		list.add("java5");
		list.add("java6");
		list.add("java7");
		list.add("java8");
		list.add("java9");
		list.add("java0");
		list.add("java");
		list.add("java1");
		list.add("java2");
		list.add("java3");
		list.add("java4");
		list.add("java5");
		list.add("java6");
		list.add("java7");
		list.add("java8");
		list.add("java9");
		list.add("java0");
		list.add("java");
		list.add("java1");
		list.add("java2");
		list.add("java3");
		list.add("java4");
		list.add("java5");
		list.add("java6");
		list.add("java7");
		list.add("java8");
		list.add("java9");
		list.add("java0");

		//TODO 添加列表上面的快速工具条(Header)
		headerView=inflater.inflate(R.layout.listview_header_textlist, listView,false);
		listView.addHeaderView(headerView);
		
		View quickPublisView=headerView.findViewById(R.id.quick_tools_publish);
		quickPublisView.setOnClickListener(this);
	
		View quickReviewView=headerView.findViewById(R.id.quick_tools_review);
		quickReviewView.setOnClickListener(this);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_1,
				list
			);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
				
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
			}
			
		}else if(offset>0){
			//证明现在是向下移动
			if (quickTools!=null) {
				quickTools.setVisibility(View.VISIBLE);
				//设置Header第一次为显示，其他为隐藏
//				if (headerView.getVisibility()==View.VISIBLE) {
//					headerView.setVisibility(View.INVISIBLE);
//				}
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
	 * 从上向下拉动列表，那么就要进行加载新数据的操作
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 从下向上拉动列表，那么就要考虑是否进行加载旧的数据
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

//	////////////////////////////////////////
	
}
