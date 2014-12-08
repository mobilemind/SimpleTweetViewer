package com.yahoo.tomking.simpletweet;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.tomking.simpletweet.R;
import com.yahoo.tomking.simpletweet.models.Tweet;

public class ComposeActivity extends Activity {
	private TwitterClient client;
	private Tweet tweet;
	public static final int REQUEST_CODE = 0;
	private EditText etComposeTweet;
	private EditText etCharsAvail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Use Back to cancel", Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_compose);
		etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
		etCharsAvail = (EditText) findViewById(R.id.etCharsAvail);
		client = TwitterApplication.getRestClient();
		setupEditTextViewListener();
	}

	public void onSubmit(View view) {
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

	private void updateCharsAvailable() {
		int charsAvail = 140 - etComposeTweet.getText().toString().length();
		etCharsAvail.setText(String.valueOf(charsAvail));
	}

	private void setupEditTextViewListener() {
		etComposeTweet.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_UP) {
					updateCharsAvailable();
					return false;
				}
				return false;
			}
		});
	}

}
