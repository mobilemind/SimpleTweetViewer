package com.yahoo.tomking.simpletweet.models;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "users")
public class User extends Model implements Serializable{
	private static final String kDESCRIPTION = "description";
	private static final String kFOLLOWERS_COUNT = "followers_count";
	private static final String kFRIENDS_COUNT = "friends_count";
	private static final String kPROFILE_IMAGE_URL = "profile_image_url";
	private static final String kSCREEN_NAME = "screen_name";
	private static final String kUSER_ID = "id";
	private static final String kUSER_NAME = "name";

	// required by convention for serialized data
	private static final long serialVersionUID = 3L;
	
    @Column(name = "uid")
	private long uid;
    
    @Column(name = kUSER_NAME)
	private String name;
    
    @Column(name = kSCREEN_NAME)
	private String screenName;
    
    @Column(name = kPROFILE_IMAGE_URL)
	private String profileImageUrl;
    
    @Column(name = kFOLLOWERS_COUNT)
    private int followersCount;

    @Column(name = kFRIENDS_COUNT)
    private int friendsCount;
    
    @Column(name = kDESCRIPTION)
    private String description;

    
	public User() {
		super();
	}
	
	// Used to return items from another table based on the foreign key
    public List<User> items() {
        return getMany(User.class, "user");
    }
	
	public static User fromJson(JSONObject jsonObject) {
		User u = new User();
		try {
			u.name = jsonObject.getString(kUSER_NAME);
			u.uid = Long.parseLong(jsonObject.getString(kUSER_ID), 10);
			u.screenName = jsonObject.getString(kSCREEN_NAME);
			u.profileImageUrl = jsonObject.getString(kPROFILE_IMAGE_URL);
			u.followersCount = jsonObject.getInt(kFOLLOWERS_COUNT);
			u.friendsCount = jsonObject.getInt(kFRIENDS_COUNT);
			u.description = jsonObject.getString(kDESCRIPTION);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		u.save();
		return u;
	}
	

	public String getName() {
		return name;
	}

	public long getUid() {
		return uid;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public int getFollowersCount() {
		return followersCount;
	}
	

	public int getFriendsCount() {
		return friendsCount;
	}
	
	public String getDescription() {
		return description;
	}

	

}
