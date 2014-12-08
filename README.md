SimpleTweetViewer
====
## Overview
A simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.
Week 4 & 5 Project for Codepath Intro to Android.

Time Spent: 16 hours

## User Stories

### Mandatory
#### Week 4
[X] User can sign in using OAuth login flow
[X] User can view last 25 tweets from their home timeline 
    [X] User should be able to see the user, body and timestamp for tweet 
    [X] User should be displayed the relative timestamp for a tweet "8m", "7h" 
    [X] __OPTIONAL__: Links in tweets are clickable and viewable 
[X] User can load more tweets once they reach the bottom of the list using "infinite scroll" pagination 
[X] User can compose a new tweet 
    [X] User can click a "Compose" icon in the Action Bar on the top right 
    [X] User will have a Compose view opened 
    [X] User can enter a message and hit a button to post to twitter 
    [X] User should be taken back to home timeline with new tweet visible 
    [X] __OPTIONAL__: User can see a counter with total number of characters left for tweet 

#### Week 5
[X] User can switch between Timeline and Mention views using tabs. 
    [X] User can view their home timeline tweets. 
    [X] User can view the recent mentions of their username. 
    [X] User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll") 
    [X] __OPTIONAL__: Implement this in a gingerbread-compatible approach 
[X] User can navigate to view their own profile. 
    [X] User can see picture, tagline, # of followers, # of following, and tweets on their profile. 
[X] User can click on the profile image in any tweet to see another user's profile. 
    [X] User can see picture, tagline, # of followers, # of following, and tweets of clicked user. 
    [X] Profile view should include that user's timeline. 
    [ ] __OPTIONAL__: User can view following / followers list through the profile 

### Optional
#### Week 4
[X] User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh) 
[ ] User can open the twitter app offline and see last loaded tweets 
    [ ] Tweets are persisted into SQLite and can be displayed from the local DB 
[ ] User can tap a tweet to display a "detailed" view of that tweet 
[ ] User can select "reply" from detail view to respond to a tweet 
[ ] Improve the user interface and theme the app to feel twitter branded 
[ ] User can see an embedded media (image) within tweet detail view 
[ ] Compose View activity is replaced with a modal overlay 

#### Week 5
[X] When a network request goes out, user sees an indeterminate progress indicator 
[ ] User can "reply" to any tweet on their home timeline 
    [ ] The user that wrote the original tweet is automatically "@" replied in compose 
[ ] User can click on a tweet to be taken to a "detail view" of that tweet 
    [ ] User can take favorite (and unfavorite) or reweet actions on a tweet 
[ ] Improve the user interface and theme the app to feel twitter branded 
[ ] User can search for tweets matching a particular query and see results 
[ ] __STRETCH__: User can view their direct messages (or send new ones) 

## Walkthroughs

![GIF Walkthrough](SimpleTweetViewer.gif)

GIFs created by LiceCap <http://www.cockos.com/licecap/>
