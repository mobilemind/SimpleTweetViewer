package com.yahoo.tomking.simpletweet;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.yahoo.tomking.simpletweet.R;
import com.yahoo.tomking.simpletweet.fragments.HomeTimeLineFragment;
import com.yahoo.tomking.simpletweet.fragments.MentionsTimeLineFragment;
import com.yahoo.tomking.simpletweet.listeners.FragmentTabListener;


public class TimelineActivity extends FragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
        setupTabs();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == ComposeActivity.REQUEST_CODE) {
			((HomeTimeLineFragment)getFragmentManager().findFragmentByTag("home")).handleIntentData(data);
		}
	}

	public void onComposeAction(MenuItem item) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, ComposeActivity.REQUEST_CODE);
	}
	
	public void onProfileView(MenuItem item) {
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
		
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.setDisplayShowTitleEnabled(true);

            Tab tab1 = actionBar
                    .newTab()
                    .setText("Home")
                    .setIcon(R.drawable.ic_home)
                    .setTag("HomeTimeLineFragment")
                    .setTabListener(
                            new FragmentTabListener<>(R.id.flContainer, this, "home",
                                    HomeTimeLineFragment.class));
            actionBar.addTab(tab1);
            Tab tab2 = actionBar
                    .newTab()
                    .setText("Mentions")
                    .setIcon(R.drawable.ic_mentions)
                    .setTag("MentionsTimeLineFragment")
                    .setTabListener(
                            new FragmentTabListener<>(
                                    R.id.flContainer, this, "mentions",
                                    MentionsTimeLineFragment.class));
            actionBar.addTab(tab2);

            actionBar.selectTab(tab1);
        }
	}

}
