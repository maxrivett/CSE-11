---
layout: with-sidebar
index: 3
name: Exam 1
released-on: "2021-10-13"
---
# Exam 1

### Release: 5pm Wednesday October 13, 2021
### Due: 9am Friday October 15, 2021

**Note that this is released after class Wednesday, and is due before class, not
due in the evening.**

This page details a take-home exam that you will complete over the next few
days. You can’t communicate with anyone about the content of the assignment
until you receive your grade. You can message us privately on Piazza, but the
course staff will not give programming advice or answer most questions about the
problems. If you have technical trouble creating a screencast (detailed below)
feel free to reach out for assistance.

Do not use any online service other than Piazza to ask questions about the
assignment. Do not search for, solicit, or use solutions to the problems that
you find elsewhere for the exam. These are all violations of academic integrity
that students have committed on exams like this in the past.

You can make use of any course notes, online resources about Java and its
libraries, Java tools, and so on to complete the exam, including re-using code
from class notes.

You can review the grading policy for exams in the [syllabus](/syllabus.html).
You will complete the programming task below and submit your work to the `exam1` Gradescope assignment.

Submission checklist (see long descriptions below for full details):

- [ ] `ExamplesMethods.java` containing methods from Task 1
- [ ] `transcript-methods.txt` containing the results of running your examples for Task 1
- [ ] `ExamplesTweets.java` containing your modifications as described below
- [ ] `transcript-tweets.txt` containing the results of running your examples for Task 2
- [ ] `explanation.mp4` (or another video extension) that has your screencast for Task 3

Your submission will be graded **after** the deadline; there are no pre-deadline check-offs for exams. You should test thoroughly yourself to make sure your program works as expected.

## Tasks

You can find the starter code in this repository [https://github.com/ucsd-cse11-f21/cse11-exam1-starter](https://github.com/ucsd-cse11-f21/cse11-exam1-starter).

### Task 1

In the `ExamplesMethods` class, you will use the design recipe to write two methods:

- `ringArea`, which takes two `double`s representing the radii of two concentric circles and returns the area of the ring between the two circles. Recall that the area of a circle is given by πr<sup>2</sup>. You can use `3.1415926` as an approximation of π, or `Math.PI`.

  <img src="/ring-area.png" width="300px">

  You can assume the radii are greater than 0.
- `rotate`, which takes a `String` called `str` and an `int` called `n` produces a `String` with the first `n` characters of `str` removed from the beginning and added at the end. If the string is shorter than `n` characters, return the original string. For example, `rotate("Hello", 3)` would produce `"loHel"`.

Note that it's fine to add _more_ methods than what we've listed, but these two methods must appear as described.

Note that you should use the full design recipe – include enough examples to
convince yourself the method works! If you have a question about an interesting
input case, try it out and justify why the answer is OK.

### Task 2

The file `ExamplesTweets.java` has two classes, `User` and `Tweet`, which are a partial solution for the same program as in PA2.

Your task is to add:

- A new class called `Date` that represents a particular calendar day (reminder that you can use code and ideas from problem sessions for this)
- A new field on `Tweet` that represents the day the Tweet was posted, including any needed updates to the constructor
- A new method on `Tweet` called `before` that takes another `Tweet` and returns `true` if `this` Tweet was posted strictly before the Tweet given as an argument.
- The following examples for the `before` method:
  - `tweetExample1` – Two different Tweets posted on different months in the same year that returns `false`
  - `tweetExample2` – Two different Tweets posted on different days in the same month and same year that returns `true`
  - `tweetExample3` – The same Tweet used as both `this` and as the argument to `before`.
  - `tweetExample4` – Two different Tweets posted in different years, with the `this` `Tweet` having an _earlier_ month and day than the argument, and that returns `false`

  You can make up any example Tweets you like for input data (they don't have to
  be real-world Tweets)


### Task 3 – Video
You will record a short video of no more than 5 minutes. Include:

- Show only your face and a picture ID (your student ID is preferred but any picture ID with your name on it will work) for a few seconds at the beginning. You don’t have to be on camera the whole time, though it’s fine if you are. Just a brief confirmation that it’s you creating the video/doing the work attached to the work itself is what we want. If you do not have a webcam, take a picture of yourself (and your picture ID) with your phone and display that picture at the start of your screen share.
- Choose two examples for your `rotate` method, one where the string is shorter than `n`, and one where it is longer. Highlight the examples, and for each, show which return statement in your program runs, and what value is returned by that statement.
- Highlight the example you wrote for `tweetExample2`. Highlight all of the lines of code that evaluate when that example runs; mark those lines with a comment at the end of the line as you go.

An example of what your video should look like when doing this kind of explanation is here:

[https://drive.google.com/file/d/117NfZaHXbHtMJZJmLRVeRy-1b9cY3jei/view?usp=sharing](https://drive.google.com/file/d/117NfZaHXbHtMJZJmLRVeRy-1b9cY3jei/view?usp=sharing)

PA2 has a tutorial for creating a screencast like this [https://ucsd-cse11-f21.github.io/assignments/pa2.html](https://ucsd-cse11-f21.github.io/assignments/pa2.html).

Here are some notes on how to improve your videos:

- Make sure to use a picture ID, either a student ID _with a photo_, a driver's license, or passport that has a picture of you. If you do not provide a picture ID, you may get a 0 on the exam until prove to us it was you who did the video.
- Make sure your picture ID and face are visible at the same time for three or four seconds. We must be able to pause the video and verify it's you. Again, if we can't verify it's you, you will get a 0 on the exam until prove to us it was you who did the video. Make sure to fill up the screen as much as possible with your face and picture id (i.e. don't stand far away from your camera).
- When you start recording your video, start with screen share off and camera on and show your picture ID and face (close-up!!). Then you can enable screen share (and disable camera) and walk through your code.
- Video must have sound! While highlighting your code, also make sure to explain the code. We must hear you explain it!
- Once you enable screen share, make sure to leave it on the entire time while explaining your code.
- Do not explain every test case! Only explain what you are explicitly told in the tasks above.
- Keep your videos under 5 minutes; videos even a second over 5 minutes will get a `1`, this is to ensure we can grade them promptly. To ensure you stay under 5 minutes, make sure to only explain what is described above, and do a few practices or make a script before you start recording.
