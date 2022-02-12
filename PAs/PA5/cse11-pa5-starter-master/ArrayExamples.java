import tester.*;
class ArrayExamples {

    static String joinWith(String[] arr, String separator){
        if (arr.length == 0) {return "";}
        String str = arr[0];
        for (int i = 1; i < arr.length; i++) {
            str += separator + arr[i];
        }
        return str;
    }

    static boolean somethingFalse(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == false) {
                return true;
            }
        }
        return false;
    }

    static int countWithinRange(double[] arr, double low, double high) {
        int ctr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= high && arr[i] >= low) {
                ctr++;
            }
        }
        return ctr;
    }

    static double[] numsWithinRange(double[] arr, double low, double high) {
        double[] darr = new double[ArrayExamples.countWithinRange(arr, low, high)];
        int ctr=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= high && arr[i] >= low) {
                darr[ctr] = arr[i];
                ctr++;
            }
        }
        return darr;
    }

    static Pair maxmin(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {max = arr[i];}
            if (arr[i] < min) {min = arr[i];}
        }
        Pair pair = new Pair(min, max);
        return pair;
    }

    static String earliest(String[] arr) {
        String s = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (s.compareTo(arr[i])>0) {
                s = arr[i];
            }
        }
        return s;
    }

}

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class ProvidedArrayExamples {
    void testJoinWith(Tester t){
      String[] example1 = {"a", "b","c"};
      String[] example2 = {"a"};
      String[] example3 = {};
      t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
      t.checkExpect(ArrayExamples.joinWith(example2, ":"), "a");
      t.checkExpect(ArrayExamples.joinWith(example3, ":"), "");
    }
  
    void testSomethingFalse(Tester t){
      boolean[] example1 = {true, false};
      boolean[] example2 = {true, true, true, true};
      boolean[] example3 = {};
      t.checkExpect(ArrayExamples.somethingFalse(example1), true);
      t.checkExpect(ArrayExamples.somethingFalse(example2), false);
      t.checkExpect(ArrayExamples.somethingFalse(example2), false);
    }
  
    void testCountWithinRange(Tester t){
      double[] example = {0.1, 1.3, 2.6};
      t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
      t.checkExpect(ArrayExamples.countWithinRange(example, -1.1, 1.2), 1);
      t.checkExpect(ArrayExamples.countWithinRange(example, 0.1, 2.6), 3);
    }
  
    void testNumsWithinRange(Tester t){
      double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
      double[] expected1 = {1.4, 1.5, 2.1};
      double[] expected2 = {0.0, 1.4, 1.5};
      double[] expected3 = {};
      t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected1);
      t.checkExpect(ArrayExamples.numsWithinRange(example, -1.1, 1.6), expected2);
      t.checkExpect(ArrayExamples.numsWithinRange(example, 10.1, 12.2), expected3);
    }
  
    void testMaxmin(Tester t){
      int[] example1 = {4, 5, 2, 3, 1};
      int[] example2 = {4, 4, 4, 4, 4};
      int[] example3 = {4, 5, 2, 3, -1};
      int[] example4 = {4};
      t.checkExpect(ArrayExamples.maxmin(example1), new Pair(1, 5));
      t.checkExpect(ArrayExamples.maxmin(example2), new Pair(4, 4));
      t.checkExpect(ArrayExamples.maxmin(example3), new Pair(-1, 5));
      t.checkExpect(ArrayExamples.maxmin(example4), new Pair(4, 4));
    }
  
    void testEarliest(Tester t){
      String[] example1 = {"aa", "aab", "abcd", "a"};
      String[] example2 = {"dog", "cat", "hamster"};
      String[] example3 = {"c", "b", "a", "a"};
      t.checkExpect(ArrayExamples.earliest(example1), "a");
      t.checkExpect(ArrayExamples.earliest(example2), "cat");
      t.checkExpect(ArrayExamples.earliest(example3), "a");
    }
  }