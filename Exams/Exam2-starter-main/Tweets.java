// EXAM INSTRUCTIONS:
// All of your code for Task 2 goes in this file.
// Add new method headers and implementations as appropriate to these classes
// Add examples to the ArrayExamples class.
import tester.*;

interface Tweet {
  public int lengthOfLongestTweetInThread();
  public int timesAuthorPostedInThread(User u);
  // Helper methods
  public String allContents();
  public String allAuthors();
  public int allTweets();
}
class User {
  String username, displayName;
  User(String username, String displayName) {
    this.username = username;
    this.displayName = displayName;
  }
}
class TextTweet implements Tweet {
  User author;
  String contents;
  int likes;
  TextTweet(User author, String contents, int likes) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
  }
  public int lengthOfLongestTweetInThread() {
    return contents.length();
  }
  public int timesAuthorPostedInThread(User u) {
    if (u.username.equals(this.author.username)) {
      return 1;
    }
    return 0;
  }
  public String allContents() {
    return this.contents;
  }
  public String allAuthors(){ return this.author.username; }
  public int allTweets(){return 1;}
}
class ReplyTweet implements Tweet {
  User author;
  String contents;
  int likes;
  Tweet replyTo;
  ReplyTweet(User author, String contents, int likes, Tweet replyTo) {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
    this.replyTo = replyTo;
  }
  public int lengthOfLongestTweetInThread() {
    String str = this.contents+ ";" + this.replyTo.allContents();
    int num = this.allTweets();
    String[] contents = new String[num];
    contents = str.split(";");
    int longest = contents[0].length();
    for (int i = 1; i < num; i++) {
      if (contents[i].length() > longest) {
        longest = contents[i].length();
      }
    }
    return longest;
    
  }
  public int timesAuthorPostedInThread(User u) {
    String str = this.author.username + ";" + this.replyTo.allAuthors();
    int num = this.allTweets();
    String[] users = new String[num];
    users = str.split(";");
    int times = 0;
    for (int i = 0; i < num; i++) {
      if (users[i].equals(u.username)) {
        times++;
      }
    }
    return times;
  }
  public String allContents() {
    String str = this.contents;
    if (this.replyTo.allContents().length() > 0) {
        str += ";" + this.replyTo.allContents();
    } 
    return str;
  }
  public String allAuthors(){
    String str = this.author.username;
    if (this.replyTo.allAuthors().length() > 0) {
        str += ";" + this.replyTo.allAuthors();
    } 
    return str;
  }
  public int allTweets() {
    int num = 0;
    if (this.replyTo.allTweets() >= 0) {
      num += 1+this.replyTo.allTweets();
    }
    return num;
  }
}

class ExamplesTweets {
  User joe = new User("joepolitz", "Joe Gibbs Politz");
  User greg = new User("gregory_miranda", "Greg Miranda");
  Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
  Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);

  User max = new User("max_rivett", "Max Rivett");
  User bot = new User("testbot", "Robot");
  User neverUsed = new User("not used", "Not Used Here");
  Tweet tweet1 =  new TextTweet(this.max, "These new tasks for the exam are very specific.", 600);
  Tweet tweet2 =  new ReplyTweet(this.bot, "Yes, they are meant to test your program", 50, this.tweet1);
  Tweet tweet3 =  new ReplyTweet(this.max, "I feel like I'm spending more time writing tests that I did my program though.", 151, this.tweet2);
  Tweet tweet4 =  new ReplyTweet(this.bot, "Okay.", 3, this.tweet3);



  void testLongestTweetInThread(Tester t) {
    t.checkExpect(this.t2.lengthOfLongestTweetInThread(), 41);
  }

  void testAuthorPostedInThread(Tester t) {
    t.checkExpect(this.t1.timesAuthorPostedInThread(joe), 1);
    t.checkExpect(this.t1.timesAuthorPostedInThread(greg), 0);
  }

  void testCase(Tester t) {
    t.checkExpect(this.tweet4.lengthOfLongestTweetInThread(), 78);
    t.checkExpect(this.tweet3.lengthOfLongestTweetInThread(), 78);
    t.checkExpect(this.tweet2.lengthOfLongestTweetInThread(), 47);
    t.checkExpect(this.tweet1.lengthOfLongestTweetInThread(), 47);
    t.checkExpect(this.tweet4.timesAuthorPostedInThread(max), 2);
    t.checkExpect(this.tweet4.timesAuthorPostedInThread(bot), 2);
    t.checkExpect(this.tweet4.timesAuthorPostedInThread(neverUsed), 0);
    t.checkExpect(this.tweet3.timesAuthorPostedInThread(max), 2);
    t.checkExpect(this.tweet3.timesAuthorPostedInThread(bot), 1);
  }
/*
  class         method	                        reference           return value
  TextTweet		  lengthOfLongestTweetInThread	  :9                  47
  ReplyTweet	  lengthOfLongestTweetInThread    :10                 40
  ReplyTweet    lengthOfLongestTweetInThread    :11                 78
  ReplyTweet    lengthOfLongestTweetInThread    :12                 5

  for: t.checkExpect(this.tweet4.lengthOfLongestTweetInThread(), 78);
  */
}
    