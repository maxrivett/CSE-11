import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

// interface Comparator<Value> {
//   int compare(Value v1, Value v2);
// }

class PointCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    if(p1.y > p2.y) { return 1; }
    else if (p1.y == p2.y) {
      if (p1.x > p2.x) { return 1; }
      else if (p1.x < p2.x) { return -1; }
      else { return 0; }
    }
    else { return -1; } // because p1.y < p2.y
  }
}

class PointDistanceCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    Point zero = new Point(0, 0);
    double p1dist = zero.distance(p1);
    double p2dist = zero.distance(p2);
    if (p1dist > p2dist) { return 1; }
    else if (p2dist > p1dist) { return -1; }
    else { return 0; }
  }
}

class StringCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    int ct = s1.compareTo(s2);
    if (ct < 0) { ct = -1; } 
    else if (ct > 0) { ct = 1; }
    else { ct = 0; } 
    return ct;
  }
}

class StringLengthCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    int s1len = s1.length();
    int s2len = s2.length();
    if (s1len > s2len) { return 1; } 
    else if (s1len < s2len) { return -1; }
    else { return 0; }
  }
}

class BooleanCompare implements Comparator<Boolean> {
  public int compare(Boolean b1, Boolean b2) {
    if (b1 == true && b2 == false) { return 1; }
    else if (b1 == false & b2 == true) { return -1; }
    else { return 0; } 
  }
}

// class CompareIntegers implements Comparator<Integer> {
//   public int compare(Integer n, Integer m) {
//     if (n>m) { return 1; }
//     else if (n<m) { return -1; }
//     else { return 0; }
//   }
// }

class ExamplesCompare {

  void testCompare(Tester t) {
    PointCompare pointcompare = new PointCompare();
    PointDistanceCompare pointdistancecompare = new PointDistanceCompare();
    StringCompare stringcompare = new StringCompare();
    StringLengthCompare stringlengthcompare = new StringLengthCompare();
    BooleanCompare booleancompare = new BooleanCompare();
    
    //PointCompare
    Point p1 = new Point(5, 5);
    Point p2 = new Point(5, 5);
    Point p3 = new Point(6, 5);
    Point p4 = new Point(5, 7);
    Point p5 = new Point(6, 7);

    t.checkExpect(pointcompare.compare(p1,p2),0);
    t.checkExpect(pointcompare.compare(p1,p3),-1);
    t.checkExpect(pointcompare.compare(p3,p1),1);
    t.checkExpect(pointcompare.compare(p1,p4),-1);
    t.checkExpect(pointcompare.compare(p1,p5),-1);

    //PointDistanceCompare
    t.checkExpect(pointdistancecompare.compare(p1,p2),0);
    t.checkExpect(pointdistancecompare.compare(p1,p4),-1);
    t.checkExpect(pointdistancecompare.compare(p4,p3),1);
    t.checkExpect(pointdistancecompare.compare(p5,p2),1);

    //StringCompare
    String s1 = "I";
    String s2 = "am";
    String s3 = "inside";
    String s4 = "our";
    String s5 = "library";
    String s6 = "right";
    String s7 = "now";
    String s8 = "now";

    t.checkExpect(stringcompare.compare(s2,s3),-1);
    t.checkExpect(stringcompare.compare(s6,s4),1);
    t.checkExpect(stringcompare.compare(s1,s7),-1);
    t.checkExpect(stringcompare.compare(s5,s2),1);
    t.checkExpect(stringcompare.compare(s8,s7),0);

    //StringLengthCompare

    t.checkExpect(stringlengthcompare.compare(s1,s2),-1);
    t.checkExpect(stringlengthcompare.compare(s3,s4),1);
    t.checkExpect(stringlengthcompare.compare(s5,s6),1);
    t.checkExpect(stringlengthcompare.compare(s7,s8),0);

    //BooleanCompare
    t.checkExpect(booleancompare.compare(false, false), 0);
    t.checkExpect(booleancompare.compare(false, true), -1);
    t.checkExpect(booleancompare.compare(true, false), 1);
    t.checkExpect(booleancompare.compare(true, true), 0);

  }
}


//---------------------------LISTS-------------------------------------

class CompareLists {

  static <E> E minimum(List<E> list, Comparator<E> comp) {
    if (list.isEmpty()) { return null; }
    Collections.sort(list,comp);
    return list.get(0);
  }

  static <E> E minimum(E[] arr, Comparator<E> comp) {
    if (arr.length > 0) {
      Arrays.sort(arr,comp);
      return arr[0];
    }
    return null;
  }

  static <E> List<E> greaterThan(List<E> list, Comparator<E> comp, E e) {
    List<E> newList = new ArrayList<>();
    // if (!list.isEmpty()) {
    //   list.add(e);
    //   Collections.sort(list, comp);
    //   int index = list.indexOf(e);
    //   for (E e1 : list) {
    //     if (list.indexOf(e1) > index) {
    //       newList.add(list.get(list.indexOf(e1)));
    //     }
    //   }
    // }
      // The above way failed for the StringLengthCompare for whatever reason so I rewrote

    for (E e2 : list) {
      if (comp.compare(e2,e) > 0) {
        newList.add(e2);
      }
     }
     Collections.sort(newList, comp);

    return newList;
  }

  static <E> boolean inOrder(List<E> list, Comparator<E> comp) { 
    for (E e : list) {
      if (e == null) {
        throw new IllegalArgumentException("null value in list");
      }
    }
    List<E> newList = new ArrayList<>();
    for (E e : list) {
      newList.add(e);
    }
    Collections.sort(newList,comp);
    if (newList.equals(list)) {
      return true;
    }
    return false;
  }

  static <E> boolean inOrder(E[] arr, Comparator<E> comp) { 
    for (E e : arr) {
      if (e == null) {
        throw new IllegalArgumentException("null value in array");
      }
    }
    List<E> oldList = new ArrayList<E>();
    Collections.addAll(oldList, arr);
    List<E> newList = new ArrayList<E>();
    Collections.addAll(newList, arr);

    Collections.sort(newList,comp);
    if (newList.equals(oldList)) {
      return true;
    }
    return false;
  }

  static <E> List<E> merge(Comparator<E> comp, List<E> list1, List<E> list2) {
    for (E e : list1) {
      if (e == null) {
        throw new IllegalArgumentException("null value in first list");
      }
    }
    for (E e : list2) {
      if (e == null) {
        throw new IllegalArgumentException("null value in second list");
      }
    }
    List<E> newList = new ArrayList<>();
    for (E e : list1) {
      newList.add(e);
    }
    for (E e : list2) {
      newList.add(e);
    }
    Collections.sort(newList,comp);
    return newList;
  }
}

class ExamplesLists {
    void testLists(Tester t) {
      // Test 1
      ArrayList<Point> integers = new ArrayList<>();
      integers.add(new Point(1, 11));
      integers.add(new Point(7, 3));
      integers.add(new Point(3, 10));
      integers.add(new Point(8, 9));
      integers.add(new Point(3, 5));
      integers.add(new Point(14, 2));
      t.checkExpect(CompareLists.minimum(integers, new PointCompare()), new Point(14, 2));
      t.checkExpect(CompareLists.minimum(integers, new PointDistanceCompare()), new Point(3,5));

      ArrayList<Boolean> bools = new ArrayList<>();
      bools.add(true);
      bools.add(false);

      t.checkExpect(CompareLists.minimum(bools, new BooleanCompare()), false);

      // Test 2
      String[] array = {"cat", "puppy", "goldfish", "aardvark"};
      Boolean[] boolarr = {true, false, true, false};
      t.checkExpect(CompareLists.minimum(array, new StringLengthCompare()), "cat");
      t.checkExpect(CompareLists.minimum(array, new StringCompare()), "aardvark");
      t.checkExpect(CompareLists.minimum(boolarr, new BooleanCompare()), false);

      // Test 3
      ArrayList<String> stringList = new ArrayList<>();
      stringList.add("hi");
      stringList.add("my");
      stringList.add("name");
      stringList.add("is");
      stringList.add("max");

      ArrayList<String> stringList2 = new ArrayList<>();
      stringList2.add("hi");
      stringList2.add("my");
      stringList2.add("name");
      stringList2.add("is");
      stringList2.add("max");

      ArrayList<String> expected = new ArrayList<>();
      expected.add("max");
      expected.add("name");

      ArrayList<String> expected2 = new ArrayList<>();
      expected2.add("my");
      expected2.add("name");

      ArrayList<Boolean> arrlistbool = new ArrayList<>();
      arrlistbool.add(true);
      arrlistbool.add(true);
      arrlistbool.add(false);
      arrlistbool.add(false);
      arrlistbool.add(false);
      arrlistbool.add(true);

      ArrayList<Boolean> arrlistbool2 = new ArrayList<>();
      arrlistbool2.add(true);
      arrlistbool2.add(true);
      arrlistbool2.add(true);

      t.checkExpect(CompareLists.greaterThan(stringList, new StringLengthCompare(), "zz"), expected);
      t.checkExpect(CompareLists.greaterThan(stringList2, new StringCompare(), "more"), expected2);
      t.checkExpect(CompareLists.greaterThan(arrlistbool, new BooleanCompare(), false), arrlistbool2);      

      // Test 4
      ArrayList<String> stringListOrdered = new ArrayList<>();
      stringList.add("hi");
      stringList.add("is");
      stringList.add("max");
      stringList.add("my");
      stringList.add("name");
      t.checkExpect(CompareLists.inOrder(stringList, new StringCompare()), false);
      t.checkExpect(CompareLists.inOrder(stringListOrdered, new StringCompare()), true);


      // Throw Exception
      ArrayList<Point> throwException = new ArrayList<>();
      throwException.add(new Point(1,1));
      throwException.add(null);

      t.checkExpect(CompareLists.inOrder(throwException, new PointCompare()), new Point(1,1));
      t.checkException(new IllegalArgumentException("null value in list"), new CompareLists(), "inOrder", throwException, new PointCompare());

      // Test 5
      String[] arrayAgain = {"cat", "puppy", "goldfish!", "aardvark"};
      String[] arrayOrderedString = {"aardvarks", "cat", "goldfish!", "puppy"};
      t.checkExpect(CompareLists.inOrder(arrayAgain, new StringCompare()), false);
      t.checkExpect(CompareLists.inOrder(arrayOrderedString, new StringCompare()), true);

      String[] arrayOrderedStringLength = {"cat", "puppy", "aardvark", "goldfish!"};
      t.checkExpect(CompareLists.inOrder(arrayAgain, new StringLengthCompare()), false);
      t.checkExpect(CompareLists.inOrder(arrayOrderedStringLength, new StringLengthCompare()), true);

      String[] nullHere = {"cat", null, "dog"};
      t.checkException(new IllegalArgumentException("null value in array"), new CompareLists(), "inOrder", nullHere, new StringLengthCompare());

      // Test 6
      ArrayList<Point> p1 = new ArrayList<>();
      p1.add(new Point(2, 4));
      p1.add(new Point(3, 2));
      p1.add(new Point(1, 7));
      p1.add(new Point(6, 3));
      p1.add(new Point(5, 1));
      p1.add(new Point(8, 9));

      ArrayList<Point> p2 = new ArrayList<>();
      p2.add(new Point(1, 6));
      p2.add(new Point(7, 5));
      p2.add(new Point(1, 3));
      p2.add(new Point(6, 4));
      p2.add(new Point(4, 7));
      p2.add(new Point(2, 8));

      ArrayList<Point> p3PointOrdered = new ArrayList<>();
      p3PointOrdered.add(new Point(5, 1));
      p3PointOrdered.add(new Point(3, 2));
      p3PointOrdered.add(new Point(1, 3));
      p3PointOrdered.add(new Point(6, 3));
      p3PointOrdered.add(new Point(2, 4));
      p3PointOrdered.add(new Point(6, 4));
      p3PointOrdered.add(new Point(7, 5));
      p3PointOrdered.add(new Point(1, 6));
      p3PointOrdered.add(new Point(1, 7));
      p3PointOrdered.add(new Point(4, 7));
      p3PointOrdered.add(new Point(2, 8));
      p3PointOrdered.add(new Point(8, 9));

      ArrayList<Point> p3PointDistanceOrdered = new ArrayList<>();
      p3PointDistanceOrdered.add(new Point(1, 3));
      p3PointDistanceOrdered.add(new Point(3, 2));
      p3PointDistanceOrdered.add(new Point(2, 4));
      p3PointDistanceOrdered.add(new Point(5, 1));
      p3PointDistanceOrdered.add(new Point(1, 6));
      p3PointDistanceOrdered.add(new Point(6, 3));
      p3PointDistanceOrdered.add(new Point(1, 7));
      p3PointDistanceOrdered.add(new Point(6, 4));
      p3PointDistanceOrdered.add(new Point(4, 7));
      p3PointDistanceOrdered.add(new Point(2, 8));
      p3PointDistanceOrdered.add(new Point(7, 5));
      p3PointDistanceOrdered.add(new Point(8, 9));

      ArrayList<Boolean> b1 = new ArrayList<>();
      b1.add(true);
      b1.add(false);
      b1.add(false);
      b1.add(true);

      ArrayList<Boolean> b2 = new ArrayList<>();
      b2.add(true);
      b2.add(true);
      b2.add(false);
      b2.add(true);
      
      ArrayList<Boolean> b3Ordered = new ArrayList<>();
      b3Ordered.add(false);
      b3Ordered.add(false);
      b3Ordered.add(false);
      
      b3Ordered.add(true);
      b3Ordered.add(true);
      b3Ordered.add(true);
      b3Ordered.add(true);
      b3Ordered.add(true);

      ArrayList<Boolean> b12 = new ArrayList<>();
      b12.add(true);

      ArrayList<Boolean> b22 = new ArrayList<>();
      b22.add(false);
      b22.add(null);

      ArrayList<Boolean> b3Ordered2 = new ArrayList<>();
      b3Ordered2.add(false);
      b3Ordered2.add(true);

      t.checkExpect(CompareLists.merge(new PointCompare(), p1, p2), p3PointOrdered);
      t.checkExpect(CompareLists.merge(new PointDistanceCompare(), p2, p1), p3PointDistanceOrdered);
      t.checkExpect(CompareLists.merge(new BooleanCompare(), b1, b2), b3Ordered);
      t.checkException(new IllegalArgumentException("null value in second list"), new CompareLists(), "merge",new BooleanCompare(),b12, b22);

    }
}