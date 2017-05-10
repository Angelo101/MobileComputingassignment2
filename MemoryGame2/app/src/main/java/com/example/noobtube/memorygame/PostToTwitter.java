package com.example.noobtube.memorygame;

/**
 * Created by noobtube on 10/05/2017.
 */

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static android.R.id.message;

/**
 * This class demonstrate how you can post a Tweet in Java using the Twitter REST API and an open source third party
 * twitter integration library in java called Twitter4J
 *
 * User: smhumayun
 * Date: 7/20/13
 * Time: 9:26 AM
 */
public class PostToTwitter {

    public static void postToTwitter() throws IOException, TwitterException {

        //Your Twitter App's Consumer Key
//        String consumerKey = "kiMjePqMImKXjjrvgWpo649H8";
//
//        //Your Twitter App's Consumer Secret
//        String consumerSecret = "EaJiRXx8LMRZsCvoCAz3pVgkp24NjLYa8GmzGR5IMRrox5wefM";

        //Your Twitter Access Token
        String accessToken = "861865585541488640-IDvQwo69f0HvFAwGuv3D7J9oYNzIWkL";

        //Your Twitter Access Token Secret
        String accessTokenSecret = "SbOIAvovTchstz1fOQFxxDXdHFit9drYwbjG5ffhmiqQ6";

        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();

        //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();

        //setup OAuth Consumer Credentials
//        twitter.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        //Instantiate and initialize a new twitter status update

        StatusUpdate statusUpdate = new StatusUpdate("status");
        twitter.updateStatus(statusUpdate);
    }

}