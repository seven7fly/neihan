package com.seven7.neihan.fragments;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.seven7.neihan.R;
import com.seven7.neihan.adapter.CommentAdapter;
import com.seven7.neihan.adapter.EssayAdapter.ViewHolder;
import com.seven7.neihan.bean.Comment;
import com.seven7.neihan.bean.CommentList;
import com.seven7.neihan.bean.TextEntity;
import com.seven7.neihan.bean.UserEntity;
import com.seven7.neihan.client.ClientAPI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailContentFragment extends Fragment implements OnTouchListener, Listener<String> {
	
	private TextEntity entity;
	private ScrollView scrollView;
	private LinearLayout scollLinear;
	
	private TextView txtHotCommentCount;
	private TextView txtRecentCommentCount;
	
	private RequestQueue queue;
	private int offset=0;
	
	public DetailContentFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (entity==null) {
			Bundle arguments=getArguments();
			Serializable serializable=arguments.getSerializable("entity");
			
			if (serializable instanceof TextEntity) {
				entity=(TextEntity) serializable;
			}
		}
		
		queue=Volley.newRequestQueue(getActivity());
	}
	
	
	private CommentAdapter hotAdapter;
	private CommentAdapter recentAdapter;
	
	private List<Comment> hotComments;	
	private List<Comment> recentComments;
	private boolean hasMore;
	private long groupId;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_detailcontent, container,false);
		
		scrollView=(ScrollView)view.findViewById(R.id.detail_fragment_scrollview);
		scrollView.setOnTouchListener(this);
		scollLinear=(LinearLayout)view.findViewById(R.id.detail_fragment_scrollLinear);
		
		//设置主体内容		
		setEssayContent(view);
		txtHotCommentCount = (TextView)view.findViewById(R.id.detail_fragment_hot_comments_txt);
		hotCommentListView = (ListView)view.findViewById(R.id.detail_fragment_hot_comments_list);
		
		hotComments=new LinkedList<Comment>();
		hotAdapter=new CommentAdapter(getActivity(),hotComments);
		hotCommentListView.setAdapter(hotAdapter);
		
		txtRecentCommentCount = (TextView)view.findViewById(R.id.detail_fragment_recent_comments_txt);
		recentCommentListView = (ListView)view.findViewById(R.id.detail_fragment_recent_comments_list);

		recentComments=new LinkedList<Comment>();
		recentAdapter=new CommentAdapter(getActivity(),recentComments);	
		recentCommentListView.setAdapter(recentAdapter);
		
		groupId = entity.getGroupId();
		ClientAPI.getComments(queue, groupId, offset, this);
		
//		if (entity!=null) {
//			UserEntity userEntity=entity.getUser();
//			if (userEntity!=null) {
//				userEntity.getName();
//				userEntity.getAvatarUrl();
//				
//				view.findViewById(R.id.comment_profile_nick);
//				view.findViewById(R.id.comment_profile_img);
//			}
//		}
		return view;
	}
	
	/**
	 * 设置段子详情主体内容
	 * @param view
	 */
	public void setEssayContent(View view) {
		TextView btnGifPlay=(TextView)view.findViewById(R.id.item_gifplay);
		GifImageView gifImageView=(GifImageView)view.findViewById(R.id.item_gifview);
		TextView btnComment=(TextView)view.findViewById(R.id.item_comment);
		CheckBox cBoxBuryCount=(CheckBox)view.findViewById(R.id.item_count_bury);
		CheckBox cBoxDiggCount=(CheckBox)view.findViewById(R.id.item_count_digg);
		ImageButton imgBtnShare=(ImageButton)view.findViewById(R.id.item_share);
		ImageView imgProfileImage=(ImageView)view.findViewById(R.id.item_profile_iamge);
		ProgressBar pdDownloadProgress=(ProgressBar)view.findViewById(R.id.item_image_download_progress);
		TextView txtContent=(TextView)view.findViewById(R.id.item_content);
		TextView txtProfileNick=(TextView)view.findViewById(R.id.item_profile_nick);
		
		//1、先设置文本内容的数据		
		String nick="未知用户";
		UserEntity userEntity=entity.getUser();
		if (userEntity!=null) {
			nick=userEntity.getName();
		}
		  txtProfileNick.setText(nick);
		
		String content=entity.getContent();
		  txtContent.setText(content);
		
		int diggCount=entity.getDiggCount();
		  cBoxDiggCount.setText(Integer.toString(diggCount));			
		int userDigg=entity.getUserDigg();
//		  cBoxDiggCount.setEnabled(userDigg==1?false:true);
		//如果userDigg 是1的话，代表了已经赞过，那么cBoxDiggCount必须禁用。所以用!=1
		  cBoxDiggCount.setEnabled(userDigg!=1);
		
		int buryCount=entity.getBuryCount();
		  cBoxBuryCount.setText(Integer.toString(buryCount));
		int userBury=entity.getUserBury();
		//如果userBury 是1的话，代表了已经赞过，那么cBoxDiggCount必须禁用。所以用!=1
		  cBoxBuryCount.setEnabled(userBury!=1);
		
		int commentCount=entity.getCommentCount();
		  btnComment.setText(Integer.toString(commentCount));
		
		//2、再设置图片的数据
		  imgProfileImage.setImageResource(R.drawable.default_round_head);
	}
	
	
	private boolean hasMoved=false;
	private ListView hotCommentListView;
	private ListView recentCommentListView;
	
	/**
	 * 处理ScrollView触摸事件，用于在ScrollView滚动到最下面的时候，自动加载数据
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		boolean bret=false;
		int action=event.getAction();
		if (action==MotionEvent.ACTION_DOWN) {
			bret=true;
			hasMoved=false;
		}else if(action==MotionEvent.ACTION_UP){
			if (hasMoved) {
				int scrollY=scrollView.getScrollY();//滑动的scrollview的高度
				int scrollH=scrollView.getMeasuredHeight();//scroll的高度
				int linearH=scollLinear.getHeight();//内容区的高度
				
				if (scrollY+scrollH>=linearH) {
					//TODO 进行分页评论的加载
					ClientAPI.getComments(queue, groupId, offset, this);
				}
			}
		}else if (action==MotionEvent.ACTION_MOVE) {
			hasMoved=true;
		}
		
		return bret;
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
//							Iterator<String> keys=json.keys();
//							while (keys.hasNext()) {
//								String key = (String) keys.next();
//								Log.i("TestActivity", "JSON key"+key);
//							}
			
							Log.i("TestActivity", "Comment List Json"+response);
			//解析获取到的评论列表									
			CommentList commentList=new CommentList();
			/*
			 * 评论列表包含两组数据，一个是热门评论，一个是新鲜评论
			 * 热门评论和新鲜评论都有可能是空的
			 */
			commentList.parseJson(json);
			
			hasMore = commentList.isHasMore();
			//评论总数
			int totalNumber=commentList.getTotalNumber();
			
			//热门评论列表,可能为空，第一次offset为0时可能有数据
			List<Comment> topComments=commentList.getTopComments();
			//新鲜评论列表,可能有数据
			List<Comment> rtComments=commentList.getRecentComments();
			
			
			if (topComments!=null) {
				hotComments.addAll(topComments);
				hotAdapter.notifyDataSetChanged();
			}
			
			if (rtComments!=null) {
				recentComments.addAll(rtComments);
				recentAdapter.notifyDataSetChanged();
			}
			//测试代码
//			Log.i("TestActivity", "ttttttttt"+commentList.getGroupId());
//			Log.i("TestActivity", "ttttttttt"+commentList.isHasMore());
			
			
			//TODO 直接把CommentList 提交给ListView 的Adapter 这样可以进行是否还有内容的判断
			//利用Adapter更新数据
			
			//分页标识，要求服务器每次必须返回20条评论，通过hasMore来进行判断是否还需要分页
			offset+=20;
			
			//扩充ListView的内容
			int childCount=hotCommentListView.getChildCount();
			if (childCount>0) {
				int tatalHeight=0;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
