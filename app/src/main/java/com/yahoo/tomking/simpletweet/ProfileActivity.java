package com.yahoo.tomking.simpletweet;

import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.tomking.simpletweet.R;
import com.yahoo.tomking.simpletweet.fragments.UserTimelineFragment;
import com.yahoo.tomking.simpletweet.models.User;

public class ProfileActivity extends FragmentActivity {

	private UserTimelineFragment userTimelineFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Use Back to return to timeline", Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_profile);
		Intent i = getIntent();
		User user = (User) i.getSerializableExtra("user");
		userTimelineFragment = (UserTimelineFragment) getFragmentManager().findFragmentById(R.id.fragmentUserTimeline);
		userTimelineFragment.setUser(user);
		userTimelineFragment.getTimeline();
		loadProfileInfo(user);
	}

	private void loadProfileInfo(User user) {
		if (user == null) {
			TwitterApplication.getRestClient().getMyInfo(
					new JsonHttpResponseHandler() {
						@Override
						public void onSuccess(JSONObject json) {
							User u = User.fromJson(json);
							loadProfileResults(u);
						}

					});
		} else {
			loadProfileResults(user);
		}
	}

	private void loadProfileResults(User u) {
        ActionBar myAB = getActionBar();
        if (null != myAB) {
            myAB.setTitle("@" + u.getScreenName());
        }
		populateProfileHeader(u);
		populateProfileList(u);
	}

	private void populateProfileList(User u) {
		userTimelineFragment.setUser(u);
		userTimelineFragment.populateTimeline();
	}

	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvBio = (TextView) findViewById(R.id.tvBio);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

		tvName.setText(u.getName());
		tvBio.setText(u.getDescription());
		tvFollowers.setText(u.getFollowersCount() + " Followers");
		tvFollowing.setText(u.getFriendsCount() + " Following");

		ImageLoader.getInstance().displayImage(u.getProfileImageUrl(),
				ivProfileImage);

	}
}
