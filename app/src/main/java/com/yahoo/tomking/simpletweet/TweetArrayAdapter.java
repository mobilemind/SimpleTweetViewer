package com.yahoo.tomking.simpletweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.tomking.simpletweet.R;
import com.yahoo.tomking.simpletweet.models.Tweet;
import com.yahoo.tomking.simpletweet.models.User;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.tweet_item, parent, false);
		} else {
			v = convertView;
		}

		ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
		TextView tvUserName = (TextView) v.findViewById(R.id.tvScreenName);
		TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
		TextView tvTimestamp = (TextView) v.findViewById(R.id.tvTimestamp);

		Tweet tweet = getItem(position);
		User user = tweet.getUser();

		setImage(ivProfileImage, user.getProfileImageUrl());
		ivProfileImage.setTag(user);
		ivProfileImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), ProfileActivity.class);
				i.putExtra("user", (User) v.getTag());
				getContext().startActivity(i);
			}
		});
		tvUserName.setText(user.getScreenName());
		tvBody.setText(tweet.getBody());
		tvTimestamp.setText(getRelativeTime(tweet.getCreatedAt()));

		return v;
	}

	private void setImage(ImageView ivProfileImage, String profileImageUrl) {
		ivProfileImage.setImageResource(android.R.color.transparent);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(profileImageUrl, ivProfileImage);
	}

	// getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
	public static String getRelativeTime(String rawJsonDate) {
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
		sdf.setLenient(true);

		String relativeDate = "";
		try {
			long dateMillis = sdf.parse(rawJsonDate).getTime();
			relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return relativeDate;
	}

}
