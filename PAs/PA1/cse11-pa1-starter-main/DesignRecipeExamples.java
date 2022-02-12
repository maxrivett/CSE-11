import tester.*;

class DesignRecipeExamples {

  /* 
    Calculates a rectangle's perimeter
    Takes two integer inputs ("width" and "height")
    Returns an integer representing the rectangle's perimeter
    Method will fail if non-integer variable is used in argument
  */
  public int perimeter(int width, int height) {
    return width * 2 + height * 2;
  }
  int p1 = this.perimeter(3, 6); // expect 18
  int p2 = this.perimeter(11, 7); // expect 36

  /*
    Calculates the border area between a rectangle and a smaller rectangle within it
    Takes four integer inputs, a height and width for each rectangle
    The larger rectangle must be entered first for method to succeed
    Returns the border area as an integer value
  */
  public int borderArea(int width1, int height1, int width2, int height2) {
    // Checking to make sure that the second rectangle can fit in the first
    // If not, return -1
    if (width1>width2 && height1>height2) {
      int area1 = width1*height1;
      int area2 = width2*height2;
      return area1 - area2;
    } 
    return -1;
  }
  int ba1 = this.borderArea(6, 7, 4, 3); // expect 30
  int ba2 = this.borderArea(5, 9, 4, 4); // expect 29
  int ba3 = this.borderArea(4, 3, 3, 7); // expect -1 because height2 > height1

  /*
    Calculates how many single items there are in an inputted number of dozens
    Takes one integer input "dozens"
    Returns an integer that represents the number of singular items
    Possible use case: Farmer wants to calculate how many eggs he has in his cartons of one dozen each
    Method calculates dozens to singles with the same results as every online converter I could find
    Method will fail if non-integer variable is used in argument
  */
  int dozenToSingles(int dozens) {
    int singles = dozens*12; // because there are 12 items in 1 dozen
    return singles;
  }

  int dts1 = this.dozenToSingles(5); // expect 60
  int dts2 = this.dozenToSingles(12); // expect 144

  /*
    Combines meters and centimeters into a total number of centimeters
    (I felt like this was preferrable to feet and inches as a Canadian...)
    Takes two integers as input ("meters" and "centimeters")
    Returns an integer that combines the two into total centimeters
    Method will fail if non-integer variable is used in argument
  */
  int meterAndCentimeterCombiner(int meters, int centimeters) {
    int totalCentimeters = meters*100 + centimeters; // because 1 meter is 100 centimeters
    return totalCentimeters;
  }

  int macc1 = this.meterAndCentimeterCombiner(1, 93); // expect 193
  int macc2 = this.meterAndCentimeterCombiner(41, 6); // expect 4106
  // To my knowledge there are no two arguments that can be added to this function that
  // will run but not produce correct results. The closest thing I can think of would be
  // if you were to input negative numbers but that would still lead to a correct output.
}
