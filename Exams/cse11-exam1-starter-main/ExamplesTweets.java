
class User {
  String name;
  String email;
  int followers;
  User(String name, String email, int followers) {
    this.name = name;
    this.email = email;
    this.followers = followers;
  }
}

class Tweet {
  User author;
  String content;
  String tweetId;
  int likes;
  Date datePosted;
  Tweet(User author, String content, String tweetId, int likes, Date datePosted) {
    this.author = author;
    this.content = content;
    this.tweetId = tweetId;
    this.likes = likes;
    this.datePosted = datePosted;
  }
  // Instance method that takes another tweet as an argument
  // Returns true if this tweet was posted earlier that tweet in argument
  boolean before(Tweet t) {
    if (this.datePosted.year < t.datePosted.year) {
      return true;
    } else if (this.datePosted.year == t.datePosted.year) {
      if (this.datePosted.month < t.datePosted.month) {
        return true;
      } else if (this.datePosted.month == t.datePosted.month) {
        if (this.datePosted.day < t.datePosted.day) {
          return true;
        }
      }
    }
    return false;
  }
}

class Date {
  int year;
  int month;
  int day;
  // Following the MM/DD/YY format
  Date(int month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
  }
}

class ExamplesTweets {
  /*
  tweetExample1 – Two different Tweets posted on different months in the same year that returns false
  tweetExample2 – Two different Tweets posted on different days in the same month and same year that returns true
  tweetExample3 – The same Tweet used as both this and as the argument to before.
  tweetExample4 – Two different Tweets posted in different years, with the this Tweet having an earlier month and day than the argument, and that returns false
  */
  User u1 = new User("Max Rivett", "mrivett@ucsd.edu", 100);
  User u2 = new User("Joe Smith", "joe@gmail.com", 50);
  Tweet t1 = new Tweet(u1, "Happy Halloween!", "1688513816885138", 2500, new Date(10, 31, 2020));
  Tweet t2 = new Tweet(u2, "Hello World!", "3141592653589793", 5, new Date(7, 15, 2020));
  Tweet t3 = new Tweet(u1, "It's almost Halloween!", "1234567898765432", 1500, new Date(10, 24, 2020));
  Tweet t4 = new Tweet(u2, "Happy New Years!", "1111111111111111", 3, new Date(1, 1, 2021));
  boolean tweetExample1 = this.t1.before(t2); // expect false
  boolean tweetExample2 = this.t3.before(t1); // expect true
  boolean tweetExample3 = this.t2.before(t2); // expect false
  boolean tweetExample4 = this.t4.before(t2); // expect false

}