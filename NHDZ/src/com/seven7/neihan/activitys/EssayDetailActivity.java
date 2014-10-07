package com.seven7.neihan.activitys;

import java.util.List;

import cn.sharesdk.framework.p;

import com.seven7.neihan.R;
import com.seven7.neihan.R.layout;
import com.seven7.neihan.R.menu;
import com.seven7.neihan.adapter.DetailPagerAdapter;
import com.seven7.neihan.bean.DataStore;
import com.seven7.neihan.bean.TextEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class EssayDetailActivity extends FragmentActivity {

	private ViewPager pager;
	private DetailPagerAdapter pagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essay_detail);
		
		pager=(ViewPager)this.findViewById(R.id.detail_pager_content);
		
		//设置FragmentPagerAdapter		
		Intent intent=getIntent();
		Bundle extras=intent.getExtras();
		int category=1;
		int currentEssayPosition=0;
		if (extras!=null) {
			category=extras.getInt("category",1);
			currentEssayPosition=extras.getInt("currentEssayPosition",0);
		}
		
		DataStore dataStore=DataStore.getInstance();
		List<TextEntity> entities;
		
		if (category==1) {
			entities=dataStore.getTextEntities();
		}else {
			entities=dataStore.getImageEntities();

		}
		
		pagerAdapter=new DetailPagerAdapter(getSupportFragmentManager(), entities);
		pager.setAdapter(pagerAdapter);
		
		if (currentEssayPosition>0) {
			pager.setCurrentItem(currentEssayPosition);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.essay_detail, menu);
		return true;
	}

}
