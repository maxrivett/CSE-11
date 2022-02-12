import tester.*;
interface Tweet {
    public boolean isReplyTo(Tweet other);
    public int totalLikes();
    public String allAuthors();
    public boolean textAppearsOnThread(String text);   
}

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

class TextTweet implements Tweet {
    String content;
    int likes;
    User author;
    TextTweet(User author, String content, int likes) {
        this.content = content;
        this.likes = likes;
        this.author = author;
    }
    public boolean isReplyTo(Tweet other) { return false; }
    public int totalLikes(){ return this.likes; }
    public String allAuthors(){ return this.author.username; }

    // should return true when the given text is IN THE CONTENTS of this TextTweet
    // thus we cannot simply use a String.equals(), rather String.indexOf()
    public boolean textAppearsOnThread(String text){
        int flag = this.content.indexOf(text); // will return -1 if not found
        return flag >= 0;
        // could also use contains() method but felt like changing it up
        // I use the contains() method in the ReplyTweet class
    }  
}

class ReplyTweet implements Tweet {
    String content;
    int likes;
    User author;
    Tweet replyTo;
    ReplyTweet(User author, String content, int likes, Tweet replyTo) {
        this.content = content;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }
    public boolean isReplyTo(Tweet other){
        return this.replyTo == other;
    }
    public int totalLikes(){
        return this.likes + this.replyTo.totalLikes();
    }
    public String allAuthors(){
        String str = this.author.username;
        if (this.replyTo.allAuthors().length() > 0) {
            str += ";" + this.replyTo.allAuthors();
        } 
        return str;
    }
    public boolean textAppearsOnThread(String text){
        if(this.content.contains(text)|| this.replyTo.textAppearsOnThread(text)){
            return true;
        }
        return false;
    }   
}

class ExamplesTweets{
    User joe = new User("joepolitz", "Joe Gibbs Politz", 999);
    User greg = new User("gregory_miranda", "Greg Miranda", 9999);
    User rachel = new User("Rachel__Lim", "Rachel Lim", 1000000);
    Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
    Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
    Tweet t3 = new ReplyTweet(this.greg, "Thought about this more, probably not yet, too new.", 73, this.t2);
    Tweet t4 = new ReplyTweet(this.joe, "Yeah, good point. Maybe in 2022.", 10, this.t3);
    Tweet t5 = new ReplyTweet(this.rachel, "Yeah... I don't want to rewrite the book right this minute", 1005, this.t2);

    // void testIsReplyTo(Tester t) {
    //     t.checkExpect(this.t1.isReplyTo(this.t2), false);
    //     t.checkExpect(this.t2.isReplyTo(this.t1), true);
    //     t.checkExpect(this.t5.isReplyTo(this.t2), true);
    //     t.checkExpect(this.t2.isReplyTo(this.t2), false);
    //     t.checkExpect(this.t4.isReplyTo(this.t3), true);
    // }

    // void testTotalLikes(Tester t) {
    //     t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
    //     t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
    //     t.checkExpect(this.t1.totalLikes(), 77);
    // }

    // void testAllAuthors(Tester t) {
    //     t.checkExpect(this.t1.allAuthors(), "joepolitz");
    //     t.checkExpect(this.t2.allAuthors(), "gregory_miranda;joepolitz");
    //     t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;joepolitz");
    //     t.checkExpect(this.t5.allAuthors(), "Rachel__Lim;gregory_miranda;joepolitz");
    // }

    // void testTextAppearsOnThread(Tester t) {
    //     t.checkExpect(this.t1.textAppearsOnThread("joepolitz"), false);
    //     t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
    //     t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
    //     t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
    //     t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
    //     t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
    //     t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
    // }


    //My Examples:
    User max = new User("max.rivett", "Max Rivett", 1000);
    User bot = new User("testbot", "Test Bot", 0);
    User justin = new User("justin_trudeau", "Justin Trudeau", 35000000);

    Tweet tweet1 = new TextTweet(this.justin, "Hello fellow Canadians", 1867);
    Tweet tweet2 = new ReplyTweet(this.max, "Do you respond to your replies?", 151, this.tweet1);
    Tweet tweet3 = new ReplyTweet(this.bot, "I can respond to you. Do you have a question?", 0, this.tweet2);
    Tweet tweet4 = new ReplyTweet(this.max, "Yeah, but now I forgot it.", 10, this.tweet3);
    Tweet tweet5 = new ReplyTweet(this.max, "Question: What are your plans for your third term?", 10000, this.tweet1);

    void testIsReplyTo(Tester t) {
        t.checkExpect(this.tweet1.isReplyTo(this.tweet2), false);
        t.checkExpect(this.tweet2.isReplyTo(this.tweet1), true);
        t.checkExpect(this.tweet5.isReplyTo(this.tweet2), false);
        t.checkExpect(this.tweet2.isReplyTo(this.tweet2), false);
        t.checkExpect(this.tweet4.isReplyTo(this.tweet3), true);
        t.checkExpect(this.tweet5.isReplyTo(this.tweet1), true);
        t.checkExpect(this.tweet4.isReplyTo(this.tweet5), false);
    }

    void testTotalLikes(Tester t) {
        t.checkExpect(this.tweet5.totalLikes(), 10000 + 1867);
        t.checkExpect(this.tweet4.totalLikes(), 10 + 0 + 151 + 1867);
        t.checkExpect(this.tweet1.totalLikes(), 1867);
        t.checkExpect(this.tweet3.totalLikes(), 0+151+1867);
        t.checkExpect(this.tweet2.totalLikes(), 151+1867);
    }

    void testAllAuthors(Tester t) {
        t.checkExpect(this.tweet1.allAuthors(), "justin_trudeau");
        t.checkExpect(this.tweet2.allAuthors(), "max.rivett;justin_trudeau");
        t.checkExpect(this.tweet3.allAuthors(), "testbot;max.rivett;justin_trudeau");
        t.checkExpect(this.tweet4.allAuthors(), "max.rivett;testbot;max.rivett;justin_trudeau");
        t.checkExpect(this.tweet5.allAuthors(), "max.rivett;justin_trudeau");
    }

    void testTextAppearsOnThread(Tester t) {
        t.checkExpect(this.tweet1.textAppearsOnThread("American"), false);
        t.checkExpect(this.tweet1.textAppearsOnThread("Canadian"), true);
        t.checkExpect(this.tweet1.textAppearsOnThread("Hey"), false);
        t.checkExpect(this.tweet2.textAppearsOnThread("fellow"), true);
        t.checkExpect(this.tweet2.textAppearsOnThread("lies?"), true);
        t.checkExpect(this.tweet2.textAppearsOnThread("Max"), false);
        t.checkExpect(this.tweet3.textAppearsOnThread("Do you"), true);
        t.checkExpect(this.tweet3.textAppearsOnThread("question"), true);
        t.checkExpect(this.tweet3.textAppearsOnThread("test"), false);
        t.checkExpect(this.tweet4.textAppearsOnThread("forgot"), true);
        t.checkExpect(this.tweet4.textAppearsOnThread("I can respond"), true);
        t.checkExpect(this.tweet4.textAppearsOnThread("test"), false);
        t.checkExpect(this.tweet5.textAppearsOnThread("third"), true);
        t.checkExpect(this.tweet5.textAppearsOnThread("Can"), true);
        t.checkExpect(this.tweet5.textAppearsOnThread("forgot"), false);
        t.checkExpect(this.tweet5.textAppearsOnThread("2024"), false);
    }
}