SimpleTweetViewer
====
## Overview
A simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.
Week 4 Project for Codepath Intro to Android.

Time Spent: #.# hours

## User Stories

### Mandatory
1. [ ] User can sign in using OAuth login flow
2. [ ] User can view last 25 tweets from their home timeline 
    a. [ ] User should be able to see the user, body and timestamp for tweet 
    b. [ ] User should be displayed the relative timestamp for a tweet "8m", "7h" 
    c. [ ] __OPTIONAL__: Links in tweets are clickable and viewable 
3. [ ] User can load more tweets once they reach the bottom of the list using "infinite scroll" pagination 
4. [ ] User can compose a new tweet 
    a. [ ] User can click a “Compose” icon in the Action Bar on the top right 
    b. [ ] User will have a Compose view opened 
    c. [ ] User can enter a message and hit a button to post to twitter 
    d. [ ] User should be taken back to home timeline with new tweet visible 
    e. [ ] __OPTIONAL__: User can see a counter with total number of characters left for tweet 

### Optional
5. [ ] User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
6. [ ] User can open the twitter app offline and see last loaded tweets 
    a. [ ] Tweets are persisted into sqlite and can be displayed from the local DB 
7. [ ] User can tap a tweet to display a "detailed" view of that tweet
8. [ ] User can select "reply" from detail view to respond to a tweet
9. [ ] Improve the user interface and theme the app to feel twitter branded
10. [ ] User can see an embedded media (image) within tweet detail view
11. [ ] Compose View activity is replaced with a modal overlay

## Walkthroughs

![GIF Walkthrough](SimpleTweetViewer.gif)

GIFs created by LiceCap <http://www.cockos.com/licecap/>
