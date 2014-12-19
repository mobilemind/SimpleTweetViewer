package com.yahoo.tomking.simpletweet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActivity;
import com.yahoo.tomking.simpletweet.R;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	// inflate menu to adds items to the action bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// called if OAuth succeeds...
	@Override
	public void onLoginSuccess() {
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
		Toast.makeText(this, "Authenticated with twitter.com", Toast.LENGTH_SHORT).show();
	}

	// called if OAuth fails...
	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, "Unable to authenticate with twitter.com", Toast.LENGTH_SHORT).show();
	}

	// start OAuth flow
	public void loginToRest(View view) {
		getClient().connect();
	}

}
