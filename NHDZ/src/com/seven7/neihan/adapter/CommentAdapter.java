package com.seven7.neihan.adapter;

import java.util.Date;
import java.util.List;

import cn.sharesdk.wechat.utils.c;

import com.seven7.neihan.R;
import com.seven7.neihan.bean.Comment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	
	private List<Comment> comments;
	
	private LayoutInflater inflater;
	public CommentAdapter(Context context,List<Comment> comments) {
		// TODO Auto-generated constructor stub
		this.comments=comments;	
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return comments.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return comments.get(position).getId();
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=convertView;
		if (convertView==null) {
			view=inflater.inflate(R.layout.item_comment, parent,false);
			
		}
		
		if (view!=null) {
			
			ViewHolder holder=(ViewHolder) view.getTag();
			
			if (holder==null) {
				
				holder=new ViewHolder();
				
				holder.imgViewproFile=(ImageView)view.findViewById(R.id.comment_profile_img);
				holder.cBoxDigg=(CheckBox)view.findViewById(R.id.item_count_digg);
				holder.txtContent=(TextView)view.findViewById(R.id.comment_content);
				holder.txtCreateTime=(TextView)view.findViewById(R.id.comment_create_time);
				holder.txtProFileNick=(TextView)view.findViewById(R.id.comment_profile_nick);
				
				view.setTag(holder);
			}
			
			Comment comment=comments.get(position);
			Log.i("CommentAdapter", comment.getUserName().toString());
			if (comment!=null) {
				holder.txtProFileNick.setText(comment.getUserName());
				holder.txtContent.setText(comment.getText());
			}else {
				holder.txtProFileNick.setText("未知用户");
				holder.txtContent.setText("加载评论失败！");
			}
			
			
			Date data=new Date(comment.getCreateTime());
			holder.txtCreateTime.setText(data.toString());
			
			int diggCount=comment.getDiggCount();
			holder.cBoxDigg.setText(Integer.toString(diggCount));
			
			int userDigg=comment.getuserDigg();
			holder.cBoxDigg.setEnabled(userDigg==0);
			
			
		}
		return view;
	}

	public static class ViewHolder{
		private ImageView imgViewproFile;
		private TextView txtProFileNick;
		private TextView txtCreateTime;
		private TextView txtContent;
		private CheckBox cBoxDigg;
	}
	
}
