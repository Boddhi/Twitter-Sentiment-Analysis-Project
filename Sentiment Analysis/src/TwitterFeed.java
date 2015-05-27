import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterFeed {
	public static String htmlFilePath;
	public static Twitter twitter;
	public static RequestToken requestToken = null;
	public static AccessToken accessToken = null;
	public static String pin;
	
	public TwitterFeed(){
		
	}
	
	/**
	 * Initializes twitter capabilities.
	 */
	public static void initTwitter(){
		twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer("nQZ4c9w1beTSPR4fT7HaJ1hGN",
				"dMXkSXV0FZDGTyQdBOypHxii8Xty8r0QqqGUjwBfozpoSU7m6q");
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		htmlFilePath = requestToken.getAuthorizationURL().toString();
	}
	
	public static void openTwitter(){
		try {
			Desktop.getDesktop().browse(new URI(htmlFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public String[] seek(){
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query("source:twitter4j yusukey");
	    QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }
	    String[] ans = new String[20];
	    return ans;
	}
}
