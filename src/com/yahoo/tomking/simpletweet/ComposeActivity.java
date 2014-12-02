package com.yahoo.tomking.simpletweet;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.tomking.simpletweet.R;
import com.yahoo.tomking.simpletweet.models.Tweet;

public class ComposeActivity extends Activity {
	private TwitterClient client;
	private Tweet tweet;
	public static final int REQUEST_CODE = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		client = TwitterApplication.getRestClient();

	}
	
	public void onSubmit(View view) {
		EditText etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
		
		String tweetText = etComposeTweet.getText().toString();
		client.postNewTweet(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				tweet = Tweet.fromJson(json);
				Intent i = new Intent();
				i.putExtra(Tweet.kTWEET_KEY, tweet);
				setResult(RESULT_OK, i);
				finish();
			}
		}, tweetText);
	}
}
