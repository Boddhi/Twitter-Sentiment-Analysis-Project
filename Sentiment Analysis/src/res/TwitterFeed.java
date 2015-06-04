package res;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFeed {
	public static String htmlFilePath;
	public static Twitter twitter;
	public static RequestToken requestToken = null;
	public static AccessToken accessToken = null;
	public static String pin;
	public static ConfigurationBuilder cb = new ConfigurationBuilder();
	
	public TwitterFeed(){
		initTwitter();
	}
	
	/**
	 * Initializes twitter capabilities.
	 */
	public void initTwitter(){
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("nQZ4c9w1beTSPR4fT7HaJ1hGN")
		.setOAuthConsumerSecret(
				"dMXkSXV0FZDGTyQdBOypHxii8Xty8r0QqqGUjwBfozpoSU7m6q")
		.setOAuthAccessToken(
				"3192494632-s3SRp7ZEKLjbgJE3c5eYap8TUyxEOy4mHRFMdJO")
		.setOAuthAccessTokenSecret(
				"6mODPqDKXvOtvSrNNYbaQFGN3MSKR3cmCVAElAI9ZUSZp");
		twitter = new TwitterFactory(cb.build()).getInstance();
	}
	
	
	public String[] seek(String queryString){
		Query query = new Query(queryString);
		int numberOfTweets = 512;
		long lastID = Long.MAX_VALUE;
		ArrayList<Status> tweets = new ArrayList<Status>();
		while (tweets.size() < numberOfTweets) {
			if (numberOfTweets - tweets.size() > 100)
				query.setCount(100);
			else
				query.setCount(numberOfTweets - tweets.size());
			try {
				QueryResult result = twitter.search(query);
				tweets.addAll(result.getTweets());
				System.out.println("Gathered " + tweets.size() + " tweets");
				if(tweets.size() == 0) break;
				for (Status t : tweets)
					if (t.getId() < lastID)
						lastID = t.getId();

			}

			catch (TwitterException te) {
				System.out.println("Couldn't connect: " + te);
				te.printStackTrace();
			}
			;
			query.setMaxId(lastID - 1);
		}
		String[] ans = new String[tweets.size()];
		
		for (int i = 0; i < tweets.size(); i++) {
			Status t = (Status) tweets.get(i);
			String msg;
			if(t.isRetweet()) msg = t.getRetweetedStatus().getText();
			else msg = t.getText();
			ans[i] = msg;
			//System.out.println("@" + t.getUser().getScreenName() + " - " + msg);
		}
		
		return ans;
	}
}
