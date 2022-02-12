import javax.swing.text.AbstractDocument.Content;

class User {
    String username;
    String fullName;
    int followers;
    // Basic constructor to initialize fields
    User (String username, String fullName, int followers) {
        this.username = username;
        this.fullName = fullName;
        this.followers = followers;
    }
    // Instance method
    // Returns the user's full name followed by their username
    // Fails in the case that either argument does not exist
    String toText() {
        return this.fullName + " @" + this.username;
    }
}

class Tweet {
    String content;
    User user;
    int likes;
    String id; // Could also be a long, but not int
    // Basic constructor to initialize fields
    Tweet (String content, User user, int likes, String id) {
        this.content = content;
        this.user = user;
        this.likes = likes;
        this.id = id;
    }
    // Instance Method
    // Returns true if the user's tweet is longer than the other, false otherwise
    // Fails if either argument doesn't exist
    boolean longerThan(Tweet other) {
        return this.content.length() > other.content.length();
    }
    // Instance Method
    // Returns true if the user's tweet has more likes than the other, false otherwise
    // Fails if either argument doesn't exist
    boolean moreLikes(Tweet other) {
        return this.likes > other.likes;
    }
    // Instance Method
    // Returns a string representing the tweet object
    String toText() {
        return this.user.toText() + " : " + this.content + " : " + this.likes + " Likes";
    }
    // Instance Method
    // Returns a string representing the URL of the page
    String toLink() {
        return "https://twitter.com/" + this.user.username + "/status/" + this.id;
    }
}

class ExampleTweets {
    // Tweet 1 (User 1): https://twitter.com/UCSDJacobs/status/1425975775929331712
    // There were no parts of this tweet that could not be represented due to class design
    User u1 = new User("UCSDJacobs", "UCSD Engineering", 12500);
    Tweet t1 = new Tweet("Kuester said: Our team developed the enabling technology and engineering ecosystem for digital exploration including the digital twin synthesis and visual analytics techniques and the environments being shown that transform how we engage with datadriven mission planning execution", u1, 0, "1425975775929331712");
    // Tweet 2 (User 2): https://twitter.com/acmucsd/status/1361474981880684548
    // There were no parts of this tweet that could not be represented due to class design
    User u2 = new User("acmucsd", "ACM @ UCSD", 38);
    Tweet t2 = new Tweet("How is your day off so far? :)", u2, 1, "1361474981880684548");
    // Tweet 3 (User 2): https://twitter.com/acmucsd/status/1357370013057904640
    // There were no parts of this tweet that could not be represented due to class design
    Tweet t3 = new Tweet("Good morning ACM! How are all your backs now that midterms are almost over?", u2, 1, "1357370013057904640");
    // Tweet 4 (User 3): https://twitter.com/cassidoo/status/1442221050092158979
    // There were no parts of this tweet that could not be represented due to class design
    User u3 = new User("cassidoo", "Cassidy", 171600);
    Tweet t4 = new Tweet("Pi time", u3, 87, "1442221050092158979");

    // Testing methods (var naming convention is acronym of method plus first letter of class then #)
    // toText() (User class)
    String ttu1 = u1.toText(); // expect "UCSD Engineering @UCSDJacobs"
    String ttu2 = u2.toText(); // expect "ACM @ UCSD @acmucsd"

    // longerThan() (Tweet class)
    boolean ltt1 = t1.longerThan(t2); // expect true
    boolean ltt2 = t3.longerThan(t4); // expect true

    // moreLikes() (Tweet class)
    boolean mlt1 = t2.moreLikes(t3); // expect false (because they are even)
    boolean mlt2 = t4.moreLikes(t1); // expect true

    // toText() (Tweet class)
    String ttt1 = t3.toText(); // expect "ACM @ UCSD @acmucsd : Good morning ACM! How are all your backs now that midterms are almost over? : 1 Likes"
    String ttt2 = t4.toText(); // expect "Cassidy @cassidoo : Pi time : 87 Likes"

    // toLink() (Tweet class)
    String tlt1 = t1.toLink(); // expect "https://twitter.com/UCSDJacobs/status/1425975775929331712"
    String tlt2 = t2.toLink(); // expect "https://twitter.com/acmucsd/status/1361474981880684548"
}
