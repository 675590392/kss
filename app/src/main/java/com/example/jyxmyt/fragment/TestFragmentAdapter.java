package com.example.jyxmyt.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//import com.viewpagerindicator.IconPagerAdapter;

public class TestFragmentAdapter extends FragmentPagerAdapter{
	private ViewFragment viewFragment;
	private MoreFragment moreFragment;
	public TestFragmentAdapter(FragmentManager fm){
		super(fm);
		viewFragment = new ViewFragment();
		moreFragment = new MoreFragment();
	}

	@Override
	public Fragment getItem(int position){
		if (position == 0) {
			return viewFragment;
		}
		if (position==1) {
			return moreFragment;
		}
		return null;
	}

	@Override
	public int getCount(){
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position){
		return null;
	}

//	@Override
//	public int getIconResId(int index){
//		return index;
//	}
}