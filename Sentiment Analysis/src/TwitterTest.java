import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTest {

	/**
	 * @param args
	 */

	public static void main(String args[]) throws Exception {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("nQZ4c9w1beTSPR4fT7HaJ1hGN")
				.setOAuthConsumerSecret(
						"dMXkSXV0FZDGTyQdBOypHxii8Xty8r0QqqGUjwBfozpoSU7m6q")
				.setOAuthAccessToken(
						"3192494632-s3SRp7ZEKLjbgJE3c5eYap8TUyxEOy4mHRFMdJO")
				.setOAuthAccessTokenSecret(
						"6mODPqDKXvOtvSrNNYbaQFGN3MSKR3cmCVAElAI9ZUSZp");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		Query query = new Query("Jenner");
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

		for (int i = 0; i < tweets.size(); i++) {
			Status t = (Status) tweets.get(i);
			String msg;
			if(t.isRetweet()) msg = t.getRetweetedStatus().getText();
			else msg = t.getText();
			
			System.out.println("@" + t.getUser().getScreenName() + " - " + msg);
		}

	}
}
