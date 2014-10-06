package com.seven7.neihan.activitys;

import java.util.LinkedList;
import java.util.List;

import com.seven7.neihan.R;
import com.seven7.neihan.fragments.HuoDongFragment;
import com.seven7.neihan.fragments.ImageListFragment;
import com.seven7.neihan.fragments.MineFragment;
import com.seven7.neihan.fragments.ReviewFragment;
import com.seven7.neihan.fragments.TextListFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {
	
	private List<Fragment> fragments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RadioGroup ridioGroup=(RadioGroup) findViewById(R.id.main_top_bar);
		ridioGroup.setOnCheckedChangeListener(this);
		
		fragments=new LinkedList<Fragment>();
		fragments.add(new TextListFragment());
		fragments.add(new ImageListFragment());
		fragments.add(new ReviewFragment());
		fragments.add(new HuoDongFragment());
		fragments.add(new MineFragment());
		
		Fragment fragment=fragments.get(0);
		FragmentManager manager=getSupportFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.main_fragment_content, fragment);
		transaction.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int childCount=group.getChildCount();
		int checkIndex=0;
		RadioButton btn=null;
		for (int i = 0; i < childCount; i++) {
			btn=(RadioButton) group.getChildAt(i);
			if (btn.isChecked()) {
				checkIndex=i;
				break;
			}
		}
		
		Fragment fragment=fragments.get(checkIndex);
		
		FragmentManager manager=getSupportFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.main_fragment_content, fragment);
		transaction.commit();
	}
	

}
