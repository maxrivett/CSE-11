class ExamplesMethods {
    
    // Method takes two doubles as input, returns double
    // Doubles are radii of concentric circles, return value is area between their circumferences
    // Assume that the radii are greater than zero and there is no order of input 
    // (e.g. larger radius is not necessarily r1)
    double ringArea(double r1, double r2) {
        // Area of a circle is (pi)(r)^2
        double a1 = Math.PI * Math.pow(r1, 2);
        double a2 = Math.PI * Math.pow(r2, 2);
        return Math.abs(a1-a2);
    }

    // Method takes a string and int as input and returns a string
    // The returned string should have the first n characters moved to the back
    // If the string length is less than n, return the original string
    // Assume that a negative number is not inputted as the int argument
    String rotate(String str, int n) {
        if (str.length() <= n) { // if str.length() == n then it's the same as returning the original string
            return str;
        }
        return str.substring(n, str.length()) + str.substring(0, n);
    }

    // (Expected values rounded to three decimals, calculated using calculator.)
    double ringAreaTest1 = this.ringArea(10, 15); // expect 392.699
    double ringAreaTest2 = this.ringArea(7, 6); // expect 40.841
    double ringAreaTest3 = this.ringArea(30, 30); // expect 0
    double ringAreaTest4 = this.ringArea(12.001, 12.002); // expect 0.075

    String rotateTest1 = this.rotate("Short", 7); // expect "Short" (String is shorter)
    String rotateTest2 = this.rotate("Hello", 3); // expect "loHel" (String is longer)
    String rotateTest3 = this.rotate("Equal", 5); // expect "Equal"
    String rotateTest4 = this.rotate("CSE11", 3); // expect "11CSE"

}
