package com.seven7.neihan.adapter;

import java.util.List;

import com.seven7.neihan.bean.TextEntity;
import com.seven7.neihan.fragments.DetailContentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailPagerAdapter extends FragmentPagerAdapter{
	
	private List<TextEntity> entities;

	public DetailPagerAdapter(FragmentManager fm,List<TextEntity> entities) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.entities=entities;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		DetailContentFragment fragment=new DetailContentFragment();
		Bundle arguments=new Bundle();
		
		TextEntity entity=entities.get(arg0);
		arguments.putSerializable("entity", entity);
		
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entities.size();
	}

}
