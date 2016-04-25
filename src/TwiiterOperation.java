import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;

/**
 * Created by knoldus on 23/4/16.
 */
public class TwiiterOperation {

    static int i = 0;

    public static void main(String args[]) {

        // confriguring twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("I96zJvIJ4ATvciKy48159Vilc")
                .setOAuthConsumerSecret("a4h801XhVQBgWNh1jpLpPsKOabZXyk4sMMtOBXTJPLTsDy5y2Z")
                .setOAuthAccessToken("1897308554-EyXKswFYyK9QmU7En2XKvHb8Z2TLnqN3JmdWebc")
                .setOAuthAccessTokenSecret("03HNq0kYAa0bpmAxmUOGrR5r9Kx6gEMGsq10QWbO7rB5g");
        // creating facory object
        TwitterFactory tf = new TwitterFactory(cb.build());
        List<Post> userstatus = new ArrayList<>();
        twitter4j.Twitter tw = tf.getInstance();
        try {
            List<Status> listOfStatus = tw.getHomeTimeline();
            for (Status status : listOfStatus) {
                    String stat = status.getText();
                    Date date = status.getCreatedAt();
                    int likes = status.getFavoriteCount();
                    String user = status.getUser().toString();
                    int retweet = status.getRetweetCount();
                    userstatus.add(i, new Post(stat, date, retweet, likes, user));
                    i++;

            }

            System.out.println("Sorting...");
            Collections.sort(userstatus, Comparator.comparing(Post::getpostinDate).reversed()
                    .thenComparing(Post::getNoOfRetweets).reversed()
                    .thenComparing(Post::getNoOfLikes).reversed());
            userstatus.forEach(System.out::println);
            System.out.println("sorted by Latest Post");

            Collections.sort(userstatus, Comparator.comparing(Post::getpostinDate).reversed());
            userstatus.forEach(System.out::println);
            System.out.println("sorted by retweets");

            Collections.sort(userstatus, Comparator.comparing(Post::getNoOfRetweets).reversed());
            userstatus.forEach(System.out::println);

            System.out.println("sorted by likes");
            Collections.sort(userstatus, Comparator.comparing(Post::getNoOfLikes).reversed());
            userstatus.forEach(System.out::println);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static class Post {
        String Status;
        Date postingDate;
        int retweet;
        int noOfLikes;
        String Name;

        Post(String Status, Date postingDate, int retweet, int noOfLikes, String Name) {

            this.Status = Status;
            this.postingDate = postingDate;
            this.retweet = retweet;
            this.noOfLikes = noOfLikes;
            this.Name = Name;
        }

        public String getStatus() {
            return Status;
        }
        public Date getpostinDate() {
            return postingDate;
        }
        public int getNoOfLikes() {
            return noOfLikes;
        }
        public String getName() {
            return Name;
        }
        public int getNoOfRetweets() {
            return retweet;
        }

        @Override
        public String toString() {

            return "tweets{" +
                    "status='" + Status + '\'' +
                    ",Name='" + Name.substring(30, 60) + '\'' +
                    ",retweet='" + retweet + '\'' +
                    ",likes='" + noOfLikes + '\'' +
                    '}';
        }

    }
}
