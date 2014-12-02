package com.yahoo.tomking.simpletweet.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name="tweets")
public class Tweet extends Model implements Serializable{
	private static final String kUSER = "user";
	private static final String kCREATED_AT = "created_at";
	private static final String kID = "id";
	private static final String kTEXT = "text";
	public static final String kTWEET_KEY = "tweet";

	// required by convention for serialized data
	private static final long serialVersionUID = 1L;
	
	@Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	private long uid;
	
    @Column(name = "body")
	private String body;
    
    @Column(name = "createdAt")
	private String createdAt;
    
    @Column(name = kUSER, onUpdate=ForeignKeyAction.CASCADE, onDelete=ForeignKeyAction.CASCADE)
	private User user;
	
	public Tweet() {
		super();
	}

	public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		return getBody() + " - " + getUser().getScreenName();
	}

	public static List<Tweet> fromJson(JSONArray json) {
		List<Tweet> tweets = new ArrayList<>(json.length());
		for (int i = 0; i < json.length(); i++) {
			JSONObject tweetJson = null;
			try {
				tweetJson = json.getJSONObject(i);
			} catch (JSONException e) {
				e.printStackTrace();
				continue;
			}
			Tweet tweet = Tweet.fromJson(tweetJson);
			if (tweet != null) {
				tweets.add(tweet);
			}
			
		}
		return tweets;
	}
	
	public static Tweet fromJson(JSONObject jsonObject) {
		if (jsonObject == null) {
			return null;
		}
		Tweet tweet = new Tweet();
		try {
			tweet.body = jsonObject.getString(kTEXT);
			tweet.uid = jsonObject.getLong(kID);
			tweet.createdAt = jsonObject.getString(kCREATED_AT);
			tweet.user = User.fromJson(jsonObject.getJSONObject(kUSER));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tweet;
	}
	
	public static List<Tweet> getAll() {
        return new Select()
          .all()
          .from(Tweet.class)
          .orderBy("uid DESC")
          .execute();
    }

	public static void saveTweets(List<Tweet> retrievedTweets) {
		for (Tweet t : retrievedTweets) {
			t.getUser().save();
			t.save();
		}
	}
	
}
