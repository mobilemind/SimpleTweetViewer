package com.yahoo.tomking.simpletweet.listeners;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
 
public class FragmentTabListener<T extends Fragment> implements TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private final int mfragmentContainerId;
    private final Bundle mfragmentArgs;


    public FragmentTabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = android.R.id.content;
        mfragmentArgs = new Bundle();
    }
        
    public FragmentTabListener(int fragmentContainerId, Activity activity, 
            String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = new Bundle();
    }
    
    public FragmentTabListener(int fragmentContainerId, Activity activity, 
            String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = args;
    }
 
 
    @Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
        FragmentTransaction sft = mActivity.getFragmentManager().beginTransaction();
        if (mFragment == null) {
            // instantiate & initialize fragment for this activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), mfragmentArgs);
            sft.add(mfragmentContainerId, mFragment, mTag);
        } else {
            sft.attach(mFragment);
        }
        sft.commit();
    }
 
    @Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
        FragmentTransaction sft = mActivity.getFragmentManager().beginTransaction();
        if (mFragment != null) {
            // detach old fragment to prepare for new
            sft.detach(mFragment);
        }
        sft.commit();
    }
 
    @Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
      // same tab again- potentially could be a refresh equivalent
    }
}
