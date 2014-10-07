package com.seven7.neihan.adapter;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

import com.seven7.neihan.R;
import com.seven7.neihan.bean.TextEntity;
import com.seven7.neihan.bean.UserEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EssayAdapter extends BaseAdapter {
	
	private Context context;
	private List<TextEntity> textEntities;
	private LayoutInflater inflater;
	
	private OnClickListener clickListener; 
	
	public EssayAdapter(Context context,List<TextEntity> textEntities) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.textEntities=textEntities;
		inflater=LayoutInflater.from(context);
	}
	
	

	/**
	 * @param clickListener the clickListener to set
	 */
	public void setClickListener(OnClickListener clickListener) {
		this.clickListener = clickListener;
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return textEntities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return textEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view=convertView;
		if (convertView==null) {
				view=inflater.inflate(R.layout.item, parent,false);			
		}
		if (view!=null) {
			ViewHolder holder=(ViewHolder)view.getTag();
			//加载组件
			if (holder==null) {
				holder=new ViewHolder();
				holder.btnGifPlay=(TextView)view.findViewById(R.id.item_gifplay);
				holder.gifImageView=(GifImageView)view.findViewById(R.id.item_gifview);
				holder.btnComment=(TextView)view.findViewById(R.id.item_comment);
				holder.cBoxBuryCount=(CheckBox)view.findViewById(R.id.item_count_bury);
				holder.cBoxDiggCount=(CheckBox)view.findViewById(R.id.item_count_digg);
				holder.imgBtnShare=(ImageButton)view.findViewById(R.id.item_share);
				holder.imgProfileImage=(ImageView)view.findViewById(R.id.item_profile_iamge);
				holder.pdDownloadProgress=(ProgressBar)view.findViewById(R.id.item_image_download_progress);
				holder.txtContent=(TextView)view.findViewById(R.id.item_content);
				holder.txtProfileNick=(TextView)view.findViewById(R.id.item_profile_nick);
				
				view.setTag(holder);
			}
			
			TextEntity textEntity=textEntities.get(position);
			
			//1、先设置文本内容的数据
			
			UserEntity userEntity=textEntities.get(position).getUser();
			String nick="未知用户";
			if (userEntity!=null) {
				nick=userEntity.getName();
			}
			holder.txtProfileNick.setText(nick);
			
			String content=textEntity.getContent();
			holder.txtContent.setText(content);
			holder.txtContent.setOnClickListener(clickListener);
			holder.txtContent.setTag(Integer.toString(position));
			
			int diggCount=textEntity.getDiggCount();
			holder.cBoxDiggCount.setText(Integer.toString(diggCount));			
			int userDigg=textEntity.getUserDigg();
//			holder.cBoxDiggCount.setEnabled(userDigg==1?false:true);
			//如果userDigg 是1的话，代表了已经赞过，那么cBoxDiggCount必须禁用。所以用!=1
			holder.cBoxDiggCount.setEnabled(userDigg!=1);
			
			int buryCount=textEntity.getBuryCount();
			holder.cBoxBuryCount.setText(Integer.toString(buryCount));
			int userBury=textEntity.getUserBury();
			//如果userBury 是1的话，代表了已经赞过，那么cBoxDiggCount必须禁用。所以用!=1
			holder.cBoxBuryCount.setEnabled(userBury!=1);
			
			int commentCount=textEntity.getCommentCount();
			holder.btnComment.setText(Integer.toString(commentCount));
			
			//2、再设置图片的数据
			holder.imgProfileImage.setImageResource(R.drawable.default_round_head);
		}
		
		
	
		return view;
	}
	
	public static class ViewHolder{
		/**
		 * 用户头像
		 */
		public ImageView imgProfileImage;
		/**
		 * 用户昵称
		 */
		public TextView txtProfileNick;
		/**
		 * 文本内容
		 */
		public TextView txtContent;
		/**
		 * 下载进度条
		 */
		public ProgressBar pdDownloadProgress;
		/**
		 * 图片
		 */
		public GifImageView gifImageView;
		/**
		 * 开启gif播放按钮
		 */
		public TextView btnGifPlay;
		/**
		 * 赞
		 */
		public CheckBox cBoxDiggCount;
		/**
		 * 踩
		 */
		public CheckBox cBoxBuryCount;
		/**
		 * 评论个数
		 */
		public TextView btnComment;
		/**
		 * 分享
		 */
		public ImageButton imgBtnShare;
	}

}
