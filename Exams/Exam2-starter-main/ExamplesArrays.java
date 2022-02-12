// EXAM INSTRUCTIONS:
// All of your code for Task 1 goes in this file.
import tester.*;

class ExamplesArrays{

    static double averageWithoutLowest(double[] d) {
        if (d.length < 2) {
            return 0;
        }
        double min = d[0];
        double sum = d[0];
        double avg = 0;
        for (int i = 1; i < d.length; i++) {
            if (d[i]<min) {
                min = d[i];
            }
            sum += d[i];
        }
        /*
        Using the test with unique3
        Array is {12.0, 8.0, 13.0, 11.0}, for reference
        i start   i end   min start min end   sum start   sum end
        1      	  2       12.0      8.0       12.0        20.0   
        2         3       8.0       8.0		  20.0        33.0
        3         3       8.0       8.0       33.0        44.0  
        */
        sum -= min;
        avg = sum / (d.length-1);
        return avg;
    }

    static int[] sumOfPairs(Pair[] p) {
        int[] ret = new int[p.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = p[i].a + p[i].b;
        }
        return ret;
    }

    static Region[] regionsWithPoint(Region[] r, Point p ){
        int ctr = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i].contains(p)) {
                ctr++;
            }
        }
        Region ret[] = new Region[ctr];
        int count = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i].contains(p)) {
                ret[count] = r[i];
                count++;
            }
        }
        return ret;
    }

}

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class ArraysExamples {
    void testAverageWithoutLowest(Tester t) {
        double[] unique = {1.0,2.0,3.0};
        t.checkExpect(ExamplesArrays.averageWithoutLowest(unique), 2.5);
        double[] unique2 = {1.0};
        t.checkExpect(ExamplesArrays.averageWithoutLowest(unique2), 0.0);
        double[] unique3 = {12.0, 8.0, 13.0, 11.0};
        t.checkExpect(ExamplesArrays.averageWithoutLowest(unique3), 12.0);
    };
    void testSumOfPairs(Tester t) {
		Pair[] pairs = {new Pair(1, 2), new Pair(3, 4)};
		int[] result = {3, 7};
		t.checkExpect(ExamplesArrays.sumOfPairs(pairs), result);
        Pair[] pairs2 = {new Pair(1, 2), new Pair(3, 4), new Pair(5, 6), new Pair(7, 8), new Pair(9, 10), new Pair(12, 11)};
		int[] result2 = {3, 7, 11, 15, 19, 23};
		t.checkExpect(ExamplesArrays.sumOfPairs(pairs2), result2);
	};
    void testRegionsWithPoint(Tester t) {
		Region[] regions = {new CircleRegion(new Point(0, 0), 5), new CircleRegion(new Point(0, 0), 10)};
		Region[] result = {new CircleRegion(new Point(0, 0), 10)};
		t.checkExpect(ExamplesArrays.regionsWithPoint(regions, new Point(9, 0)), result);
        Region[] regions2 = {new RectRegion(new Point(0, 0), new Point(10,10)), new CircleRegion(new Point(20, 20), 10)};
		Region[] result2 = {new RectRegion(new Point(0, 0), new Point(10,10))};
		t.checkExpect(ExamplesArrays.regionsWithPoint(regions2, new Point(9, 2)), result2);
	};
}