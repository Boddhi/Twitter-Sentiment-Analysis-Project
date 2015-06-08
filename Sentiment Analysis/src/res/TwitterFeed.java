package res;

import java.util.ArrayList;

import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterFeed class. Performs string queries on available tweets. Uses the Twitter4J library.
 * @author Tristan Monger
 *
 */
public class TwitterFeed {
	public static String htmlFilePath;
	public static Twitter twitter;
	public static RequestToken requestToken = null;
	public static AccessToken accessToken = null;
	public static String pin;
	public static ConfigurationBuilder cb = new ConfigurationBuilder();
	
	/**
	 * Constructor.
	 */
	public TwitterFeed(){
		initTwitter();
	}
	
	/**
	 * Initializes twitter capabilities and completes authorization.
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
	
	/**
	 * Returns the <code>n</code> most recent tweets that match the given query.
	 * @param queryString the keywords to search for
	 * @param n the number of tweets to retrieve
	 * @return <code>n</code> recent tweets matching <code>queryString </code>
	 */
	public String[] seek(String queryString, int n){
		Query query = new Query(queryString);
		long lastID = Long.MAX_VALUE;
		ArrayList<Status> tweets = new ArrayList<Status>();
		while (tweets.size() < n) {
			if (n - tweets.size() > 100)
				query.setCount(100);
			else
				query.setCount(n - tweets.size());
			try {
				QueryResult result = twitter.search(query);
				tweets.addAll(result.getTweets());
				if(tweets.size() == 0) break;
				for (Status t : tweets)
					if (t.getId() < lastID)
						lastID = t.getId();

			}

			catch (TwitterException te) {
				te.printStackTrace();
				break;
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
		}
		
		return ans;
	}
}
